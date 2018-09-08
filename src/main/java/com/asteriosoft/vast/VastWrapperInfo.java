package com.asteriosoft.vast;

import java.util.Map;

public class VastWrapperInfo {

    public static final VastWrapperInfo NO_ADS = new VastWrapperInfo(NonUnwrupableReason.NoAds);
    public static final VastWrapperInfo MULTIPLE_ADS = new VastWrapperInfo(NonUnwrupableReason.MultipleAds);
    public static final VastWrapperInfo NOT_WRAPPER = new VastWrapperInfo(NonUnwrupableReason.NotWrapper);
    public static final VastWrapperInfo CREATIVES = new VastWrapperInfo(NonUnwrupableReason.Creatives);
    public static final VastWrapperInfo EMPTY_VASTTAGURI = new VastWrapperInfo(NonUnwrupableReason.EmptyVastTagURI);
    public static final VastWrapperInfo UNSUPPORTED_VERSION = new VastWrapperInfo(NonUnwrupableReason.UnsupportedVastVersion);

    private String vastTagURI;
    private Map<String, Object> trackers;
    private NonUnwrupableReason nonUnwrapableReason;

    public VastWrapperInfo(String vastTagURI, Map<String, Object> trackers) {
        this.vastTagURI = vastTagURI;
        this.trackers = trackers;
    }

    public VastWrapperInfo(NonUnwrupableReason nonUnwrapableReason) {
        this.nonUnwrapableReason = nonUnwrapableReason;
    }

    public String getVastTagURI() {
        return vastTagURI;
    }

    public Map<String, Object> getTrackers() {
        return trackers;
    }

    public NonUnwrupableReason getNonUnwrapableReason() {
        return nonUnwrapableReason;
    }
}
