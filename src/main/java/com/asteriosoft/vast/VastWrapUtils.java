package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public final class VastWrapUtils extends VastUtils {

    public static String addVastTrackingUrls(String vastStr, Map<String, Object> vastdata) throws VastUnwrapException {
        try {
            vastStr = cutVast(vastStr);
            BaseVastHelper vastHelper = getVastHelper(vastStr);
            return vastHelper == null
                    ? StringUtils.EMPTY
                    : vastHelper.addVastTrackingUrls(vastStr, vastdata);
        } catch (Exception err) {
            throw new VastUnwrapException(err);
        }
    }
}
