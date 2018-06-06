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

import java.util.List;
import java.util.Map;

public final class Vast4Helper extends BaseVastHelper {

    private final XmlMarshaling xmlMarshaling = new XmlMarshaling(ObjectFactory.class);

    private void injectTracking(VAST vast, Map<String, Object> data) {

        List<String> errorUrls = get(VASTTag.UrlErrors.getTagName(), data);
        String errorUrl = CollectionUtils.isNotEmpty(errorUrls) ? errorUrls.get(0) : null;

        List<String> imprUrls = get(VASTTag.UrlImpressions.getTagName(), data);
        Map<String, List<String>> trackingUrls = get(VASTTag.UrlsTrackingLinear.getTagName(), data);
        Map<String, List<String>> trackingUrlsNonLinear = get(VASTTag.UrlsTrackingNonLinear.getTagName(), data);

        List<Ad> ads = vast.getAd();

        for (Ad ad : ads) {

            if (ad.getWrapper() != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    List<String> errors = ad.getWrapper().getError();
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
                        errors = List.of(errorUrl);
                    }
                    ad.getWrapper().setError(errors);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<AnyURIType> impressions = ad.getWrapper().getImpression();
                    impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                    for (String imprUrl : imprUrls) {
                        AnyURIType impr = new AnyURIType();
                        impr.setValue(imprUrl);
                        impressions.add(impr);
                    }
                }

                Ad.Wrapper.Creatives creatives = ad.getWrapper().getCreatives();
                if (creatives != null) {
                    for (Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

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
                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

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

            if (ad.getInLine() != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    List<String> errors = ad.getInLine().getError();
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
                        errors = List.of(errorUrl);
                    }
                    ad.getInLine().setError(errors);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    for (String imprUrl : imprUrls) {
                        List<AnyURIType> impressions = ad.getInLine().getImpression();
                        impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                        AnyURIType impr = new AnyURIType();
                        impr.setValue(imprUrl);
                        impressions.add(impr);
                    }
                }

                Ad.InLine.Creatives creatives = ad.getInLine().getCreatives();

                if (creatives != null) {
                    List<Ad.InLine.Creatives.Creative> creativeAds = creatives.getCreative();

                    for (Ad.InLine.Creatives.Creative creative : creativeAds) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

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

                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

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
    protected String injectTracking(String vastStr, Map<String, Object> vastdata) throws XmlMarshalingException {
        VAST vast = unmarshal(vastStr, xmlMarshaling, VAST.class);
        if (vast == null) {
            return null;
        }

        injectTracking(vast, vastdata);

        vast.setVersion(getVastVersion().versionStr);
        return marshal(vast, xmlMarshaling);
    }

    @Override
    protected VastVersion getVastVersion() {
        return VastVersion.VAST4;
    }
}
