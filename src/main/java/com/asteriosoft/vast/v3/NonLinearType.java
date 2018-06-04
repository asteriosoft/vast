package com.asteriosoft.vast.v3;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonLinear_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "creativeExtensions",
        "nonLinearClickTracking",
        "nonLinearClickThrough",
        "adParameters"
})
public final class NonLinearType {

    @XmlElement(name = "StaticResource")
    protected NonLinearType.StaticResource staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    protected HTMLResourceType htmlResource;
    @XmlElement(name = "CreativeExtensions")
    protected CreativeExtensionsType creativeExtensions;
    @XmlElement(name = "NonLinearClickTracking")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> nonLinearClickTracking;
    @XmlElement(name = "NonLinearClickThrough")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String nonLinearClickThrough;
    @XmlElement(name = "AdParameters")
    protected AdParametersType adParameters;
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
    protected Boolean scalable;
    @XmlAttribute
    protected Boolean maintainAspectRatio;
    @XmlAttribute
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar minSuggestedDuration;
    @XmlAttribute
    protected String apiFramework;

    public NonLinearType.StaticResource getStaticResource() {
        return staticResource;
    }

    public void setStaticResource(NonLinearType.StaticResource staticResource) {
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

    public CreativeExtensionsType getCreativeExtensions() {
        return creativeExtensions;
    }

    public void setCreativeExtensions(CreativeExtensionsType creativeExtensions) {
        this.creativeExtensions = creativeExtensions;
    }

    public List<String> getNonLinearClickTracking() {
        if (nonLinearClickTracking == null) {
            nonLinearClickTracking = new ArrayList<>();
        }
        return nonLinearClickTracking;
    }

    public void setNonLinearClickTracking(List<String> nonLinearClickTracking) {
        this.nonLinearClickTracking = nonLinearClickTracking;
    }

    public String getNonLinearClickThrough() {
        return nonLinearClickThrough;
    }

    public void setNonLinearClickThrough(String nonLinearClickThrough) {
        this.nonLinearClickThrough = nonLinearClickThrough;
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

    public XMLGregorianCalendar getMinSuggestedDuration() {
        return minSuggestedDuration;
    }

    public void setMinSuggestedDuration(XMLGregorianCalendar minSuggestedDuration) {
        this.minSuggestedDuration = minSuggestedDuration;
    }

    public String getApiFramework() {
        return apiFramework;
    }

    public void setApiFramework(String apiFramework) {
        this.apiFramework = apiFramework;
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
