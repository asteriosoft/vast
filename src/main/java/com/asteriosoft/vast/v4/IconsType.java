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

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Icons_type", propOrder = "icon")
public final class IconsType {

    @XmlElement(name = "Icon", required = true)
    protected List<IconsType.Icon> icon;

    public List<IconsType.Icon> getIcon() {
        if (icon == null) {
            icon = new ArrayList<>();
        }
        return icon;
    }

    public void setIcon(List<Icon> icon) {
        this.icon = icon;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "staticResource",
            "iFrameResource",
            "htmlResource",
            "iconClicks",
            "iconViewTracking"
    })
    public static class Icon {

        @XmlElement(name = "StaticResource")
        protected StaticResourceType staticResource;
        @XmlElement(name = "IFrameResource")
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String iFrameResource;
        @XmlElement(name = "HTMLResource")
        protected HTMLResourceType htmlResource;
        @XmlElement(name = "IconClicks")
        protected IconsType.Icon.IconClicks iconClicks;
        @XmlElement(name = "IconViewTracking")
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected List<String> iconViewTracking;
        @XmlAttribute(required = true)
        protected String program;
        @XmlAttribute(required = true)
        protected BigInteger width;
        @XmlAttribute(required = true)
        protected BigInteger height;
        @XmlAttribute(required = true)
        protected String xPosition;
        @XmlAttribute(required = true)
        protected String yPosition;
        @XmlAttribute
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar offset;
        @XmlAttribute
        @XmlSchemaType(name = "time")
        protected XMLGregorianCalendar duration;
        @XmlAttribute
        protected String apiFramework;
        @XmlAttribute
        protected BigDecimal pxratio;

        public StaticResourceType getStaticResource() {
            return staticResource;
        }

        public void setStaticResource(StaticResourceType staticResource) {
            this.staticResource = staticResource;
        }

        public String getIFrameResource() {
            return iFrameResource;
        }

        public void setIFrameResource(String iFrameResource) {
            this.iFrameResource = iFrameResource;
        }

        public HTMLResourceType getHTMLResource() {
            return htmlResource;
        }

        public void setHTMLResource(HTMLResourceType htmlResource) {
            this.htmlResource = htmlResource;
        }

        public IconsType.Icon.IconClicks getIconClicks() {
            return iconClicks;
        }

        public void setIconClicks(IconsType.Icon.IconClicks iconClicks) {
            this.iconClicks = iconClicks;
        }

        public List<String> getIconViewTracking() {
            if (iconViewTracking == null) {
                iconViewTracking = new ArrayList<>();
            }
            return iconViewTracking;
        }

        public void setIconViewTracking(List<String> iconViewTracking) {
            this.iconViewTracking = iconViewTracking;
        }

        public String getProgram() {
            return program;
        }

        public void setProgram(String program) {
            this.program = program;
        }

        public BigInteger getWidth() {
            return width;
        }

        public void setWidth(BigInteger width) {
            this.width = width;
        }

        public BigInteger getHeight() {
            return height;
        }

        public void setHeight(BigInteger height) {
            this.height = height;
        }

        public String getXPosition() {
            return xPosition;
        }

        public void setXPosition(String xPosition) {
            this.xPosition = xPosition;
        }

        public String getYPosition() {
            return yPosition;
        }

        public void setYPosition(String yPosition) {
            this.yPosition = yPosition;
        }

        public XMLGregorianCalendar getOffset() {
            return offset;
        }

        public void setOffset(XMLGregorianCalendar offset) {
            this.offset = offset;
        }

        public XMLGregorianCalendar getDuration() {
            return duration;
        }

        public void setDuration(XMLGregorianCalendar duration) {
            this.duration = duration;
        }

        public String getApiFramework() {
            return apiFramework;
        }

        public void setApiFramework(String apiFramework) {
            this.apiFramework = apiFramework;
        }

        public BigDecimal getPxratio() {
            return pxratio;
        }

        public void setPxratio(BigDecimal pxratio) {
            this.pxratio = pxratio;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "iconClickThrough",
                "iconClickTracking"
        })
        public static class IconClicks {

            @XmlElement(name = "IconClickThrough")
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String iconClickThrough;
            @XmlElement(name = "IconClickTracking")
            protected List<AnyURIType> iconClickTracking;

            public String getIconClickThrough() {
                return iconClickThrough;
            }

            public void setIconClickThrough(String iconClickThrough) {
                this.iconClickThrough = iconClickThrough;
            }

            public List<AnyURIType> getIconClickTracking() {
                if (iconClickTracking == null) {
                    iconClickTracking = new ArrayList<>();
                }
                return iconClickTracking;
            }

            public void setIconClickTracking(List<AnyURIType> iconClickTracking) {
                this.iconClickTracking = iconClickTracking;
            }
        }

    }

}
