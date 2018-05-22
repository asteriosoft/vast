package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class VastWrapUtils {

    private static final Vast2Helper vast2Helper = new Vast2Helper();
    private static final Vast3Helper vast3Helper = new Vast3Helper();
    private static final Vast4Helper vast4Helper = new Vast4Helper();

    private VastWrapUtils() {
    }

    private static String cutVast(String adCode) {
        adCode = adCode.trim();

        if (adCode.endsWith("</VAST>")) {
            return adCode;
        }

        int z = adCode.indexOf("</VAST>");
        if (z > 0) {
            adCode = adCode.substring(0, z + "</VAST>".length());
        }
        return adCode;
    }

    private static VastVersion parseVastVersion(String vast) {
        if (vast.contains("version=\"2.0\">")) {
            return VastVersion.VAST2;
        } else if (vast.contains("version=\"3.0\">")) {
            return VastVersion.VAST3;
        } else if (vast.contains("version=\"4.0\">")) {
            return VastVersion.VAST4;
        } else if (vast.contains("<VideoAdServingTemplate")) {
            return VastVersion.VAST1;
        } else {
            return VastVersion.VAST2;
        }
    }

    public static String addVastTrackingUrls(String vastStr, Map<String, Object> vastdata) throws VastUnwrapException {
        try {
            vastStr = cutVast(vastStr);

            switch (parseVastVersion(vastStr)) {
                case VAST2:
                    return vast2Helper.addVastTrackingUrls(vastStr, vastdata);
                case VAST3:
                    return vast3Helper.addVastTrackingUrls(vastStr, vastdata);
                case VAST4:
                    return vast4Helper.addVastTrackingUrls(vastStr, vastdata);
                case VAST1:
                    return StringUtils.EMPTY;
            }
            return StringUtils.EMPTY;
        } catch (Exception err) {
            throw new VastUnwrapException(err);
        }
    }

    public static boolean isVast(String adCode) {
        if (StringUtils.isEmpty(adCode)) {
            return false;
        } else if (adCode.trim().contains("<VAST")) {
            return true;
        }

        return false;
    }
}
