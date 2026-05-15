/*
 *
 *  * Copyright 2023 Asteriosoft Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Vast2 extends BaseVast<VAST> {

    private final XmlMarshaling xmlMarshaling = new XmlMarshaling(ObjectFactory.class);

    private InjectTrackersResult injectWrapperTrackers(boolean createMissingElements, VAST.Ad.Wrapper.Creatives creatives,
                                                       Set<String> clickUrls, Map<String, Set<String>> trackingUrls, Set<String> clickUrlsNonLinear, Map<String, Set<String>> trackingUrlsNonLinear) {
        boolean linearClickUrlsAdded = false;
        boolean linearTracksAdded = false;
        boolean nonLinearClickUrlsAdded = false;
        boolean nonLinearTracksAdded = false;

        for (VAST.Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
            // LINEAR
            if (creative.getLinear() != null) {

                // CLICK TRACKING
                if (!linearClickUrlsAdded && CollectionUtils.isNotEmpty(clickUrls)) {
                    VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks videoClicks = creative.getLinear().getVideoClicks();
                    if (videoClicks == null && createMissingElements) {
                        videoClicks = new VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks();
                        creative.getLinear().setVideoClicks(videoClicks);
                    }
                    if (videoClicks != null) {
                        for (String clickUrl : clickUrls) {
                            List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> trackingsList = videoClicks.getClickTracking();
                            VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking tracking = new VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking();
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
                                Tracking tracking = new Tracking();
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

                // EVENTS                            
                if (!nonLinearTracksAdded && MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                    TrackingEventsType eventsType = creative.getNonLinearAds().getTrackingEvents();
                    if (eventsType == null && createMissingElements) {
                        creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsType());
                    }
                    if (eventsType != null) {
                        List<TrackingEventsType.Tracking> trackingList = eventsType.getTracking();
                        for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
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
                        nonLinearTracksAdded = true;
                    }
                }
            }
        }
        return new InjectTrackersResult(linearTracksAdded, linearClickUrlsAdded, nonLinearTracksAdded, nonLinearClickUrlsAdded);
    }

    private void doInjectTrackers(VAST vast, VastTrackers trackers) {

        List<String> errorUrls = trackers.getErrorUrls();
        String errorUrl = CollectionUtils.isNotEmpty(errorUrls) ? errorUrls.get(0).trim() : null;

        List<String> imprUrls = trackers.getImpressionUrls();

        Map<String, Set<String>> trackingUrls = trackers.getLinearTrackingUrls();
        Set<String> clickUrls = trackers.getLinearClickUrls();

        Map<String, Set<String>> trackingUrlsNonLinear = trackers.getNonLinearTrackingUrls();
        Set<String> clickUrlsNonLinear = trackers.getNonLinearClickUrls();

        List<Ad> ads = vast.getAd();

        for (Ad ad : ads) {

            Ad.Wrapper wrapper = ad.getWrapper();
            if (wrapper != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    String error = wrapper.getError();
                    if (StringUtils.isBlank(error) || VastOption.REPLACE_ERROR_URL) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error.trim());
                    }
                    wrapper.setError(error);
                }

                // impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    Set<String> newImprs = new LinkedHashSet<>();

                    if (wrapper.getImpression() != null) {
                        newImprs.addAll(wrapper.getImpression());
                    }
                    newImprs.addAll(imprUrls);
                    wrapper.setImpression(new ArrayList<>(newImprs));
                }

                if (!trackingUrls.isEmpty() || !clickUrls.isEmpty() || !trackingUrlsNonLinear.isEmpty() || !clickUrlsNonLinear.isEmpty()) {
                    VAST.Ad.Wrapper.Creatives creatives = wrapper.getCreatives();
                    if (creatives == null) {
                        creatives = new VAST.Ad.Wrapper.Creatives();
                        wrapper.setCreatives(creatives);
                    }
                    if (CollectionUtils.isEmpty(creatives.getCreative())) {
                        creatives.setCreative(Collections.singletonList(new VAST.Ad.Wrapper.Creatives.Creative()));
                    }
                    Ad.Wrapper.Creatives.Creative creative = creatives.getCreative().get(0);
                    if (creative.getLinear() == null) {
                        creative.setLinear(new VAST.Ad.Wrapper.Creatives.Creative.Linear());

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

            Ad.InLine inLine = ad.getInLine();
            if (inLine != null) {

                // error
                if (StringUtils.isNotEmpty(errorUrl)) {
                    String error = inLine.getError();
                    if (StringUtils.isBlank(error)) {
                        error = errorUrl;
                    } else {
                        error = errorUrl + "&errorUrl=" + encodeUrl(error.trim());
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
                                for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
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
        return VastVersion.VAST2;
    }
}
