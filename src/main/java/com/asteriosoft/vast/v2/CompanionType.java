package com.asteriosoft.vast.v2;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Companion_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "trackingEvents",
        "companionClickThrough",
        "altText",
        "adParameters"
})
public class CompanionType {

    @XmlElement(name = "StaticResource")
    protected CompanionType.StaticResource staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    @XmlCDATA
    protected String htmlResource;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsType trackingEvents;
    @XmlElement(name = "CompanionClickThrough")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String companionClickThrough;
    @XmlElement(name = "AltText")
    @XmlCDATA
    protected String altText;
    @XmlElement(name = "AdParameters")
    @XmlCDATA
    protected String adParameters;
    @XmlAttribute
    protected String id;
    @XmlAttribute(required = true)
    protected BigInteger width;
    @XmlAttribute(required = true)
    protected BigInteger height;
    @XmlAttribute
    protected BigInteger expandedWidth;
    @XmlAttribute
    protected BigInteger expandedHeight;
    @XmlAttribute
    protected String apiFramework;

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

    public String getHTMLResource() {
        return htmlResource;
    }

    public void setHTMLResource(String htmlResource) {
        this.htmlResource = htmlResource;
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

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public String getAdParameters() {
        return adParameters;
    }

    public void setAdParameters(String adParameters) {
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


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static class StaticResource {

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
