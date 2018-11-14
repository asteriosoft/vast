package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import com.asteriosoft.vast.v2.TrackingEventsType;
import com.asteriosoft.vast.v2.VAST;
import com.asteriosoft.vast.v3.NonLinearWrapperType;
import com.asteriosoft.vast.v4.AnyURIType;
import com.asteriosoft.vast.v4.TrackingEventsLinearType;
import com.asteriosoft.vast.v4.TrackingEventsNonLinearType;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static boolean isVast(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        } else if (str.contains("<VAST")) {
            return true;
        }

        return false;
    }

    public static boolean isVastAd(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        } else if (str.contains("<VAST") && str.contains("<Ad")) {
            return true;
        }
        return false;
    }

    public static boolean isVastWrapper(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        } else if (str.contains("<VAST") && str.contains("<Wrapper")) {
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

    static BaseVast<?> getVastHelper(VastVersion version) {
        BaseVast<?> helper = null;
        try {
            switch (version) {
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
        return addVastTrackingUrls(vastStr, parseVastVersion(vastStr), trackers);
    }

    public static String addVastTrackingUrls(String vastStr, VastVersion version, VastTrackers trackers) throws VastUtilsException {
        return transform(new VastTransformationInfo(vastStr, version, trackers, null));
    }

    public static String transform(VastTransformationInfo info) throws VastUtilsException{
        try {
           String vast = cutVast(info.getVastStr());
            VastVersion version = info.getVersion() == null ? parseVastVersion(vast) : info.getVersion();
            BaseVast<?> vastHelper = getVastHelper(version);
            if (vastHelper == null) {
                return null;
            }
            return vastHelper.transform(vast, info);
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
	        vast = fixVast(vast);
	        
	        BaseVast<?> vastHelper = getVastHelper(parseVastVersion(vast));
	        return vastHelper == null
	                ? null
	                : vastHelper.unmarshal(vast);
	    } catch (Exception err) {
	        throw new VastUtilsException(err);
	    }
	}

	static String fixVast(String vast) {
		int res1 = vast.indexOf("<HTMLResource>");
		if (res1 > 0) {
			int res2 = vast.indexOf("</HTMLResource>", res1);
			if (res2 > 0) {
				int cdataIndex = vast.indexOf("<![CDATA[", res1);
				if (cdataIndex < 0) {
					String part1 = vast.substring(0, res1 + "<HTMLResource>".length());
					String part2 = vast.substring(res1 + "<HTMLResource>".length(), res2);
					String part3 = vast.substring(res2, vast.length());
					StringBuilder sb = new StringBuilder(vast.length() + 12);
					sb
					.append(part1)
					.append("<![CDATA[")
					.append(part2)
					.append("]]>")
					.append(part3);
					return sb.toString();
				}
			}
		}
		return vast;
	}
	
	public static VastTrackers getWrapperTrackers(Object vast) {

        final Map<String, Set<String>> linearTrackingUrls = new LinkedHashMap<>(3);
        final Set<String> linearClickUrls = new LinkedHashSet<>(3);
        final Map<String, Set<String>> nonLinearTrackingUrls = new LinkedHashMap<>(3);
        final Set<String> nonLinearClickUrls = new LinkedHashSet<>(3);

	    if (vast instanceof com.asteriosoft.vast.v2.VAST) {
	        List<com.asteriosoft.vast.v2.VAST.Ad> adList = ((com.asteriosoft.vast.v2.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return null;
	        }
	        if (adList.size() > 1) {
	        	return null;
	        }
	        com.asteriosoft.vast.v2.VAST.Ad vast2Ad = adList.get(0);
	        VAST.Ad.Wrapper wrapper = vast2Ad.getWrapper();
	        if (wrapper == null) {
	            return null;
	        }
	        //
            List<String> impressions = wrapper.getImpression();
            List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());

            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
            	for(VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
            		if (cr.getLinear() != null) {
            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
		        			List<TrackingEventsType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
		        			if (CollectionUtils.isNotEmpty(trackings)) {
		        				for (TrackingEventsType.Tracking tracking : trackings) {
		        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
								}
		        			}
            			}
            			if (cr.getLinear().getVideoClicks() != null) {
            				List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
            					for (VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking clickTracking : clickTrackings) {
            						linearClickUrls.add(clickTracking.getValue());
								}
            				}
            			}
            		}
            		if (cr.getNonLinearAds() != null && cr.getNonLinearAds().getTrackingEvents() != null) {
            			List<TrackingEventsType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
            			if (CollectionUtils.isNotEmpty(trackings)) {
	            			for (TrackingEventsType.Tracking tracking : trackings) {
	            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
	            			}
            			}
            		}
            	}
            }

            return new VastTrackers(impressions, errors, linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls, nonLinearClickUrls);
	    } else if (vast instanceof com.asteriosoft.vast.v3.VAST) {
	        List<com.asteriosoft.vast.v3.VAST.Ad> adList = ((com.asteriosoft.vast.v3.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return null;
	        }
	        if (adList.size() > 1) {
	        	return null;
	        }
	        com.asteriosoft.vast.v3.VAST.Ad vast3Ad = adList.get(0);
	        com.asteriosoft.vast.v3.VAST.Ad.Wrapper wrapper = vast3Ad.getWrapper();
	        if (wrapper == null) {
	        	return null;
	        }
	        //
            List<String> impressions = wrapper.getImpression();
            List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());

            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
            	for(com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
            		if (cr.getLinear() != null) {
            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
		        			List<com.asteriosoft.vast.v3.TrackingEventsType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
		        			if (CollectionUtils.isNotEmpty(trackings)) {
		        				for (com.asteriosoft.vast.v3.TrackingEventsType.Tracking tracking : trackings) {
		        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
								}
		        			}
            			}
            			if (cr.getLinear().getVideoClicks() != null) {
            				List<com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
            					for (com.asteriosoft.vast.v3.VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking clickTracking : clickTrackings) {
            						linearClickUrls.add(clickTracking.getValue());
								}
            				}
            			}
            		}
            		if (cr.getNonLinearAds() != null) {
            			if (cr.getNonLinearAds().getTrackingEvents() != null) {
	            			List<com.asteriosoft.vast.v3.TrackingEventsType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
	            			if (CollectionUtils.isNotEmpty(trackings)) {
		            			for (com.asteriosoft.vast.v3.TrackingEventsType.Tracking tracking : trackings) {
		            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
		            			}
	            			}
            			}

            			if (cr.getNonLinearAds().getNonLinear() != null) {
            				for(NonLinearWrapperType nl: cr.getNonLinearAds().getNonLinear()) {
            					List<String> cls = nl.getNonLinearClickTracking();
            					if (CollectionUtils.isNotEmpty(cls)) {
            						nonLinearClickUrls.addAll(cls);
            					}
            				}
            			}
            		}
            	}
            }

            return new VastTrackers(impressions, errors, linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls, nonLinearClickUrls);
	    } else if (vast instanceof com.asteriosoft.vast.v4.VAST) {
	        List<com.asteriosoft.vast.v4.VAST.Ad> adList = ((com.asteriosoft.vast.v4.VAST) vast).getAd();
	        if (CollectionUtils.isEmpty(adList)) {
	            return null;
	        }
	        if (adList.size() > 1) {
	        	return null;
	        }
	        com.asteriosoft.vast.v4.VAST.Ad vast4Ad = adList.get(0);
	        com.asteriosoft.vast.v4.VAST.Ad.Wrapper wrapper = vast4Ad.getWrapper();
	        if (wrapper == null) {
	        	return null;
	        }
	        //
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

            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
            	for(com.asteriosoft.vast.v4.VAST.Ad.Wrapper.Creatives.Creative cr: wrapper.getCreatives().getCreative()) {
            		if (cr.getLinear() != null) {
            			if (cr.getLinear().getTrackingEvents() != null && cr.getLinear().getTrackingEvents() != null) {
		        			List<TrackingEventsLinearType.Tracking> trackings = cr.getLinear().getTrackingEvents().getTracking();
		        			if (CollectionUtils.isNotEmpty(trackings)) {
		        				for (TrackingEventsLinearType.Tracking tracking : trackings) {
		        					linearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
								}
		        			}
            			}
            			if (cr.getLinear().getVideoClicks() != null) {
            				List<AnyURIType> clickTrackings = cr.getLinear().getVideoClicks().getClickTracking();
            				if (CollectionUtils.isNotEmpty(clickTrackings)) {
            					for (AnyURIType clickTracking : clickTrackings) {
            						linearClickUrls.add(clickTracking.getValue());
								}
            				}
            			}
            		}
            		if (cr.getNonLinearAds() != null) {
            			if (cr.getNonLinearAds().getTrackingEvents() != null) {
	            			List<TrackingEventsNonLinearType.Tracking> trackings = cr.getNonLinearAds().getTrackingEvents().getTracking();
	            			if (CollectionUtils.isNotEmpty(trackings)) {
		            			for (TrackingEventsNonLinearType.Tracking tracking : trackings) {
		            				nonLinearTrackingUrls.computeIfAbsent(tracking.getEvent(), t -> {return new LinkedHashSet<String>(1);}).add(tracking.getValue());
		            			}
	            			}
            			}
            			if (cr.getNonLinearAds().getNonLinear() != null) {
            				for(com.asteriosoft.vast.v4.NonLinearWrapperType nl: cr.getNonLinearAds().getNonLinear()) {
            					List<String> cls = nl.getNonLinearClickTracking();
            					if (CollectionUtils.isNotEmpty(cls)) {
            						nonLinearClickUrls.addAll(cls);
            					}
            				}
            			}
            		}
            	}
            }

            return new VastTrackers(impressions, wrapper.getError(), linearTrackingUrls, linearClickUrls, nonLinearTrackingUrls, nonLinearClickUrls);
	    } else {
	        return null;
	    }
	}
}
