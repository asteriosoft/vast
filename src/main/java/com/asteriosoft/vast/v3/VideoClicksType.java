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

package com.asteriosoft.vast.v3;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoClicks_type", propOrder = {
        "clickThrough",
        "clickTracking",
        "customClick"
})
public final class VideoClicksType {

    @XmlElement(name = "ClickThrough")
    protected VideoClicksType.ClickThrough clickThrough;
    @XmlElement(name = "ClickTracking")
    protected List<VideoClicksType.ClickTracking> clickTracking;
    @XmlElement(name = "CustomClick")
    protected List<VideoClicksType.CustomClick> customClick;

    public VideoClicksType.ClickThrough getClickThrough() {
        return clickThrough;
    }

    public void setClickThrough(VideoClicksType.ClickThrough clickThrough) {
        this.clickThrough = clickThrough;
    }

    public List<VideoClicksType.ClickTracking> getClickTracking() {
        if (clickTracking == null) {
            clickTracking = new ArrayList<>();
        }
        return clickTracking;
    }

    public void setClickTracking(List<ClickTracking> clickTracking) {
        this.clickTracking = clickTracking;
    }

    public List<VideoClicksType.CustomClick> getCustomClick() {
        if (customClick == null) {
            customClick = new ArrayList<>();
        }
        return customClick;
    }

    public void setCustomClick(List<CustomClick> customClick) {
        this.customClick = customClick;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static class ClickThrough {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String value;
        @XmlAttribute
        protected String id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static class ClickTracking {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String value;
        @XmlAttribute
        protected String id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static class CustomClick {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String value;
        @XmlAttribute
        protected String id;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

    }

}
