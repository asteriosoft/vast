package com.asteriosoft.vast;

public class VastUnwrapUtils extends VastUtils {

    public static Object parseVast(String vast) throws VastUnwrapException {
        try {
            vast = cutVast(vast);
            BaseVastHelper vastHelper = getVastHelper(vast);
            return vastHelper == null
                    ? null
                    : vastHelper.unmarshal(vast);
        } catch (Exception err) {
            throw new VastUnwrapException(err);
        }
    }
}
