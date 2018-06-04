package com.asteriosoft.vast.v3;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Companion_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "creativeExtensions",
        "trackingEvents",
        "companionClickThrough",
        "companionClickTracking",
        "altText",
        "adParameters"
})
public final class CompanionType {

    @XmlElement(name = "StaticResource")
    protected CompanionType.StaticResource staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    protected HTMLResourceType htmlResource;
    @XmlElement(name = "CreativeExtensions")
    protected CreativeExtensionsType creativeExtensions;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsType trackingEvents;
    @XmlElement(name = "CompanionClickThrough")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String companionClickThrough;
    @XmlElement(name = "CompanionClickTracking")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> companionClickTracking;
    @XmlElement(name = "AltText")
    @XmlCDATA
    protected String altText;
    @XmlElement(name = "AdParameters")
    protected AdParametersType adParameters;
    @XmlAttribute
    protected String id;
    @XmlAttribute(required = true)
    protected BigInteger width;
    @XmlAttribute(required = true)
    protected BigInteger height;
    @XmlAttribute
    protected BigInteger assetWidth;
    @XmlAttribute
    protected BigInteger assetHeight;
    @XmlAttribute
    protected BigInteger expandedWidth;
    @XmlAttribute
    protected BigInteger expandedHeight;
    @XmlAttribute
    protected String apiFramework;
    @XmlAttribute
    protected String adSlotId;

    public CompanionType.StaticResource getStaticResource() {
        return staticResource;
    }

    public void setStaticResource(CompanionType.StaticResource staticResource) {
        this.staticResource = staticResource;
    }

    public String getIFrameResource() {
        return iFrameResource;
    }

    public void setIFrameResource(String iFrameResource) {
        this.iFrameResource = iFrameResource;
    }

    public HTMLResourceType getHtmlResource() {
        return htmlResource;
    }

    public void setHtmlResource(HTMLResourceType htmlResource) {
        this.htmlResource = htmlResource;
    }

    public HTMLResourceType getHTMLResource() {
        return htmlResource;
    }

    public void setHTMLResource(HTMLResourceType htmlResource) {
        this.htmlResource = htmlResource;
    }

    public CreativeExtensionsType getCreativeExtensions() {
        return creativeExtensions;
    }

    public void setCreativeExtensions(CreativeExtensionsType creativeExtensions) {
        this.creativeExtensions = creativeExtensions;
    }

    public TrackingEventsType getTrackingEvents() {
        return trackingEvents;
    }

    public void setTrackingEvents(TrackingEventsType trackingEvents) {
        this.trackingEvents = trackingEvents;
    }

    public String getCompanionClickThrough() {
        return companionClickThrough;
    }

    public void setCompanionClickThrough(String companionClickThrough) {
        this.companionClickThrough = companionClickThrough;
    }

    public List<String> getCompanionClickTracking() {
        if (companionClickTracking == null) {
            companionClickTracking = new ArrayList<>();
        }
        return companionClickTracking;
    }

    public void setCompanionClickTracking(List<String> companionClickTracking) {
        this.companionClickTracking = companionClickTracking;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public AdParametersType getAdParameters() {
        return adParameters;
    }

    public void setAdParameters(AdParametersType adParameters) {
        this.adParameters = adParameters;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public BigInteger getAssetWidth() {
        return assetWidth;
    }

    public void setAssetWidth(BigInteger assetWidth) {
        this.assetWidth = assetWidth;
    }

    public BigInteger getAssetHeight() {
        return assetHeight;
    }

    public void setAssetHeight(BigInteger assetHeight) {
        this.assetHeight = assetHeight;
    }

    public BigInteger getExpandedWidth() {
        return expandedWidth;
    }

    public void setExpandedWidth(BigInteger expandedWidth) {
        this.expandedWidth = expandedWidth;
    }

    public BigInteger getExpandedHeight() {
        return expandedHeight;
    }

    public void setExpandedHeight(BigInteger expandedHeight) {
        this.expandedHeight = expandedHeight;
    }

    public String getApiFramework() {
        return apiFramework;
    }

    public void setApiFramework(String apiFramework) {
        this.apiFramework = apiFramework;
    }

    public String getAdSlotId() {
        return adSlotId;
    }

    public void setAdSlotId(String adSlotId) {
        this.adSlotId = adSlotId;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static final class StaticResource {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String value;
        @XmlAttribute(required = true)
        protected String creativeType;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getCreativeType() {
            return creativeType;
        }

        public void setCreativeType(String creativeType) {
            this.creativeType = creativeType;
        }

    }

}
