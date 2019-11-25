package com.asteriosoft.vast;

import com.asteriosoft.vast.v42.CreativeInlineType;
import com.asteriosoft.vast.v42.CreativeWrapperType;
import com.asteriosoft.vast.v42.ImpressionType;
import com.asteriosoft.vast.v42.InlineType;
import com.asteriosoft.vast.v42.NonLinearAdInlineType;
import com.asteriosoft.vast.v42.ObjectFactory;
import com.asteriosoft.vast.v42.TrackingEventsType;
import com.asteriosoft.vast.v42.VAST;
import com.asteriosoft.vast.v42.VAST.Ad;
import com.asteriosoft.vast.v42.VideoClicksType;
import com.asteriosoft.vast.v42.WrapperType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Vast42 extends BaseVast<VAST> {

    private final XmlMarshaling xmlMarshaling = new XmlMarshaling(ObjectFactory.class);

    private InjectTrackersResult injectWrapperTrackers(boolean createMissingElements, WrapperType.Creatives creatives,
                                                       Set<String> clickUrls, Map<String, Set<String>> trackingUrls, Set<String> clickUrlsNonLinear, Map<String, Set<String>> trackingUrlsNonLinear) {
        boolean linearClickUrlsAdded = false;
        boolean linearTracksAdded = false;
        boolean nonLinearClickUrlsAdded = false;
        boolean nonLinearTracksAdded = false;

        for (CreativeWrapperType creative : creatives.getCreative()) {
            // LINEAR
            if (creative.getLinear() != null) {

                // CLICK TRACKING
                if (!linearClickUrlsAdded && CollectionUtils.isNotEmpty(clickUrls)) {
                    VideoClicksType videoClicks = creative.getLinear().getVideoClicks();
                    if (videoClicks == null) {
                        videoClicks = new VideoClicksType();
                        creative.getLinear().setVideoClicks(videoClicks);
                    }
                    if (videoClicks != null) {
                        for (String clickUrl : clickUrls) {
                            List<VideoClicksType.ClickTracking> trackingsList = videoClicks.getClickTracking();
                            VideoClicksType.ClickTracking tracking = new VideoClicksType.ClickTracking();
                            tracking.setValue(clickUrl);
                            trackingsList.add(tracking);
                        }
                        linearClickUrlsAdded = true;
                    }
                }

                // EVENTS
                if (!linearTracksAdded && MapUtils.isNotEmpty(trackingUrls)) {
                    TrackingEventsType eventsType = creative.getLinear().getTrackingEvents();
                    if (eventsType == null && createMissingElements) {
                        creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsType());
                    }
                    if (eventsType != null) {
                        List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                        for (Map.Entry<String, Set<String>> stringListEntry : trackingUrls.entrySet()) {
                            for (String url : stringListEntry.getValue()) {
                                if (StringUtils.isEmpty(url)) {
                                    continue;
                                }
                                TrackingEventsType.Tracking tracking = new TrackingEventsType.Tracking();
                                tracking.setEvent(stringListEntry.getKey());
                                tracking.setValue(url);
                                trackingList.add(tracking);
                            }
                        }
                        linearTracksAdded = true;
                    }
                }
            }

            // NON-LINEAR
            if (creative.getNonLinearAds() != null) {
                // CLICK
                if (CollectionUtils.isNotEmpty(clickUrlsNonLinear)) {
                    TrackingEventsType trackingEvents = creative.getNonLinearAds().getTrackingEvents();
                    if (trackingEvents == null && createMissingElements) {
                        creative.getNonLinearAds().setTrackingEvents(trackingEvents = new TrackingEventsType());
                    }
                    if (trackingEvents != null) {
                        for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                            for (String url : stringListEntry.getValue()) {
                                if (StringUtils.isEmpty(url)) {
                                    continue;
                                }
                                TrackingEventsType.Tracking tracking = new TrackingEventsType.Tracking();
                                tracking.setEvent(stringListEntry.getKey());
                                tracking.setValue(url);
                                trackingEvents.getTracking().add(tracking);
                            }
                        }
                        nonLinearTracksAdded = true;
                    }
                }

                // todo: NonLinearAdBaseType & NonLinearAdInlineType need to clarify
                /*if (CollectionUtils.isNotEmpty(clickUrlsNonLinear)) {
                    List<NonLinearAdBaseType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                    if (CollectionUtils.isEmpty(nonLinearTypes) && createMissingElements) {
                        creative.getNonLinearAds().getNonLinear().add(new NonLinearAdBaseType());
                    }
                    if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                        for (NonLinearAdBaseType nonLinearAdBaseType : nonLinearTypes) {
                            for (String clickUrl : clickUrlsNonLinear) {
                                if (StringUtils.isEmpty(clickUrl)) {
                                    continue;
                                }
                                nonLinearAdBaseType.getNonLinearClickTracking().add(clickUrl);
                            }
                        }
                    }
                    TrackingEventsType trackingEvents = creative.getNonLinearAds().getTrackingEvents();
                    if (CollectionUtils.isEmpty(trackingEvents.getTracking()) && createMissingElements) {
                        trackingEvents.getTracking().add(new TrackingEventsType.Tracking());
                        creative.getNonLinearAds().setTrackingEvents(trackingEvents);
                    }
                    if (CollectionUtils.isNotEmpty(trackingEvents.getTracking())) {
                        for (TrackingEventsType.Tracking tracking : trackingEvents.getTracking()) {
                            for (String clickUrl : clickUrlsNonLinear) {
                                if (StringUtils.isEmpty(clickUrl)) {
                                    continue;
                                }
                                tracking.getNonLinearClickTracking().add(clickUrl);
                            }
                        }
                    }
                    nonLinearClickUrlsAdded = true;
                }*/

                // EVENTS
                if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                    TrackingEventsType trackingEvents = creative.getNonLinearAds().getTrackingEvents();
                    if (trackingEvents == null && createMissingElements) {
                        creative.getNonLinearAds().setTrackingEvents(trackingEvents = new TrackingEventsType());
                    }
                    if (trackingEvents != null) {
                        List<TrackingEventsType.Tracking> trackingList = trackingEvents.getTracking();
                        for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                            for (String url : stringListEntry.getValue()) {
                                if (StringUtils.isEmpty(url)) {
                                    continue;
                                }
                                TrackingEventsType.Tracking tracking = new TrackingEventsType.Tracking();
                                tracking.setEvent(stringListEntry.getKey());
                                tracking.setValue(url);
                                trackingList.add(tracking);
                            }
                        }
                        nonLinearTracksAdded = true;
                    }
                }
            }
        }
        return new InjectTrackersResult(linearTracksAdded, linearClickUrlsAdded, nonLinearTracksAdded, nonLinearClickUrlsAdded);
    }

    private void doInjectTrackers(VAST vast, VastTrackers trackers) {

        List<String> errorUrls = trackers.getErrorUrls();
        List<String> imprUrls = trackers.getImpressionUrls();

        Map<String, Set<String>> trackingUrls = trackers.getLinearTrackingUrls();
        Set<String> clickUrls = trackers.getLinearClickUrls();

        Map<String, Set<String>> trackingUrlsNonLinear = trackers.getNonLinearTrackingUrls();
        Set<String> clickUrlsNonLinear = trackers.getNonLinearClickUrls();

        List<Ad> ads = vast.getAd();

        for (Ad ad : ads) {

            WrapperType wrapper = ad.getWrapper();
            if (wrapper != null) {

                if (CollectionUtils.isNotEmpty(errorUrls)) {
                    wrapper.getError().addAll(errorUrls);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    Set<String> newImprs = new LinkedHashSet<>();
                    if (wrapper.getImpression() != null) {
                        for (ImpressionType type : wrapper.getImpression()) {
                            if (StringUtils.isNotBlank(type.getValue())) {
                                newImprs.add(type.getValue());
                            }
                        }
                    }
                    newImprs.addAll(imprUrls);
                    List<ImpressionType> newList = new ArrayList<>(newImprs.size());
                    for (String string : newImprs) {
                        ImpressionType impr = new ImpressionType();
                        impr.setValue(string);
                        newList.add(impr);
                    }
                    wrapper.getImpression().addAll(newList);
                }

                if (!trackingUrls.isEmpty() || !clickUrls.isEmpty() || !trackingUrlsNonLinear.isEmpty() || !clickUrlsNonLinear.isEmpty()) {
                    WrapperType.Creatives creatives = wrapper.getCreatives();
                    if (creatives == null) {
                        creatives = new WrapperType.Creatives();
                        wrapper.setCreatives(creatives);
                        injectWrapperTrackers(true, creatives, clickUrls, trackingUrls, clickUrlsNonLinear, trackingUrlsNonLinear);
                    } else {
                        InjectTrackersResult result = injectWrapperTrackers(false, creatives, clickUrls, trackingUrls, clickUrlsNonLinear, trackingUrlsNonLinear);
                        if (result.linearClickUrlsAdded) {
                            clickUrls = Collections.emptySet();
                        }
                        if (result.linearTracksAdded) {
                            trackingUrls = Collections.emptyMap();
                        }
                        if (result.nonLinearClickUrlsAdded) {
                            clickUrlsNonLinear = Collections.emptySet();
                        }
                        if (result.nonLinearTracksAdded) {
                            trackingUrlsNonLinear = Collections.emptyMap();
                        }
                        if (!trackingUrls.isEmpty() || !clickUrls.isEmpty() || !trackingUrlsNonLinear.isEmpty()) {
                            injectWrapperTrackers(true, creatives, clickUrls, trackingUrls, clickUrlsNonLinear, trackingUrlsNonLinear);
                        }
                    }
                }
            }

            InlineType inLine = ad.getInLine();
            if (inLine != null) {

                // error
                if (CollectionUtils.isNotEmpty(errorUrls)) {
                    inLine.getError().addAll(errorUrls);
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
                    for (String imprUrl : imprUrls) {
                        ImpressionType impr = new ImpressionType();
                        impr.setValue(imprUrl);
                        inLine.getImpression().add(impr);
                    }
                    if (!impressions.isEmpty()) {
                        inLine.getImpression().addAll(impressions);
                    }
                }

                InlineType.Creatives creatives = inLine.getCreatives();

                if (creatives != null) {
                    List<CreativeInlineType> creativeAds = creatives.getCreative();

                    for (CreativeInlineType creative : creativeAds) {
                        // LINEAR
                        if (creative.getLinear() != null) {
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
                                for (Map.Entry<String, Set<String>> stringListEntry : trackingUrls.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsType.Tracking tracking = new TrackingEventsType.Tracking();
                                        tracking.setEvent(stringListEntry.getKey());
                                        tracking.setValue(url);
                                        trackingList.add(tracking);
                                    }
                                }
                            }
                        }

                        // NON-LINEAR
                        if (creative.getNonLinearAds() != null) {
                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrlsNonLinear)) {
                                List<NonLinearAdInlineType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                                if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                                    for (NonLinearAdInlineType nonLinearType : nonLinearTypes) {
                                        List<NonLinearAdInlineType.NonLinearClickTracking> clickTracking = nonLinearType.getNonLinearClickTracking();
                                        for (String clickUrl : clickUrlsNonLinear) {
                                            NonLinearAdInlineType.NonLinearClickTracking newClickUrl = new NonLinearAdInlineType.NonLinearClickTracking();
                                            newClickUrl.setValue(clickUrl);
                                            clickTracking.add(newClickUrl);
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
                                for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
                                    for (String url : stringListEntry.getValue()) {
                                        if (StringUtils.isEmpty(url)) {
                                            continue;
                                        }
                                        TrackingEventsType.Tracking tracking = new TrackingEventsType.Tracking();
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
    protected void injectTracking(VAST vast, VastTrackers trackers) {
        if (vast == null) {
            return;
        }
        doInjectTrackers(vast, trackers);
        vast.setVersion(getVastVersion().string);
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
