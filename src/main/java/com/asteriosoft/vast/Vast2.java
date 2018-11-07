package com.asteriosoft.vast;

import com.asteriosoft.vast.v2.CompanionType;
import com.asteriosoft.vast.v2.ImpressionType;
import com.asteriosoft.vast.v2.NonLinearType;
import com.asteriosoft.vast.v2.ObjectFactory;
import com.asteriosoft.vast.v2.StaticResource;
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
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
        String errorUrl = CollectionUtils.isNotEmpty(errorUrls) ? errorUrls.get(0) : null;

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
                        error = errorUrl + "&errorUrl=" + encodeUrl(error);
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
    protected void convertAllResourcesToHTML(VAST vast, BiFunction<String, String, String> htmlContentApplier) {
        List<Ad> ads = vast.getAd();
        if (CollectionUtils.isEmpty(ads)) {
            return;
        }
        for (Ad ad : ads) {
            Ad.Wrapper wrapper = ad.getWrapper();
            if (wrapper != null) {
                Ad.Wrapper.Creatives creatives = wrapper.getCreatives();
                if (creatives == null) {
                    continue;
                }
                List<Ad.Wrapper.Creatives.Creative> creative = creatives.getCreative();
                if (CollectionUtils.isEmpty(creative)) {
                    continue;
                }
                for (Ad.Wrapper.Creatives.Creative cr : creative) {
                    Ad.Wrapper.Creatives.Creative.CompanionAds companionAds = cr.getCompanionAds();
                    if (companionAds != null) {
                        List<CompanionType> companions = companionAds.getCompanion();
                        if (CollectionUtils.isNotEmpty(companions)) {
                            for (CompanionType c : companions) {
                                convertAllToHTMLResource(c::getHTMLResource, c::getIFrameResource, c::getStaticResource,
                                        c::setHTMLResource, c::setIFrameResource, c::setStaticResource,
                                        c.getHeight(), c.getWidth(), htmlContentApplier, "companion");
                            }
                        }
                    }
                }
            }

            Ad.InLine inLine = ad.getInLine();
            if (inLine != null) {
                Ad.InLine.Creatives creatives = inLine.getCreatives();
                if (creatives == null) {
                    continue;
                }
                List<Ad.InLine.Creatives.Creative> creative = creatives.getCreative();
                if (CollectionUtils.isEmpty(creative)) {
                    continue;
                }
                for (Ad.InLine.Creatives.Creative cr : creative) {
                    Ad.InLine.Creatives.Creative.CompanionAds companionAds = cr.getCompanionAds();
                    if (companionAds != null) {
                        List<CompanionType> companions = companionAds.getCompanion();
                        if (CollectionUtils.isNotEmpty(companions)) {
                            for (CompanionType c : companions) {
                                convertAllToHTMLResource(c::getHTMLResource, c::getIFrameResource, c::getStaticResource,
                                        c::setHTMLResource, c::setIFrameResource, c::setStaticResource,
                                        c.getHeight(), c.getWidth(), htmlContentApplier, "companion");
                            }
                        }
                    }

                    Ad.InLine.Creatives.Creative.NonLinearAds nonLinearAds = cr.getNonLinearAds();
                    if (nonLinearAds != null) {
                        List<NonLinearType> nonLinear = nonLinearAds.getNonLinear();
                        if (CollectionUtils.isNotEmpty(nonLinear)) {
                            for (NonLinearType nl : nonLinear) {
                                convertAllToHTMLResource(nl::getHTMLResource, nl::getIFrameResource, nl::getStaticResource,
                                        nl::setHTMLResource, nl::setIFrameResource, nl::setStaticResource,
                                        nl.getHeight(), nl.getWidth(), htmlContentApplier, "nonlinear");
                            }
                        }
                    }
                }
            }
        }
    }

    private void convertAllToHTMLResource(Supplier<String> htmlGetter, Supplier<String> iFrameGetter, Supplier<StaticResource> staticGetter,
                                          Consumer<String> htmlSetter, Consumer<String> iframeSetter, Consumer<StaticResource> staticSetter,
                                          Number height, Number width, BiFunction<String, String, String> htmlContentApplier, String placeName) {

        String htmlResource = htmlGetter.get();
        String iFrameResource = iFrameGetter.get();
        StaticResource staticResource = staticGetter.get();

        if (htmlResource != null) {
            htmlSetter.accept(htmlContentApplier.apply(htmlResource, placeName + "-html"));
        }
        if (iFrameResource != null) {
            iframeSetter.accept(null);
            if (htmlGetter.get() == null) {
                htmlSetter.accept(htmlContentApplier.apply(createIframe(iFrameResource, height, width), placeName + "-iframe"));
            }
        }
        if (staticResource != null && staticResource.getCreativeType().contains("image")) {
            staticSetter.accept(null);
            if (htmlGetter.get() == null) {
                htmlSetter.accept(htmlContentApplier.apply(createImg(staticResource.getValue(), height, width), placeName + "-image"));
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
