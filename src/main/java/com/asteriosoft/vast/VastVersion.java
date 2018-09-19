package com.asteriosoft.vast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum VastVersion {

    VAST2(2, "2.0"),
    VAST3(3, "3.0"),
    VAST4(4, "4.0");

    private static final Map<Integer, VastVersion> NUMBER_TO_VERSION;

    static {
        Map<Integer, VastVersion> byVersionMap = new HashMap<>(values().length);
        for (VastVersion vastVersion : values()) {
            byVersionMap.put(vastVersion.version, vastVersion);
        }
        NUMBER_TO_VERSION = Collections.unmodifiableMap(byVersionMap);
    }

    public final int version;
    public final String string;

    VastVersion(int version, String string) {
        this.version = version;
        this.string = string;
    }

    public static VastVersion byVersion(int version) {
        return NUMBER_TO_VERSION.get(version);
    }

    @Override
    public String toString() {
        return string;
    }

}
