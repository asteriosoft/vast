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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
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
    protected List<String> error;
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

    public List<String> getError() {
        if (error == null) {
            error = new ArrayList<>();
        }
        return error;
    }

    public void setError(List<String> error) {
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
    public static class Ad {

        @XmlElement(name = "InLine")
        protected VAST.Ad.InLine inLine;
        @XmlElement(name = "Wrapper")
        protected VAST.Ad.Wrapper wrapper;
        @XmlAttribute
        protected String id;
        @XmlAttribute
        protected BigInteger sequence;
        @XmlAttribute
        protected Boolean conditionalAd;

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

        public Boolean isConditionalAd() {
            return conditionalAd;
        }

        public void setConditionalAd(Boolean conditionalAd) {
            this.conditionalAd = conditionalAd;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "adSystem",
                "adTitle",
                "description",
                "advertiser",
                "category",
                "pricing",
                "survey",
                "error",
                "impression",
                "viewableImpression",
                "adVerifications",
                "creatives",
                "extensions"
        })
        public static class InLine {

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
            @XmlElement(name = "Category")
            protected List<CategoryType> category;
            @XmlElement(name = "Pricing")
            protected PricingType pricing;
            @XmlElement(name = "Survey")
            protected SurveyType survey;
            @XmlElement(name = "Error")
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected List<String> error;
            @XmlElement(name = "Impression", required = true)
            protected List<AnyURIType> impression;
            @XmlElement(name = "ViewableImpression")
            protected ViewableImpressionType viewableImpression;
            @XmlElement(name = "AdVerifications")
            protected AdVerificationsType adVerifications;
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

            public List<CategoryType> getCategory() {
                if (category == null) {
                    category = new ArrayList<>();
                }
                return category;
            }

            public void setCategory(List<CategoryType> category) {
                this.category = category;
            }

            public PricingType getPricing() {
                return pricing;
            }

            public void setPricing(PricingType pricing) {
                this.pricing = pricing;
            }

            public SurveyType getSurvey() {
                return survey;
            }

            public void setSurvey(SurveyType survey) {
                this.survey = survey;
            }

            public List<String> getError() {
                if (error == null) {
                    error = new ArrayList<>();
                }
                return error;
            }

            public void setError(List<String> error) {
                this.error = error;
            }

            public List<AnyURIType> getImpression() {
                if (impression == null) {
                    impression = new ArrayList<>();
                }
                return impression;
            }

            public void setImpression(List<AnyURIType> impression) {
                this.impression = impression;
            }

            public ViewableImpressionType getViewableImpression() {
                return viewableImpression;
            }

            public void setViewableImpression(ViewableImpressionType viewableImpression) {
                this.viewableImpression = viewableImpression;
            }

            public AdVerificationsType getAdVerifications() {
                return adVerifications;
            }

            public void setAdVerifications(AdVerificationsType adVerifications) {
                this.adVerifications = adVerifications;
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
            public static class Creatives {

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
                        "universalAdId",
                        "creativeExtensions",
                        "linear",
                        "companionAds",
                        "nonLinearAds"
                })
                public static class Creative {

                    @XmlElement(name = "UniversalAdId", required = true)
                    protected UniversalAdIdType universalAdId;
                    @XmlElement(name = "CreativeExtensions")
                    protected CreativeExtensionsType creativeExtensions;
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
                    @XmlAttribute
                    protected String adId;
                    @XmlAttribute
                    protected String apiFramework;

                    public UniversalAdIdType getUniversalAdId() {
                        return universalAdId;
                    }

                    public void setUniversalAdId(UniversalAdIdType universalAdId) {
                        this.universalAdId = universalAdId;
                    }

                    public CreativeExtensionsType getCreativeExtensions() {
                        return creativeExtensions;
                    }

                    public void setCreativeExtensions(CreativeExtensionsType creativeExtensions) {
                        this.creativeExtensions = creativeExtensions;
                    }

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

                    public String getAdId() {
                        return adId;
                    }

                    public void setAdId(String adId) {
                        this.adId = adId;
                    }

                    public String getApiFramework() {
                        return apiFramework;
                    }

                    public void setApiFramework(String apiFramework) {
                        this.apiFramework = apiFramework;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = "companion")
                    public static class CompanionAds {

                        @XmlElement(name = "Companion", required = true)
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
                            "duration",
                            "trackingEvents",
                            "adParameters",
                            "videoClicks",
                            "mediaFiles",
                            "icons"
                    })
                    public static class Linear {

                        @XmlElement(name = "Duration", required = true)
                        @XmlSchemaType(name = "time")
                        protected XMLGregorianCalendar duration;
                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsLinearType trackingEvents;
                        @XmlElement(name = "AdParameters")
                        protected AdParametersType adParameters;
                        @XmlElement(name = "VideoClicks")
                        protected VideoClicksType videoClicks;
                        @XmlElement(name = "MediaFiles", required = true)
                        protected MediaFilesType mediaFiles;
                        @XmlElement(name = "Icons")
                        protected IconsType icons;
                        @XmlAttribute
                        protected String skipoffset;

                        public XMLGregorianCalendar getDuration() {
                            return duration;
                        }

                        public void setDuration(XMLGregorianCalendar duration) {
                            this.duration = duration;
                        }

                        public TrackingEventsLinearType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsLinearType trackingEvents) {
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

                        public MediaFilesType getMediaFiles() {
                            return mediaFiles;
                        }

                        public void setMediaFiles(MediaFilesType mediaFiles) {
                            this.mediaFiles = mediaFiles;
                        }

                        public IconsType getIcons() {
                            return icons;
                        }

                        public void setIcons(IconsType icons) {
                            this.icons = icons;
                        }

                        public String getSkipoffset() {
                            return skipoffset;
                        }

                        public void setSkipoffset(String skipoffset) {
                            this.skipoffset = skipoffset;
                        }

                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "nonLinear",
                            "trackingEvents"
                    })
                    public static class NonLinearAds {

                        @XmlElement(name = "NonLinear", required = true)
                        protected List<NonLinearType> nonLinear;
                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsNonLinearType trackingEvents;

                        public List<NonLinearType> getNonLinear() {
                            if (nonLinear == null) {
                                nonLinear = new ArrayList<>();
                            }
                            return nonLinear;
                        }

                        public void setNonLinear(List<NonLinearType> nonLinear) {
                            this.nonLinear = nonLinear;
                        }

                        public TrackingEventsNonLinearType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsNonLinearType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                    }

                }

            }

        }


        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
                "adSystem",
                "vastAdTagURI",
                "pricing",
                "error",
                "impression",
                "viewableImpression",
                "adVerifications",
                "creatives",
                "extensions"
        })
        public static class Wrapper {

            @XmlElement(name = "AdSystem", required = true)
            protected AdSystemType adSystem;
            @XmlElement(name = "VASTAdTagURI", required = true)
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String vastAdTagURI;
            @XmlElement(name = "Pricing")
            protected PricingType pricing;
            @XmlElement(name = "Error")
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected List<String> error;
            @XmlElement(name = "Impression", required = true)
            protected List<AnyURIType> impression;
            @XmlElement(name = "ViewableImpression")
            protected ViewableImpressionType viewableImpression;
            @XmlElement(name = "AdVerifications")
            protected AdVerificationsWrapperType adVerifications;
            @XmlElement(name = "Creatives")
            protected VAST.Ad.Wrapper.Creatives creatives;
            @XmlElement(name = "Extensions")
            protected ExtensionsType extensions;
            @XmlAttribute
            protected Boolean followAdditionalWrappers;
            @XmlAttribute
            protected Boolean allowMultipleAds;
            @XmlAttribute
            protected Boolean fallbackOnNoAd;

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

            public PricingType getPricing() {
                return pricing;
            }

            public void setPricing(PricingType pricing) {
                this.pricing = pricing;
            }

            public List<String> getError() {
                if (error == null) {
                    error = new ArrayList<>();
                }
                return error;
            }

            public void setError(List<String> error) {
                this.error = error;
            }

            public List<AnyURIType> getImpression() {
                if (impression == null) {
                    impression = new ArrayList<>();
                }
                return impression;
            }

            public void setImpression(List<AnyURIType> impression) {
                this.impression = impression;
            }

            public ViewableImpressionType getViewableImpression() {
                return viewableImpression;
            }

            public void setViewableImpression(ViewableImpressionType viewableImpression) {
                this.viewableImpression = viewableImpression;
            }

            public AdVerificationsWrapperType getAdVerifications() {
                return adVerifications;
            }

            public void setAdVerifications(AdVerificationsWrapperType adVerifications) {
                this.adVerifications = adVerifications;
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

            public Boolean isFollowAdditionalWrappers() {
                return followAdditionalWrappers;
            }

            public void setFollowAdditionalWrappers(Boolean followAdditionalWrappers) {
                this.followAdditionalWrappers = followAdditionalWrappers;
            }

            public Boolean isAllowMultipleAds() {
                return allowMultipleAds;
            }

            public void setAllowMultipleAds(Boolean allowMultipleAds) {
                this.allowMultipleAds = allowMultipleAds;
            }

            public Boolean isFallbackOnNoAd() {
                return fallbackOnNoAd;
            }

            public void setFallbackOnNoAd(Boolean fallbackOnNoAd) {
                this.fallbackOnNoAd = fallbackOnNoAd;
            }

            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = "creative")
            public static class Creatives {

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
                public static class Creative {

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
                    @XmlAttribute
                    protected String adId;

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

                    public String getAdId() {
                        return adId;
                    }

                    public void setAdId(String adId) {
                        this.adId = adId;
                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = "companion")
                    public static class CompanionAds {

                        @XmlElement(name = "Companion", required = true)
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
                            "trackingEvents",
                            "videoClicks",
                            "icons"
                    })
                    public static class Linear {

                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsLinearType trackingEvents;
                        @XmlElement(name = "VideoClicks")
                        protected VideoClicksWrapperType videoClicks;
                        @XmlElement(name = "Icons")
                        protected IconsType icons;

                        public TrackingEventsLinearType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsLinearType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                        public VideoClicksWrapperType getVideoClicks() {
                            return videoClicks;
                        }

                        public void setVideoClicks(VideoClicksWrapperType videoClicks) {
                            this.videoClicks = videoClicks;
                        }

                        public IconsType getIcons() {
                            return icons;
                        }

                        public void setIcons(IconsType icons) {
                            this.icons = icons;
                        }

                    }

                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                            "nonLinear",
                            "trackingEvents"
                    })
                    public static class NonLinearAds {

                        @XmlElement(name = "NonLinear", required = true)
                        protected List<NonLinearWrapperType> nonLinear;
                        @XmlElement(name = "TrackingEvents")
                        protected TrackingEventsNonLinearType trackingEvents;

                        public List<NonLinearWrapperType> getNonLinear() {
                            if (nonLinear == null) {
                                nonLinear = new ArrayList<>();
                            }
                            return nonLinear;
                        }

                        public void setNonLinear(List<NonLinearWrapperType> nonLinear) {
                            this.nonLinear = nonLinear;
                        }

                        public TrackingEventsNonLinearType getTrackingEvents() {
                            return trackingEvents;
                        }

                        public void setTrackingEvents(TrackingEventsNonLinearType trackingEvents) {
                            this.trackingEvents = trackingEvents;
                        }

                    }

                }

            }

        }

    }

}
