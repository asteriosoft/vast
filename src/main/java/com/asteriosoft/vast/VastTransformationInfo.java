package com.asteriosoft.vast;

public class VastTransformationInfo {

    private String vastStr;
    private VastVersion version;
    private VastTrackers trackers;

    public VastTransformationInfo() {
    }

    public VastTransformationInfo(String vastStr, VastVersion version, VastTrackers trackers) {
        this.vastStr = vastStr;
        this.version = version;
        this.trackers = trackers;
    }

    public String getVastStr() {
        return vastStr;
    }

    public void setVastStr(String vastStr) {
        this.vastStr = vastStr;
    }

    public VastVersion getVersion() {
        return version;
    }

    public void setVersion(VastVersion version) {
        this.version = version;
    }

    public VastTrackers getTrackers() {
        return trackers;
    }

    public void setTrackers(VastTrackers trackers) {
        this.trackers = trackers;
    }

}
