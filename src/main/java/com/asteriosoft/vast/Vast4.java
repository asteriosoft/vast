package com.asteriosoft.vast;

import com.asteriosoft.vast.v4.AnyURIType;
import com.asteriosoft.vast.v4.NonLinearType;
import com.asteriosoft.vast.v4.NonLinearWrapperType;
import com.asteriosoft.vast.v4.ObjectFactory;
import com.asteriosoft.vast.v4.TrackingEventsLinearType;
import com.asteriosoft.vast.v4.TrackingEventsNonLinearType;
import com.asteriosoft.vast.v4.VAST;
import com.asteriosoft.vast.v4.VAST.Ad;
import com.asteriosoft.vast.v4.VideoClicksType;
import com.asteriosoft.vast.v4.VideoClicksWrapperType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class Vast4 extends BaseVast<VAST> {

    private final XmlMarshaling xmlMarshaling = new XmlMarshaling(ObjectFactory.class);

    private void doInjectTrackers(VAST vast, VastTrackers trackers) {

        List<String> errorUrls = trackers.getErrorUrls();
        String errorUrl = CollectionUtils.isNotEmpty(errorUrls) ? errorUrls.get(0) : null;

        List<String> imprUrls = trackers.getImpressionUrls();
        Map<String, List<String>> trackingUrls = trackers.getLinearTrackingUrls();
        Map<String, List<String>> trackingUrlsNonLinear = trackers.getNonLinearTrackingUrls();

        List<Ad> ads = vast.getAd();

        for (Ad ad : ads) {

            Ad.Wrapper wrapper = ad.getWrapper();
            if (wrapper != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    List<String> errors = wrapper.getError();
                    if (CollectionUtils.isNotEmpty(errors)) {
                        int i = 0;
                        for (String error : errors) {
                            if (StringUtils.isBlank(error)) {
                                errors.set(i, errorUrl);
                            } else {
                                errors.set(i, errorUrl + "&errorUrl=" + encodeUrl(error));
                            }
                            i++;
                        }
                    } else {
                        errors = Collections.singletonList(errorUrl);
                    }
                    wrapper.setError(errors);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<AnyURIType> impressions = wrapper.getImpression();
                    if (impressions == null) {
                        impressions = Collections.emptyList();
                    }
                    if (!impressions.isEmpty()) {
                        impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                    }
                    wrapper.setImpression(new ArrayList<>(impressions.size() + imprUrls.size()));
                    for (String imprUrl : imprUrls) {
                        AnyURIType impr = new AnyURIType();
                        impr.setValue(imprUrl);
                        wrapper.getImpression().add(impr);
                    }
                    if (!impressions.isEmpty()) {
                        wrapper.getImpression().addAll(impressions);
                    }
                }

                Ad.Wrapper.Creatives creatives = wrapper.getCreatives();
                if (creatives != null) {
                    for (Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = trackers.getLinearClickUrls();

                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                VideoClicksWrapperType videoClicks = creative.getLinear().getVideoClicks();
                                if (videoClicks == null) {
                                    videoClicks = new VideoClicksWrapperType();
                                    creative.getLinear().setVideoClicks(videoClicks);
                                }
                                for (String clickUrl : clickUrls) {
                                    List<AnyURIType> trackingsList = videoClicks.getClickTracking();
                                    AnyURIType tracking = new AnyURIType();
                                    tracking.setValue(clickUrl);
                                    trackingsList.add(tracking);
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrls)) {
                                TrackingEventsLinearType eventsType = creative.getLinear().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsLinearType());
                                }
                                List<TrackingEventsLinearType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrls.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsLinearType.Tracking tracking = new TrackingEventsLinearType.Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }

                        // NON-LINEAR
                        if (creative.getNonLinearAds() != null) {
                            List<String> clickUrls = trackers.getLinearClickUrls();

                            // CLICK
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                List<NonLinearWrapperType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                                if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                                    for (NonLinearWrapperType nonLinearWrapperType : nonLinearTypes) {
                                        for (String clickUrl : clickUrls) {
                                            if (StringUtils.isEmpty(clickUrl)) {
                                                continue;
                                            }
                                            nonLinearWrapperType.getNonLinearClickTracking().add(clickUrl);
                                        }
                                    }
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                                TrackingEventsNonLinearType eventsType = creative.getNonLinearAds().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsNonLinearType());
                                }
                                List<TrackingEventsNonLinearType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsNonLinearType.Tracking tracking = new TrackingEventsNonLinearType.Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Ad.InLine inLine = ad.getInLine();
            if (inLine != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    List<String> errors = inLine.getError();
                    if (CollectionUtils.isNotEmpty(errors)) {
                        int i = 0;
                        for (String error : errors) {
                            if (StringUtils.isBlank(error)) {
                                errors.set(i, errorUrl);
                            } else {
                                errors.set(i, errorUrl + "&errorUrl=" + encodeUrl(error));
                            }
                            i++;
                        }
                    } else {
                        errors = Collections.singletonList(errorUrl);
                    }
                    inLine.setError(errors);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<AnyURIType> impressions = inLine.getImpression();
                    if (impressions == null) {
                        impressions = Collections.emptyList();
                    }
                    if (!impressions.isEmpty()) {
                        impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                    }
                    inLine.setImpression(new ArrayList<>(impressions.size() + imprUrls.size()));
                    for (String imprUrl : imprUrls) {
                        AnyURIType impr = new AnyURIType();
                        impr.setValue(imprUrl);
                        inLine.getImpression().add(impr);
                    }
                    if (!impressions.isEmpty()) {
                        inLine.getImpression().addAll(impressions);
                    }
                }

                Ad.InLine.Creatives creatives = inLine.getCreatives();

                if (creatives != null) {
                    List<Ad.InLine.Creatives.Creative> creativeAds = creatives.getCreative();

                    for (Ad.InLine.Creatives.Creative creative : creativeAds) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = trackers.getLinearClickUrls();

                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                VideoClicksType videoClicks = creative.getLinear().getVideoClicks();
                                if (videoClicks == null) {
                                    videoClicks = new VideoClicksType();
                                    creative.getLinear().setVideoClicks(videoClicks);
                                }

                                List<AnyURIType> trackingsList = videoClicks.getClickTracking();
                                for (String clickUrl : clickUrls) {
                                    if (StringUtils.isEmpty(clickUrl)) {
                                        continue;
                                    }
                                    AnyURIType tracking = new AnyURIType();
                                    tracking.setValue(clickUrl);
                                    trackingsList.add(tracking);
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrls)) {
                                TrackingEventsLinearType eventsType = creative.getLinear().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsLinearType());
                                }
                                List<TrackingEventsLinearType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrls.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsLinearType.Tracking tracking = new TrackingEventsLinearType.Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }

                        // NON-LINEAR
                        if (creative.getNonLinearAds() != null) {

                            List<String> clickUrls = trackers.getLinearClickUrls();

                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                List<NonLinearType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                                if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                                    for (NonLinearType nonLinearType : nonLinearTypes) {
                                        List<AnyURIType> clickTracking = nonLinearType.getNonLinearClickTracking();
                                        for (String clickUrl : clickUrls) {
                                            AnyURIType newClickUrl = new AnyURIType();
                                            newClickUrl.setValue(clickUrl);
                                            clickTracking.add(newClickUrl);
                                        }
                                    }
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                                TrackingEventsNonLinearType eventsType = creative.getNonLinearAds().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsNonLinearType());
                                }
                                List<TrackingEventsNonLinearType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsNonLinearType.Tracking tracking = new TrackingEventsNonLinearType.Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected String injectTracking(String vastStr, VastTrackers trackers) throws XmlMarshalingException {
        VAST vast = unmarshal(vastStr);
        if (vast == null) {
            return null;
        }

        doInjectTrackers(vast, trackers);

        vast.setVersion(getVastVersion().string);
        return marshal(vast);
    }

    @Override
    protected XmlMarshaling getXmlMarshaling() {
        return xmlMarshaling;
    }

    @Override
    protected Class<?> getVastClass() {
        return VAST.class;
    }

    @Override
    protected VastVersion getVastVersion() {
        return VastVersion.VAST4;
    }
}
