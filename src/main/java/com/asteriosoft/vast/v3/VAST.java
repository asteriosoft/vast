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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ad",
        "error"
})
@XmlRootElement(name = "VAST")
public final class VAST {

    @XmlElement(name = "Ad")
    protected List<VAST.Ad> ad;
    @XmlElement(name = "Error")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String error;
    @XmlAttribute(required = true)
    protected String version;

    public List<VAST.Ad> getAd() {
        if (ad == null) {
            ad = new ArrayList<>();
        }
        return ad;
    }

    public void setAd(List<Ad> ad) {
        this.ad = ad;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "inLine",
            "wrapper"
    })
    public static final class Ad {

        @XmlElement(name = "InLine")
        protected VAST.Ad.InLine inLine;
        @XmlElement(name = "Wrapper")
        protected VAST.Ad.Wrapper wrapper;
        @XmlAttribute
        protected String id;
        @XmlAttribute
        protected BigInteger sequence;

        public VAST.Ad.InLine getInLine() {
            return inLine;
        }

        public void setInLine(VAST.Ad.InLine inLine) {
            this.inLine = inLine;
        }

        public VAST.Ad.Wrapper getWrapper() {
            return wrapper;
        }

        public void setWrapper(VAST.Ad.Wrapper wrapper) {
            this.wrapper = wrapper;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BigInteger getSequence() {
            return sequence;
        }

        public void setSequence(BigInteger sequence) {
            this.sequence = sequence;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "adSystem",
                "adTitle",
                "description",
                "advertiser",
                "pricing",
                "survey",
                "error",
                "impression",
                "creatives",
                "extensions"
        })
        public static final class InLine {

            @XmlElement(name = "AdSystem", required = true)
            protected AdSystemType adSystem;
            @XmlElement(name = "AdTitle", required = true)
            @XmlCDATA
            protected String adTitle;
            @XmlElement(name = "Description")
            @XmlCDATA
            protected String description;
            @XmlElement(name = "Advertiser")
            @XmlCDATA
            protected String advertiser;
            @XmlElement(name = "Pricing")
            protected VAST.Ad.InLine.Pricing pricing;
            @XmlElement(name = "Survey")
            @XmlCDATA
            protected String survey;
            @XmlElement(name = "Error")
            @XmlCDATA
            protected String error;
            @XmlElement(name = "Impression", required = true)
            protected List<ImpressionType> impression;
            @XmlElement(name = "Creatives", required = true)
            protected VAST.Ad.InLine.Creatives creatives;
            @XmlElement(name = "Extensions")
            protected ExtensionsType extensions;

            public AdSystemType getAdSystem() {
                return adSystem;
            }

            public void setAdSystem(AdSystemType adSystem) {
                this.adSystem = adSystem;
            }

            public String getAdTitle() {
                return adTitle;
            }

            public void setAdTitle(String adTitle) {
                this.adTitle = adTitle;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAdvertiser() {
                return advertiser;
            }

            public void setAdvertiser(String advertiser) {
                this.advertiser = advertiser;
            }

            public VAST.Ad.InLine.Pricing getPricing() {
                return pricing;
            }

            public void setPricing(VAST.Ad.InLine.Pricing pricing) {
                this.pricing = pricing;
            }

            public String getSurvey() {
                return survey;
            }

            public void setSurvey(String survey) {
                this.survey = survey;
            }

            public String getError() {
                return error;
            }

            public void setError(String error) {
                this.error = error;
            }

            public List<ImpressionType> getImpression() {
                if (impression == null) {
                    impression = new ArrayList<>();
                }
                return impression;
            }

            public void setImpression(List<ImpressionType> impression) {
                this.impression = impression;
            }

            public VAST.Ad.InLine.Creatives getCreatives() {
                return creatives;
            }

            public void setCreatives(VAST.Ad.InLine.Creatives creatives) {
                this.creatives = creatives;
            }

            public ExtensionsType getExtensions() {
                return extensions;
            }

            public void setExtensions(ExtensionsType extensions) {
                this.extensions = extensions;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = "creative")
            public static final class Creatives {

                @XmlElement(name = "Creative", required = true)
                protected List<VAST.Ad.InLine.Creatives.Creative> creative;

                public List<VAST.Ad.InLine.Creatives.Creative> getCreative() {
                    if (creative == null) {
                        creative = new ArrayList<>();
                    }
                    return creative;
                }

                public void setCreative(List<Creative> creative) {
                    this.creative = creative;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "linear",
                        "companionAds",
                        "nonLinearAds"
                })
                public static final class Creative {

                    @XmlElement(name = "Linear")
                    protected VAST.Ad.InLine.Creatives.Creative.Linear linear;
                    @XmlElement(name = "CompanionAds")
                    protected VAST.Ad.InLine.Creatives.Creative.CompanionAds companionAds;
                    @XmlElement(name = "NonLinearAds")
                    protected VAST.Ad.InLine.Creatives.Creative.NonLinearAds nonLinearAds;
                    @XmlAttribute
                    protected String id;
                    @XmlAttribute
                    protected BigInteger sequence;
                    @XmlAttribute(name = "AdID")
                    protected String adID;

                    public VAST.Ad.InLine.Creatives.Creative.Linear getLinear() {
                        return linear;
                    }

                    public void setLinear(VAST.Ad.InLine.Creatives.Creative.Linear linear) {
                        this.linear = linear;
                    }

                    public VAST.Ad.InLine.Creatives.Creative.CompanionAds getCompanionAds() {
                        return companionAds;
                    }

                    public void setCompanionAds(VAST.Ad.InLine.Creatives.Creative.CompanionAds companionAds) {
                        this.companionAds = companionAds;
                    }

                    public VAST.Ad.InLine.Creatives.Creative.NonLinearAds getNonLinearAds() {
                        return nonLinearAds;
                    }

                    public void setNonLinearAds(VAST.Ad.InLine.Creatives.Creative.NonLinearAds nonLinearAds) {
                        this.nonLinearAds = nonLinearAds;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public BigInteger getSequence() {
                        return sequence;
                    }

                    public void setSequence(BigInteger sequence) {
                        this.sequence = sequence;
                    }

                    public String getAdID() {
                        return adID;
                    }

                    public void setAdID(String adID) {
                        this.adID = adID;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = "companion")
                    public static final class CompanionAds {

                        @XmlElement(name = "Companion")
                        protected List<CompanionType> companion;
                        @XmlAttribute
                        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                        protected String required;

                        public List<CompanionType> getCompanion() {
                            if (companion == null) {
                                companion = new ArrayList<>();
                            }
                            return companion;
                        }

                        public void setCompanion(List<CompanionType> companion) {
                            this.companion = companion;
                        }

                        public String getRequired() {
                            return required;
                        }

                        public void setRequired(String required) {
                            this.required = required;
                        }

                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "icons",
                            "creativeExtensions",
                            "duration",
                            "trackingEvents",
                            "adParameters",
                            "videoClicks",
                            "mediaFiles"
                    })
                    public static final class Linear {

                        @XmlElement(name = "Icons")
                        protected Icons icons;
                        @XmlElement(name = "CreativeExtensions")
                        protected CreativeExtensionsType creativeExtensions;
                        @XmlElement(name = "Duration", required = true)
                        @XmlSchemaType(name = "time")
                        protected XMLGregorianCalendar duration;
                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsType trackingEvents;
                        @XmlElement(name = "AdParameters")
                        protected AdParametersType adParameters;
                        @XmlElement(name = "VideoClicks")
                        protected VideoClicksType videoClicks;
                        @XmlElement(name = "MediaFiles")
                        protected VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles mediaFiles;
                        @XmlAttribute
                        protected String skipoffset;

                        public Icons getIcons() {
                            return icons;
                        }

                        public void setIcons(Icons icons) {
                            this.icons = icons;
                        }

                        public CreativeExtensionsType getCreativeExtensions() {
                            return creativeExtensions;
                        }

                        public void setCreativeExtensions(CreativeExtensionsType creativeExtensions) {
                            this.creativeExtensions = creativeExtensions;
                        }

                        public XMLGregorianCalendar getDuration() {
                            return duration;
                        }

                        public void setDuration(XMLGregorianCalendar duration) {
                            this.duration = duration;
                        }

                        public TrackingEventsType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                        public AdParametersType getAdParameters() {
                            return adParameters;
                        }

                        public void setAdParameters(AdParametersType adParameters) {
                            this.adParameters = adParameters;
                        }

                        public VideoClicksType getVideoClicks() {
                            return videoClicks;
                        }

                        public void setVideoClicks(VideoClicksType videoClicks) {
                            this.videoClicks = videoClicks;
                        }

                        public VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles getMediaFiles() {
                            return mediaFiles;
                        }

                        public void setMediaFiles(VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles mediaFiles) {
                            this.mediaFiles = mediaFiles;
                        }

                        public String getSkipoffset() {
                            return skipoffset;
                        }

                        public void setSkipoffset(String skipoffset) {
                            this.skipoffset = skipoffset;
                        }

                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = "mediaFile")
                        public static final class MediaFiles {

                            @XmlElement(name = "MediaFile", required = true)
                            protected List<VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles.MediaFile> mediaFile;

                            public List<VAST.Ad.InLine.Creatives.Creative.Linear.MediaFiles.MediaFile> getMediaFile() {
                                if (mediaFile == null) {
                                    mediaFile = new ArrayList<>();
                                }
                                return mediaFile;
                            }

                            public void setMediaFile(List<MediaFile> mediaFile) {
                                this.mediaFile = mediaFile;
                            }

                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = "value")
                            public static final class MediaFile {

                                @XmlValue
                                @XmlSchemaType(name = "anyURI")
                                @XmlCDATA
                                protected String value;
                                @XmlAttribute
                                protected String id;
                                @XmlAttribute(required = true)
                                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                                protected String delivery;
                                @XmlAttribute(required = true)
                                protected String type;
                                @XmlAttribute
                                protected BigInteger bitrate;
                                @XmlAttribute
                                protected BigInteger minBitrate;
                                @XmlAttribute
                                protected BigInteger maxBitrate;
                                @XmlAttribute(required = true)
                                protected BigInteger width;
                                @XmlAttribute(required = true)
                                protected BigInteger height;
                                @XmlAttribute
                                protected Boolean scalable;
                                @XmlAttribute
                                protected Boolean maintainAspectRatio;
                                @XmlAttribute
                                protected String apiFramework;
                                @XmlAttribute
                                protected String codec;

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

                                public String getDelivery() {
                                    return delivery;
                                }

                                public void setDelivery(String delivery) {
                                    this.delivery = delivery;
                                }

                                public String getType() {
                                    return type;
                                }

                                public void setType(String type) {
                                    this.type = type;
                                }

                                public BigInteger getBitrate() {
                                    return bitrate;
                                }

                                public void setBitrate(BigInteger bitrate) {
                                    this.bitrate = bitrate;
                                }

                                public BigInteger getMinBitrate() {
                                    return minBitrate;
                                }

                                public void setMinBitrate(BigInteger minBitrate) {
                                    this.minBitrate = minBitrate;
                                }

                                public BigInteger getMaxBitrate() {
                                    return maxBitrate;
                                }

                                public void setMaxBitrate(BigInteger maxBitrate) {
                                    this.maxBitrate = maxBitrate;
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

                                public Boolean isScalable() {
                                    return scalable;
                                }

                                public void setScalable(Boolean scalable) {
                                    this.scalable = scalable;
                                }

                                public Boolean isMaintainAspectRatio() {
                                    return maintainAspectRatio;
                                }

                                public void setMaintainAspectRatio(Boolean maintainAspectRatio) {
                                    this.maintainAspectRatio = maintainAspectRatio;
                                }

                                public String getApiFramework() {
                                    return apiFramework;
                                }

                                public void setApiFramework(String apiFramework) {
                                    this.apiFramework = apiFramework;
                                }

                                public String getCodec() {
                                    return codec;
                                }

                                public void setCodec(String codec) {
                                    this.codec = codec;
                                }

                            }

                        }

                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "trackingEvents",
                            "nonLinear"
                    })
                    public static final class NonLinearAds {

                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsType trackingEvents;
                        @XmlElement(name = "NonLinear", required = true)
                        protected List<NonLinearType> nonLinear;

                        public TrackingEventsType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                        public List<NonLinearType> getNonLinear() {
                            if (nonLinear == null) {
                                nonLinear = new ArrayList<>();
                            }
                            return nonLinear;
                        }

                        public void setNonLinear(List<NonLinearType> nonLinear) {
                            this.nonLinear = nonLinear;
                        }

                    }

                }

            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = "value")
            public static final class Pricing {

                @XmlValue
                protected BigDecimal value;
                @XmlAttribute(required = true)
                @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                protected String model;
                @XmlAttribute(required = true)
                protected String currency;

                public BigDecimal getValue() {
                    return value;
                }

                public void setValue(BigDecimal value) {
                    this.value = value;
                }

                public String getModel() {
                    return model;
                }

                public void setModel(String model) {
                    this.model = model;
                }

                public String getCurrency() {
                    return currency;
                }

                public void setCurrency(String currency) {
                    this.currency = currency;
                }

            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "adSystem",
                "vastAdTagURI",
                "error",
                "impression",
                "creatives",
                "extensions"
        })
        public static final class Wrapper {

            @XmlElement(name = "AdSystem", required = true)
            protected AdSystemType adSystem;
            @XmlElement(name = "VASTAdTagURI", required = true)
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String vastAdTagURI;
            @XmlElement(name = "Error")
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String error;
            @XmlElement(name = "Impression", required = true)
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected List<String> impression;
            @XmlElement(name = "Creatives")
            protected VAST.Ad.Wrapper.Creatives creatives;
            @XmlElement(name = "Extensions")
            protected ExtensionsType extensions;

            public AdSystemType getAdSystem() {
                return adSystem;
            }

            public void setAdSystem(AdSystemType adSystem) {
                this.adSystem = adSystem;
            }

            public String getVASTAdTagURI() {
                return vastAdTagURI;
            }

            public void setVASTAdTagURI(String vastAdTagURI) {
                this.vastAdTagURI = vastAdTagURI;
            }

            public String getError() {
                return error;
            }

            public void setError(String error) {
                this.error = error;
            }

            public List<String> getImpression() {
                if (impression == null) {
                    impression = new ArrayList<>();
                }
                return impression;
            }

            public void setImpression(List<String> impression) {
                this.impression = impression;
            }

            public VAST.Ad.Wrapper.Creatives getCreatives() {
                return creatives;
            }

            public void setCreatives(VAST.Ad.Wrapper.Creatives creatives) {
                this.creatives = creatives;
            }

            public ExtensionsType getExtensions() {
                return extensions;
            }

            public void setExtensions(ExtensionsType extensions) {
                this.extensions = extensions;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = "creative")
            public static final class Creatives {

                @XmlElement(name = "Creative")
                protected List<VAST.Ad.Wrapper.Creatives.Creative> creative;

                public List<VAST.Ad.Wrapper.Creatives.Creative> getCreative() {
                    if (creative == null) {
                        creative = new ArrayList<>();
                    }
                    return creative;
                }

                public void setCreative(List<Creative> creative) {
                    this.creative = creative;
                }

                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                        "linear",
                        "companionAds",
                        "nonLinearAds"
                })
                public static final class Creative {

                    @XmlElement(name = "Linear")
                    protected VAST.Ad.Wrapper.Creatives.Creative.Linear linear;
                    @XmlElement(name = "CompanionAds")
                    protected VAST.Ad.Wrapper.Creatives.Creative.CompanionAds companionAds;
                    @XmlElement(name = "NonLinearAds")
                    protected VAST.Ad.Wrapper.Creatives.Creative.NonLinearAds nonLinearAds;
                    @XmlAttribute
                    protected String id;
                    @XmlAttribute
                    protected BigInteger sequence;
                    @XmlAttribute(name = "AdID")
                    protected String adID;

                    public VAST.Ad.Wrapper.Creatives.Creative.Linear getLinear() {
                        return linear;
                    }

                    public void setLinear(VAST.Ad.Wrapper.Creatives.Creative.Linear linear) {
                        this.linear = linear;
                    }

                    public VAST.Ad.Wrapper.Creatives.Creative.CompanionAds getCompanionAds() {
                        return companionAds;
                    }

                    public void setCompanionAds(VAST.Ad.Wrapper.Creatives.Creative.CompanionAds companionAds) {
                        this.companionAds = companionAds;
                    }

                    public VAST.Ad.Wrapper.Creatives.Creative.NonLinearAds getNonLinearAds() {
                        return nonLinearAds;
                    }

                    public void setNonLinearAds(VAST.Ad.Wrapper.Creatives.Creative.NonLinearAds nonLinearAds) {
                        this.nonLinearAds = nonLinearAds;
                    }

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public BigInteger getSequence() {
                        return sequence;
                    }

                    public void setSequence(BigInteger sequence) {
                        this.sequence = sequence;
                    }

                    public String getAdID() {
                        return adID;
                    }

                    public void setAdID(String adID) {
                        this.adID = adID;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = "companion")
                    public static final class CompanionAds {

                        @XmlElement(name = "Companion")
                        protected List<CompanionWrapperType> companion;

                        public List<CompanionWrapperType> getCompanion() {
                            if (companion == null) {
                                companion = new ArrayList<>();
                            }
                            return companion;
                        }

                        public void setCompanion(List<CompanionWrapperType> companion) {
                            this.companion = companion;
                        }
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "creativeExtensions",
                            "icons",
                            "trackingEvents",
                            "videoClicks"
                    })
                    public static final class Linear {

                        @XmlElement(name = "CreativeExtensions")
                        protected CreativeExtensionsType creativeExtensions;
                        @XmlElement(name = "Icons")
                        protected Icons icons;
                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsType trackingEvents;
                        @XmlElement(name = "VideoClicks")
                        protected VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks videoClicks;

                        public CreativeExtensionsType getCreativeExtensions() {
                            return creativeExtensions;
                        }

                        public void setCreativeExtensions(CreativeExtensionsType creativeExtensions) {
                            this.creativeExtensions = creativeExtensions;
                        }

                        public Icons getIcons() {
                            return icons;
                        }

                        public void setIcons(Icons icons) {
                            this.icons = icons;
                        }

                        public TrackingEventsType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                        public VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks getVideoClicks() {
                            return videoClicks;
                        }

                        public void setVideoClicks(VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks videoClicks) {
                            this.videoClicks = videoClicks;
                        }

                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                                "clickTracking",
                                "customClick"
                        })
                        public static final class VideoClicks {

                            @XmlElement(name = "ClickTracking")
                            protected List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> clickTracking;
                            @XmlElement(name = "CustomClick")
                            protected List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.CustomClick> customClick;

                            public List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.ClickTracking> getClickTracking() {
                                if (clickTracking == null) {
                                    clickTracking = new ArrayList<>();
                                }
                                return clickTracking;
                            }

                            public void setClickTracking(List<ClickTracking> clickTracking) {
                                this.clickTracking = clickTracking;
                            }

                            public List<VAST.Ad.Wrapper.Creatives.Creative.Linear.VideoClicks.CustomClick> getCustomClick() {
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
                            public static final class ClickTracking {

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
                            public static final class CustomClick {

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

                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "trackingEvents",
                            "nonLinear"
                    })
                    public static final class NonLinearAds {

                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsType trackingEvents;
                        @XmlElement(name = "NonLinear")
                        protected List<NonLinearWrapperType> nonLinear;

                        public TrackingEventsType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                        public List<NonLinearWrapperType> getNonLinear() {
                            if (nonLinear == null) {
                                nonLinear = new ArrayList<>();
                            }
                            return nonLinear;
                        }

                        public void setNonLinear(List<NonLinearWrapperType> nonLinear) {
                            this.nonLinear = nonLinear;
                        }
                    }

                }

            }

        }

    }

}
