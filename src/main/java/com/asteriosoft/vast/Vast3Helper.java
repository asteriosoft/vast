package com.asteriosoft.vast;

import com.asteriosoft.vast.v3.ImpressionType;
import com.asteriosoft.vast.v3.NonLinearType;
import com.asteriosoft.vast.v3.NonLinearWrapperType;
import com.asteriosoft.vast.v3.ObjectFactory;
import com.asteriosoft.vast.v3.TrackingEventsType;
import com.asteriosoft.vast.v3.TrackingEventsType.Tracking;
import com.asteriosoft.vast.v3.VAST;
import com.asteriosoft.vast.v3.VAST.Ad;
import com.asteriosoft.vast.v3.VideoClicksType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class Vast3Helper extends BaseVastHelper {

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
                    String error = ad.getWrapper().getError();
                    if (StringUtils.isBlank(error)) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error);
                    }
                    ad.getWrapper().setError(error);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    List<String> impressions = ad.getWrapper().getImpression();
                    impressions.addAll(imprUrls);
                }

                VAST.Ad.Wrapper.Creatives creatives = ad.getWrapper().getCreatives();
                if (creatives != null) {
                    for (VAST.Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

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

            if (ad.getInLine() != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    String error = ad.getInLine().getError();
                    if (StringUtils.isBlank(error)) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error);
                    }
                    ad.getInLine().setError(error);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    for (String imprUrl : imprUrls) {
                        List<ImpressionType> impressions = ad.getInLine().getImpression();
                        impressions.removeIf(t -> StringUtils.isBlank(t.getValue()));
                        ImpressionType impr = new ImpressionType();
                        impr.setValue(imprUrl);
                        impressions.add(impr);
                    }
                }

                VAST.Ad.InLine.Creatives creatives = ad.getInLine().getCreatives();

                if (creatives != null) {
                    List<VAST.Ad.InLine.Creatives.Creative> creativeAds = creatives.getCreative();

                    for (VAST.Ad.InLine.Creatives.Creative creative : creativeAds) {
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

                            List<String> clickUrls = get(VASTTag.UrlClicks.getTagName(), data);

                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                List<NonLinearType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                                if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                                    for (NonLinearType nonLinearType : nonLinearTypes) {
                                        nonLinearType.getNonLinearClickTracking().addAll(clickUrls);
                                    }
                                }
                            }

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
        return VastVersion.VAST3;
    }
}
