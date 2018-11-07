package com.asteriosoft.vast;

import java.util.function.BiFunction;

public class VastTransformationInfo {

    private String vastStr;
    private VastVersion version;
    private VastTrackers trackers;
    private BiFunction<String, String, String> htmlContentApplier;

    public VastTransformationInfo() {
    }

    public VastTransformationInfo(String vastStr, VastVersion version, VastTrackers trackers, BiFunction<String, String, String> htmlContentApplier) {
        this.vastStr = vastStr;
        this.version = version;
        this.trackers = trackers;
        this.htmlContentApplier = htmlContentApplier;
    }

    public VastTransformationInfo(String vastStr, VastVersion version, VastTrackers trackers) {
        this(vastStr, version, trackers, null);
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

    public BiFunction<String, String, String> getHtmlContentApplier() {
        return htmlContentApplier;
    }

    public void setHtmlContentApplier(BiFunction<String, String, String> htmlContentApplier) {
        this.htmlContentApplier = htmlContentApplier;
    }

}
