package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class VastUtils {

    private static final Vast2Helper vast2Helper = new Vast2Helper();
    private static final Vast3Helper vast3Helper = new Vast3Helper();
    private static final Vast4Helper vast4Helper = new Vast4Helper();

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
        } else if (adCode.trim().contains("<VAST")) {
            return true;
        }

        return false;
    }

    public static VastVersion parseVastVersion(String vast) {
        if (vast.contains("<VideoAdServingTemplate")) {
            return VastVersion.VAST1;
        }
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
            double v = Double.parseDouble(version);
            if (Objects.equals(v, 2.0)) {
                return VastVersion.VAST2;
            } else if (Objects.equals(v, 3.0)) {
                return VastVersion.VAST3;
            } else if (Objects.equals(v, 4.0)) {
                return VastVersion.VAST4;
            } else {
                return VastVersion.VAST2;
            }
        } catch (Exception e) {
            return VastVersion.VAST2;
        }
    }

    static BaseVastHelper getVastHelper(String vast) {
        BaseVastHelper helper = null;
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
}
