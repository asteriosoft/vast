/*
 *
 *  * Copyright 2023 Asteriosoft Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.asteriosoft.vast;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum VastVersion {

    VAST2(2, "2.0"),
    VAST3(3, "3.0"),
    VAST4(4, "4.0"),
    VAST41(41, "4.1"),
    VAST42(42, "4.2");

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
