package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import com.asteriosoft.vast.v2.TrackingEventsType;
import com.asteriosoft.vast.v2.VAST;
import com.asteriosoft.vast.v4.AnyURIType;
import com.asteriosoft.vast.v4.TrackingEventsLinearType;
import com.asteriosoft.vast.v4.TrackingEventsNonLinearType;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class VastUtils {

    private static final Vast2 vast2Helper = new Vast2();
    private static final Vast3 vast3Helper = new Vast3();
    private static final Vast4 vast4Helper = new Vast4();

    static String cutVast(String adCode) {
        adCode = removeIabXmlns(adCode).trim();

        if (adCode.endsWith("</VAST>")) {
            return adCode;
        }

        int z = adCode.indexOf("</VAST>");
        if (z > 0) {
            adCode = adCode.substring(0, z + "</VAST>".length());
        }
        return adCode;
    }

    private static String removeIabXmlns(String vast) {
        String strToRemove = "xmlns=\"http://www.iab.com/VAST\"";
        int index = vast.indexOf(strToRemove);
        if (index > -1) {
            String firstPart = null;
            if (index > 0) {
                firstPart = vast.substring(0, index - 1);
            }
            String secondPart = vast.substring(index + strToRemove.length());
            vast = firstPart == null ? secondPart : firstPart + secondPart;
        }
        return vast;
    }

    public static boolean isVast(String adCode) {
        if (StringUtils.isEmpty(adCode)) {
            return false;
        } else if (adCode.contains("<VAST")) {
            return true;
        }

        return false;
    }

    public static VastVersion parseVastVersion(String vast) {
        int vastTagIndex = vast.indexOf("<VAST");
        if (vastTagIndex < 0) {
            return VastVersion.VAST2;
        }
        int versionIndex = vast.indexOf("version=\"", vastTagIndex);
        if (versionIndex < 0) {
            return VastVersion.VAST2;
        }

        int versionIndexStart = versionIndex + "version=\"".length();
        int versionIndexEnd = vast.indexOf("\"", versionIndexStart + 1);
        try {
            String version = vast.substring(versionIndexStart, versionIndexEnd);
            if (version.startsWith("2.")) {
                return VastVersion.VAST2;
            } else if (version.startsWith("3.")) {
                return VastVersion.VAST3;
            } else if (version.startsWith("4.")) {
                return VastVersion.VAST4;
            } else {
                return VastVersion.VAST2;
            }
        } catch (Exception e) {
            return VastVersion.VAST2;
        }
    }

    static VastHelper<?> getVastHelper(String vast) {
        VastHelper<?> helper = null;
        try {
            switch (parseVastVersion(vast)) {
                case VAST2:
                    helper = vast2Helper;
                    break;
                case VAST3:
                    helper = vast3Helper;
                    break;
                case VAST4:
                    helper = vast4Helper;
                    break;
            }
        } catch (Exception ignored) {
        }
        return helper;
    }
    
    public static String addVastTrackingUrls(String vastStr, VastTrackers trackers) throws VastUtilsException {
    	if (trackers == null || trackers.isEmpty()) {
    		return vastStr;
    	}
    	//
        try {
            vastStr = cutVast(vastStr);
            VastHelper<?> vastHelper = getVastHelper(vastStr);
            return vastHelper == null
                    ? StringUtils.EMPTY
                    : vastHelper.addVastTrackingUrls(vastStr, trackers);
        } catch (Exception err) {
            throw new VastUtilsException(err);
        }
    }
    
	public static String printVast(Object vast) throws VastUtilsException {
	    try {    	
	    	if (vast instanceof VAST) {
	    		return vast2Helper.marshal((VAST)vast);
	    	} else if (vast instanceof com.asteriosoft.vast.v3.VAST) {
	    		return vast3Helper.marshal((com.asteriosoft.vast.v3.VAST)vast);
	    	} else if (vast instanceof com.asteriosoft.vast.v4.VAST) {
	    		return vast4Helper.marshal((com.asteriosoft.vast.v4.VAST)vast);
	    	} else {
	    		return null;
	    	}
	    } catch (Exception err) {
	        throw new VastUtilsException(err);
	    }
	}
    
	public static Object parseVast(String vast) throws VastUtilsException {
	    try {
	        vast = cutVast(vast);
	        VastHelper<?> vastHelper = getVastHelper(vast);
	        return vastHelper == null
	                ? null
	                : vastHelper.unmarshal(vast);
	    } catch (Exception err) {
	        throw new VastUtilsException(err);
	    }
	}

	public static VastWrapperInfo getWrapperInfo(Object vast) {
	    if (vast instanceof com.asteriosoft.vast.v2.VAST) {
	        List<com.asteriosoft.vast.v2.VAST.Ad> adList = ((com.asteriosoft.vast.v2.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return VastWrapperInfo.NO_ADS;
	        }
	        if (adList.size() > 1) {
	            return VastWrapperInfo.MULTIPLE_ADS;
	        }
	        com.asteriosoft.vast.v2.VAST.Ad vast2Ad = adList.get(0);
	        VAST.Ad.Wrapper wrapper = vast2Ad.getWrapper();
	        if (wrapper == null) {
	            return VastWrapperInfo.NOT_WRAPPER;
	        }
	        //
	        String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
	        if (isUrl(vastAdTagURI)) {
	            List<String> impressions = wrapper.getImpression();
	            List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());
	            Map<String, List<String>> linearTrackingUrls = null;
	            Map<String, List<String>> nonLinearTrackingUrls = null;
	            List<String> linearClickUrls = null;
	            
	            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
	            	for(VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
	            		if (cr.getLinear() != null) {
	            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
			        			List<TrackingEventsType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
			        			if (CollectionUtils.isNotEmpty(trackings)) {
			        				linearTrackingUrls = new HashMap<>(trackings.size());
			        				for (TrackingEventsType.Tracking tracking : trackings) {
			        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
									}
			        			}
	            			}
	            			if (cr.getLinear().getVideoClicks() != null) {
	            				List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
	            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
	            					linearClickUrls = new ArrayList<>(clickTrackings.size());
	            					for (VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking clickTracking : clickTrackings) {
	            						linearClickUrls.add(clickTracking.getValue());
									}
	            				}
	            			}
	            		}
	            		if (cr.getNonLinearAds() != null && cr.getNonLinearAds().getTrackingEvents() != null) {
	            			List<TrackingEventsType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
	            			if (CollectionUtils.isNotEmpty(trackings)) {
	            				nonLinearTrackingUrls = new HashMap<>();
		            			for (TrackingEventsType.Tracking tracking : trackings) {
		            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
		            			}
	            			}
	            		}
	            	}
	            }
	
	            return new VastWrapperInfo(vastAdTagURI, new VastTrackers(impressions, errors, linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls));
	        } else {
	            return VastWrapperInfo.EMPTY_VASTTAGURI;
	        }
	    } else if (vast instanceof com.asteriosoft.vast.v3.VAST) {
	        List<com.asteriosoft.vast.v3.VAST.Ad> adList = ((com.asteriosoft.vast.v3.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return VastWrapperInfo.NO_ADS;
	        }
	        if (adList.size() > 1) {
	            return VastWrapperInfo.MULTIPLE_ADS;
	        }
	        com.asteriosoft.vast.v3.VAST.Ad vast3Ad = adList.get(0);
	        com.asteriosoft.vast.v3.VAST.Ad.Wrapper wrapper = vast3Ad.getWrapper();
	        if (wrapper == null) {
	            return VastWrapperInfo.NOT_WRAPPER;
	        }
	        //
	        String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
	        if (isUrl(vastAdTagURI)) {
	            List<String> impressions = wrapper.getImpression();
	            List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());
	            Map<String, List<String>> linearTrackingUrls = null;
	            Map<String, List<String>> nonLinearTrackingUrls = null;
	            List<String> linearClickUrls = null;
	            
	            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
	            	for(com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
	            		if (cr.getLinear() != null) {
	            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
			        			List<com.asteriosoft.vast.v3.TrackingEventsType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
			        			if (CollectionUtils.isNotEmpty(trackings)) {
			        				linearTrackingUrls = new HashMap<>(trackings.size());
			        				for (com.asteriosoft.vast.v3.TrackingEventsType.Tracking tracking : trackings) {
			        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
									}
			        			}
	            			}
	            			if (cr.getLinear().getVideoClicks() != null) {
	            				List<com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
	            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
	            					linearClickUrls = new ArrayList<>(clickTrackings.size());
	            					for (com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking clickTracking : clickTrackings) {
	            						linearClickUrls.add(clickTracking.getValue());
									}
	            				}
	            			}
	            		}
	            		if (cr.getNonLinearAds() != null && cr.getNonLinearAds().getTrackingEvents() != null) {
	            			List<com.asteriosoft.vast.v3.TrackingEventsType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
	            			if (CollectionUtils.isNotEmpty(trackings)) {
	            				nonLinearTrackingUrls = new HashMap<>();
		            			for (com.asteriosoft.vast.v3.TrackingEventsType.Tracking tracking : trackings) {
		            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
		            			}
	            			}
	            		}	            		
	            	}
	            }
	
	            return new VastWrapperInfo(vastAdTagURI, new VastTrackers(impressions, errors, linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls));
	        } else {
	            return VastWrapperInfo.EMPTY_VASTTAGURI;
	        }
	    } else if (vast instanceof com.asteriosoft.vast.v4.VAST) {
	        List<com.asteriosoft.vast.v4.VAST.Ad> adList = ((com.asteriosoft.vast.v4.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return VastWrapperInfo.NO_ADS;
	        }
	        if (adList.size() > 1) {
	            return VastWrapperInfo.MULTIPLE_ADS;
	        }
	        com.asteriosoft.vast.v4.VAST.Ad vast4Ad = adList.get(0);
	        com.asteriosoft.vast.v4.VAST.Ad.Wrapper wrapper = vast4Ad.getWrapper();
	        if (wrapper == null) {
	            return VastWrapperInfo.NOT_WRAPPER;
	        }
	        //
	        String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
	        if (isUrl(vastAdTagURI)) {
	
	            List<String> impressions = null;
	            List<AnyURIType> anyURITypes = wrapper.getImpression();
	            if (CollectionUtils.isNotEmpty(anyURITypes)) {
	                impressions = new ArrayList<>(anyURITypes.size());
	                for (AnyURIType anyURIType : anyURITypes) {
	                    String value = anyURIType.getValue();
	                    if (StringUtils.isNotBlank(value)) {
	                        impressions.add(value);
	                    }
	                }
	            }
	
	            Map<String, List<String>> linearTrackingUrls = null;
	            Map<String, List<String>> nonLinearTrackingUrls = null;	            
	            List<String> linearClickUrls = null;
	            
	            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
	            	for(com.asteriosoft.vast.v4.VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
	            		if (cr.getLinear() != null) {
	            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
			        			List<TrackingEventsLinearType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
			        			if (CollectionUtils.isNotEmpty(trackings)) {
			        				linearTrackingUrls = new HashMap<>(trackings.size());
			        				for (TrackingEventsLinearType.Tracking tracking : trackings) {
			        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
									}
			        			}
	            			}
	            			if (cr.getLinear().getVideoClicks() != null) {
	            				List<AnyURIType> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
	            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
	            					linearClickUrls = new ArrayList<>(clickTrackings.size());
	            					for (AnyURIType clickTracking : clickTrackings) {
	            						linearClickUrls.add(clickTracking.getValue());
									}
	            				}
	            			}
	            		}
	            		if (cr.getNonLinearAds() != null && cr.getNonLinearAds().getTrackingEvents() != null) {
	            			List<TrackingEventsNonLinearType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
	            			if (CollectionUtils.isNotEmpty(trackings)) {
	            				nonLinearTrackingUrls = new HashMap<>();
		            			for (TrackingEventsNonLinearType.Tracking tracking : trackings) {
		            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new ArrayList<String>(1);}).add(tracking.getValue());
		            			}
	            			}
	            		}	            		
	            	}
	            }
	
	            return new VastWrapperInfo(vastAdTagURI, new VastTrackers(impressions, wrapper.getError(), linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls));
	        } else {
	            return VastWrapperInfo.EMPTY_VASTTAGURI;
	        }
	    } else {
	        return VastWrapperInfo.UNSUPPORTED_VERSION;
	    }
	}

	private static boolean isUrl(String vastAdTagURI) {
	    return StringUtils.startsWithIgnoreCase(vastAdTagURI, "http");
	}    
}
