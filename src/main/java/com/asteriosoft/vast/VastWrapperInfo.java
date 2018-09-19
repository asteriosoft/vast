package com.asteriosoft.vast;

import java.util.Map;

public final class VastWrapperInfo {

    public static final VastWrapperInfo NO_ADS = new VastWrapperInfo(NonUnwrappableReason.NoAds);
    public static final VastWrapperInfo MULTIPLE_ADS = new VastWrapperInfo(NonUnwrappableReason.MultipleAds);
    public static final VastWrapperInfo NOT_WRAPPER = new VastWrapperInfo(NonUnwrappableReason.NotWrapper);
    public static final VastWrapperInfo EMPTY_VASTTAGURI = new VastWrapperInfo(NonUnwrappableReason.EmptyVastTagURI);
    public static final VastWrapperInfo UNSUPPORTED_VERSION = new VastWrapperInfo(NonUnwrappableReason.UnsupportedVastVersion);

    private String vastTagURI;
    private NonUnwrappableReason nonUnwrappableReason;
    private VastTrackers trackers;

    public VastWrapperInfo(String vastTagURI, VastTrackers trackers) {
        this.vastTagURI = vastTagURI;
        this.trackers = trackers;
    }

    public VastWrapperInfo(NonUnwrappableReason nonUnwrapableReason) {
        this.nonUnwrappableReason = nonUnwrapableReason;
    }

    public String getVastTagURI() {
        return vastTagURI;
    }

    public VastTrackers getTrackers() {
        return trackers;
    }

    public NonUnwrappableReason getNonUnwrappableReason() {
        return nonUnwrappableReason;
    }

	@Override
	public String toString() {
		return "VastWrapperInfo [vastTagURI=" + vastTagURI + ", nonUnwrappableReason=" + nonUnwrappableReason + ", trackers=" + trackers + "]";
	}
}
