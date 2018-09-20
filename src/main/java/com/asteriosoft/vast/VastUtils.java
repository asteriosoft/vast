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

    static BaseVast<?> getVastHelper(String vast) {
        BaseVast<?> helper = null;
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
            BaseVast<?> vastHelper = getVastHelper(vastStr);
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
	        BaseVast<?> vastHelper = getVastHelper(vast);
	        return vastHelper == null
	                ? null
	                : vastHelper.unmarshal(vast);
	    } catch (Exception err) {
	        throw new VastUtilsException(err);
	    }
	}
}
