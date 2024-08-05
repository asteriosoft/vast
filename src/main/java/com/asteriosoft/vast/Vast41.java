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

import com.asteriosoft.vast.v41.ViewableImpressionType;
import com.asteriosoft.vast.v41.CreativeInlineType;
import com.asteriosoft.vast.v41.CreativeWrapperType;
import com.asteriosoft.vast.v41.ImpressionType;
import com.asteriosoft.vast.v41.InlineType;
import com.asteriosoft.vast.v41.NonLinearAdInlineType;
import com.asteriosoft.vast.v41.ObjectFactory;
import com.asteriosoft.vast.v41.TrackingEventsType;
import com.asteriosoft.vast.v41.VAST;
import com.asteriosoft.vast.v41.VAST.Ad;
import com.asteriosoft.vast.v41.VideoClicksBaseType;
import com.asteriosoft.vast.v41.VideoClicksInlineType;
import com.asteriosoft.vast.v41.WrapperType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Vast41 extends BaseVast<VAST> {

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
                    VideoClicksBaseType videoClicks = creative.getLinear().getVideoClicks();
                    if (videoClicks == null) {
                        videoClicks = new VideoClicksBaseType();
                        creative.getLinear().setVideoClicks(videoClicks);
                    }
                    for (String clickUrl : clickUrls) {
                        List<VideoClicksBaseType.ClickTracking> trackingsList = videoClicks.getClickTracking();
                        VideoClicksBaseType.ClickTracking tracking = new VideoClicksBaseType.ClickTracking();
                        tracking.setValue(clickUrl);
                        trackingsList.add(tracking);
                    }
                    linearClickUrlsAdded = true;
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

        List<String> viewImpressionUrls = trackers.getViewImprUrls();
        List<String> unviewImpressionUrls = trackers.getUnviewImprUrls();
        List<String> viewUndeterminedUrls = trackers.getViewUndeterminedUrls();

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

                // viewable impression
                if (CollectionUtils.isNotEmpty(viewImpressionUrls)
                        || CollectionUtils.isNotEmpty(unviewImpressionUrls)
                        || CollectionUtils.isNotEmpty(viewUndeterminedUrls)) {
                    ViewableImpressionType existedViewImpression = wrapper.getViewableImpression();
                    ViewableImpressionType viewImpression = addInViewableImpression(existedViewImpression, viewImpressionUrls, unviewImpressionUrls, viewUndeterminedUrls);
                    wrapper.setViewableImpression(viewImpression);
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

                //viewable impression
                if (CollectionUtils.isNotEmpty(imprUrls)) {
                    ViewableImpressionType existedViewImpression = inLine.getViewableImpression();
                    ViewableImpressionType viewImpression = addInViewableImpression(
                            existedViewImpression, viewImpressionUrls, unviewImpressionUrls, viewUndeterminedUrls);
                    inLine.setViewableImpression(viewImpression);
                }

                InlineType.Creatives creatives = inLine.getCreatives();

                if (creatives != null) {
                    List<CreativeInlineType> creativeAds = creatives.getCreative();

                    for (CreativeInlineType creative : creativeAds) {
                        // LINEAR
                        if (creative.getLinear() != null) {
                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrls)) {
                                VideoClicksInlineType videoClicks = creative.getLinear().getVideoClicks();
                                if (videoClicks == null) {
                                    videoClicks = new VideoClicksInlineType();
                                    creative.getLinear().setVideoClicks(videoClicks);
                                }

                                List<VideoClicksBaseType.ClickTracking> trackingsList = videoClicks.getClickTracking();
                                for (String clickUrl : clickUrls) {
                                    if (StringUtils.isEmpty(clickUrl)) {
                                        continue;
                                    }
                                    VideoClicksBaseType.ClickTracking tracking = new VideoClicksBaseType.ClickTracking();
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
    }private ViewableImpressionType addInViewableImpression(ViewableImpressionType existedViewImpression,
                                                            List<String> viewImpressionUrls,
                                                            List<String> unviewImpressionUrls,
                                                            List<String> viewUndeterminedUrls) {
        List<String> viewable = existedViewImpression != null && CollectionUtils.isNotEmpty(existedViewImpression.getViewable())
                ? new ArrayList<>(existedViewImpression.getViewable())
                : new ArrayList<>();
        viewable.addAll(viewImpressionUrls);

        List<String> notViewable = existedViewImpression != null && CollectionUtils.isNotEmpty(existedViewImpression.getNotViewable())
                ? new ArrayList<>(existedViewImpression.getNotViewable())
                : new ArrayList<>();
        notViewable.addAll(unviewImpressionUrls);

        List<String> viewUndetermined = existedViewImpression != null && CollectionUtils.isNotEmpty(existedViewImpression.getViewUndetermined())
                ? new ArrayList<>(existedViewImpression.getViewUndetermined())
                : new ArrayList<>();
        viewUndetermined.addAll(viewUndeterminedUrls);

        ViewableImpressionType viewImpression = new ViewableImpressionType();
        viewImpression.setViewable(viewable);
        viewImpression.setNotViewable(notViewable);
        viewImpression.setViewUndetermined(viewUndetermined);
        return viewImpression;
    }
}
