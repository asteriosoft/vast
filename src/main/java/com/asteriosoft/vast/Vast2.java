package com.asteriosoft.vast;

import com.asteriosoft.vast.v2.ImpressionType;
import com.asteriosoft.vast.v2.ObjectFactory;
import com.asteriosoft.vast.v2.TrackingEventsType;
import com.asteriosoft.vast.v2.TrackingEventsType.Tracking;
import com.asteriosoft.vast.v2.VAST;
import com.asteriosoft.vast.v2.VAST.Ad;
import com.asteriosoft.vast.v2.VideoClicksType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

final class Vast2 extends BaseVast<VAST> {

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
                    String error = wrapper.getError();
                    if (StringUtils.isBlank(error)) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error);
                    }
                    wrapper.setError(error);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<String> impressions = wrapper.getImpression();
                    if (impressions == null) {
                        impressions = Collections.emptyList();
                    }
                    if (!impressions.isEmpty()) {
                        impressions.removeIf(StringUtils::isBlank);
                    }
                    wrapper.setImpression(new ArrayList<>(impressions.size() + imprUrls.size()));
                    wrapper.getImpression().addAll(imprUrls);
                    if (!impressions.isEmpty()) {
                        wrapper.getImpression().addAll(impressions);
                    }
                }

                VAST.Ad.Wrapper.Creatives creatives = wrapper.getCreatives();
                if (creatives != null) {
                    for (VAST.Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = trackers.getLinearClickUrls();

                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks videoClicks = creative.getLinear().getVideoClicks();
                                if (videoClicks == null) {
                                    videoClicks = new VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks();
                                    creative.getLinear().setVideoClicks(videoClicks);
                                }
                                for (String clickUrl : clickUrls) {
                                    List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> trackingsList = videoClicks.getClickTracking();
                                    VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking tracking = new VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking();
                                    tracking.setValue(clickUrl);
                                    trackingsList.add(tracking);
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrls)) {
                                TrackingEventsType eventsType = creative.getLinear().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsType());
                                }
                                List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrls.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        Tracking tracking = new Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }

                        // NON-LINEAR
                        if (creative.getNonLinearAds() != null) {

                            // EVENTS                            
                            if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                                TrackingEventsType eventsType = creative.getNonLinearAds().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsType());
                                }
                                List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        Tracking tracking = new Tracking();
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
                    String error = inLine.getError();
                    if (StringUtils.isBlank(error)) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error);
                    }
                    inLine.setError(error);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<ImpressionType> impressions = inLine.getImpression();
                    if (impressions == null) {
                        impressions = Collections.emptyList();
                    }
                    if (!impressions.isEmpty()) {
                        impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                    }
                    inLine.setImpression(new ArrayList<>(impressions.size() + imprUrls.size()));
                    for (String imprUrl : imprUrls) {
                        ImpressionType impr = new ImpressionType();
                        impr.setValue(imprUrl);
                        inLine.getImpression().add(impr);
                    }
                    if (!impressions.isEmpty()) {
                        inLine.getImpression().addAll(impressions);
                    }
                }

                VAST.Ad.InLine.Creatives creatives = inLine.getCreatives();

                if (creatives != null) {
                    List<VAST.Ad.InLine.Creatives.Creative> creativeAds = creatives.getCreative();

                    for (VAST.Ad.InLine.Creatives.Creative creative : creativeAds) {
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

                                List<VideoClicksType.ClickTracking> trackingsList = videoClicks.getClickTracking();
                                for (String clickUrl : clickUrls) {
                                    if (StringUtils.isEmpty(clickUrl)) {
                                        continue;
                                    }
                                    VideoClicksType.ClickTracking tracking = new VideoClicksType.ClickTracking();
                                    tracking.setValue(clickUrl);
                                    trackingsList.add(tracking);
                                }
                            }

                            // EVENTS
                            if (MapUtils.isNotEmpty(trackingUrls)) {
                                TrackingEventsType eventsType = creative.getLinear().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsType());
                                }
                                List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrls.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        Tracking tracking = new Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }

                        // NON-LINEAR
                        if (creative.getNonLinearAds() != null) {

                            // EVENTS                            
                            if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                                TrackingEventsType eventsType = creative.getNonLinearAds().getTrackingEvents();
                                if (eventsType == null) {
                                    creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsType());
                                }
                                List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                                for (Map.Entry<String, List<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        Tracking tracking = new Tracking();
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
        return VastVersion.VAST2;
    }
}
