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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class Vast4 extends BaseVast<VAST> {

    private final XmlMarshaling xmlMarshaling = new XmlMarshaling(ObjectFactory.class);

    private InjectTrackersResult injectWrapperTrackers(boolean createMissingElements, VAST.Ad.Wrapper.Creatives creatives, 
    		Set<String> clickUrls, Map<String, Set<String>> trackingUrls, Set<String> clickUrlsNonLinear, Map<String, Set<String>> trackingUrlsNonLinear) {
    	boolean linearClickUrlsAdded = false;
    	boolean linearTracksAdded = false;
    	boolean nonLinearClickUrlsAdded = false;    	
    	boolean nonLinearTracksAdded = false;
    	
        for (Ad.Wrapper.Creatives.Creative creative : creatives.getCreative()) {
            // LINEAR
            if (creative.getLinear() != null) {

                // CLICK TRACKING
                if (!linearClickUrlsAdded && CollectionUtils.isNotEmpty(clickUrls)) {
                    VideoClicksWrapperType videoClicks = creative.getLinear().getVideoClicks();
                    if (videoClicks == null) {
                        videoClicks = new VideoClicksWrapperType();
                        creative.getLinear().setVideoClicks(videoClicks);
                    }
                    if (videoClicks != null) {
                        for (String clickUrl : clickUrls) {
                            List<AnyURIType> trackingsList = videoClicks.getClickTracking();
                            AnyURIType tracking = new AnyURIType();
                            tracking.setValue(clickUrl);
                            trackingsList.add(tracking);
                        }
	                    linearClickUrlsAdded = true;	                    
                    }
                }

                // EVENTS
                if (!linearTracksAdded && MapUtils.isNotEmpty(trackingUrls)) {
                    TrackingEventsLinearType eventsType = creative.getLinear().getTrackingEvents();
                    if (eventsType == null && createMissingElements) {
                        creative.getLinear().setTrackingEvents(eventsType = new TrackingEventsLinearType());
                    }
                    if (eventsType != null) {
                      List<TrackingEventsLinearType.Tracking> trackingList = eventsType.getTracking();
                      for (Map.Entry<String, Set<String>> stringListEntry : trackingUrls.entrySet()) {
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
	              linearTracksAdded = true;
                    }
                }
            }

            // NON-LINEAR
            if (creative.getNonLinearAds() != null) {
                // CLICK
                if (CollectionUtils.isNotEmpty(clickUrlsNonLinear)) {
                    List<NonLinearWrapperType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                    if (CollectionUtils.isEmpty(nonLinearTypes) && createMissingElements) {
                    	nonLinearTypes = new ArrayList<>(1);
                    	nonLinearTypes.add(new NonLinearWrapperType());
                    	creative.getNonLinearAds().setNonLinear(nonLinearTypes);                    	
                    }
                    if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                        for (NonLinearWrapperType nonLinearWrapperType : nonLinearTypes) {
                            for (String clickUrl : clickUrlsNonLinear) {
                                if (StringUtils.isEmpty(clickUrl)) {
                                    continue;
                                }
                                nonLinearWrapperType.getNonLinearClickTracking().add(clickUrl);
                            }
                        }
                    }
                    nonLinearClickUrlsAdded = true;
                }

                // EVENTS
                if (MapUtils.isNotEmpty(trackingUrlsNonLinear)) {
                    TrackingEventsNonLinearType eventsType = creative.getNonLinearAds().getTrackingEvents();
                    if (eventsType == null && createMissingElements) {
                        creative.getNonLinearAds().setTrackingEvents(eventsType = new TrackingEventsNonLinearType());
                    }
                    if (eventsType != null) {
                      List<TrackingEventsNonLinearType.Tracking> trackingList = eventsType.getTracking();
                      for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
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

            Ad.Wrapper wrapper = ad.getWrapper();
            if (wrapper != null) {

                // error
                if (CollectionUtils.isNotEmpty(errorUrls)) {
                    List<String> errors = wrapper.getError();
                    if (CollectionUtils.isNotEmpty(errors)) {
                    	Set<String> newErrors = new LinkedHashSet<>(errors);
                    	newErrors.addAll(errorUrls);
                    	errors = new ArrayList<>(newErrors);
                    } else {
                        errors = errorUrls;
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

                if (!trackingUrls.isEmpty() || !clickUrls.isEmpty() || !trackingUrlsNonLinear.isEmpty() || !clickUrlsNonLinear.isEmpty()) {
                Ad.Wrapper.Creatives creatives = wrapper.getCreatives();
                    if (creatives == null) {
                    	creatives = new VAST.Ad.Wrapper.Creatives();
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

            Ad.InLine inLine = ad.getInLine();
            if (inLine != null) {

                // error
                if (CollectionUtils.isNotEmpty(errorUrls)) {
                    List<String> errors = inLine.getError();
                    if (CollectionUtils.isNotEmpty(errors)) {
                    	Set<String> newErrors = new LinkedHashSet<>(errors);
                    	newErrors.addAll(errorUrls);
                    	errors = new ArrayList<>(newErrors);
                    } else {
                        errors = errorUrls;
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
                                for (Map.Entry<String, Set<String>> stringListEntry : trackingUrls.entrySet()) {
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
                            // CLICK TRACKING
                            if (CollectionUtils.isNotEmpty(clickUrlsNonLinear)) {
                                List<NonLinearType> nonLinearTypes = creative.getNonLinearAds().getNonLinear();
                                if (CollectionUtils.isNotEmpty(nonLinearTypes)) {
                                    for (NonLinearType nonLinearType : nonLinearTypes) {
                                        List<AnyURIType> clickTracking = nonLinearType.getNonLinearClickTracking();
                                        for (String clickUrl : clickUrlsNonLinear) {
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
                                for (Map.Entry<String, Set<String>> stringListEntry : trackingUrlsNonLinear.entrySet()) {
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
