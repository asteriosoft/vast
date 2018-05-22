package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Companion_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "adParameters",
        "altText",
        "companionClickThrough",
        "companionClickTracking",
        "trackingEvents"
})
public class CompanionType {

    @XmlElement(name = "StaticResource")
    protected StaticResourceType staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    protected HTMLResourceType htmlResource;
    @XmlElement(name = "AdParameters")
    protected AdParametersType adParameters;
    @XmlElement(name = "AltText")
    @XmlCDATA
    protected String altText;
    @XmlElement(name = "CompanionClickThrough")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String companionClickThrough;
    @XmlElement(name = "CompanionClickTracking")
    protected List<AnyURIType> companionClickTracking;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsCompanionType trackingEvents;
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

    public AdParametersType getAdParameters() {
        return adParameters;
    }

    public void setAdParameters(AdParametersType adParameters) {
        this.adParameters = adParameters;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getCompanionClickThrough() {
        return companionClickThrough;
    }

    public void setCompanionClickThrough(String companionClickThrough) {
        this.companionClickThrough = companionClickThrough;
    }

    public List<AnyURIType> getCompanionClickTracking() {
        if (companionClickTracking == null) {
            companionClickTracking = new ArrayList<>();
        }
        return companionClickTracking;
    }

    public void setCompanionClickTracking(List<AnyURIType> companionClickTracking) {
        this.companionClickTracking = companionClickTracking;
    }

    public TrackingEventsCompanionType getTrackingEvents() {
        return trackingEvents;
    }

    public void setTrackingEvents(TrackingEventsCompanionType trackingEvents) {
        this.trackingEvents = trackingEvents;
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

    public BigDecimal getPxratio() {
        return pxratio;
    }

    public void setPxratio(BigDecimal pxratio) {
        this.pxratio = pxratio;
    }

}
