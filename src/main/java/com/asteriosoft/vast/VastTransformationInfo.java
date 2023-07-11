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
