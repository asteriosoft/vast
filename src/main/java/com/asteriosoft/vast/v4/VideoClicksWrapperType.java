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

package com.asteriosoft.vast.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoClicksWrapper_type", propOrder = {
        "clickTracking",
        "customClick"
})
public final class VideoClicksWrapperType {

    @XmlElement(name = "ClickTracking")
    protected List<AnyURIType> clickTracking;
    @XmlElement(name = "CustomClick")
    protected List<AnyURIType> customClick;

    public List<AnyURIType> getClickTracking() {
        if (clickTracking == null) {
            clickTracking = new ArrayList<>();
        }
        return clickTracking;
    }

    public void setClickTracking(List<AnyURIType> clickTracking) {
        this.clickTracking = clickTracking;
    }

    public List<AnyURIType> getCustomClick() {
        if (customClick == null) {
            customClick = new ArrayList<>();
        }
        return customClick;
    }

    public void setCustomClick(List<AnyURIType> customClick) {
        this.customClick = customClick;
    }
}
