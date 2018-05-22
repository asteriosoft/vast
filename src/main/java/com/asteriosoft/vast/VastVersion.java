package com.asteriosoft.vast;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Note: aspect values taken from rmp-rawlog lib
 */
public enum VastVersion {

    VAST1(1, "1.0", 6, 1, 4),
    VAST2(2, "2.0", 7, 2, 5),
    VAST3(3, "3.0", 11, 3, 6),
    VAST4(4, "4.0", 13, 7, 8);

    private static final Map<Integer, VastVersion> BY_VERSION;
    private static final Map<Integer, VastVersion> BY_OPEN_RTB_PROTOCOL;

    static {
        Map<Integer, VastVersion> byVersionMap = new HashMap<>();
        Map<Integer, VastVersion> byOpenRtbProtocolMap = new HashMap<>();
        for (VastVersion vastVersion : values()) {
            byVersionMap.put(vastVersion.version, vastVersion);
            byOpenRtbProtocolMap.put(vastVersion.openRtbProtocol, vastVersion);
            byOpenRtbProtocolMap.put(vastVersion.openRtbProtocolWrapper, vastVersion);
        }
        BY_VERSION = Collections.unmodifiableMap(byVersionMap);
        BY_OPEN_RTB_PROTOCOL = Collections.unmodifiableMap(byOpenRtbProtocolMap);
    }

    public final int version;
    public final String versionStr;
    public final int viewAspect;
    public final int openRtbProtocol;
    public final int openRtbProtocolWrapper;

    VastVersion(int version, String versionStr, int viewAspect, int openRtbProtocol, int openRtbProtocolWrapper) {
        this.version = version;
        this.versionStr = versionStr;
        this.viewAspect = viewAspect;
        this.openRtbProtocol = openRtbProtocol;
        this.openRtbProtocolWrapper = openRtbProtocolWrapper;
    }

    public static VastVersion byVersion(int version) {
        return BY_VERSION.get(version);
    }

    public static VastVersion byOpenRtbProtocol(Integer id) {
        return id == null ? null : BY_OPEN_RTB_PROTOCOL.get(id);
    }

    public static VastVersion getVastVersion(int[] protocols) {
        if (ArrayUtils.isEmpty(protocols)) {
            return null;
        }
        VastVersion best = null;
        for (int protocol : protocols) {
            VastVersion version = VastVersion.byOpenRtbProtocol(protocol);
            if (version != null) {
                if (best == null || version.version > best.version) {
                    best = version;
                }
            }
        }
        return best;
    }

    @Override
    public String toString() {
        return Integer.toString(version);
    }

}
