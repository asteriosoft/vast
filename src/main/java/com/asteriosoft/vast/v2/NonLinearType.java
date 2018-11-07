package com.asteriosoft.vast.v2;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonLinear_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "nonLinearClickThrough",
        "adParameters"
})
public final class NonLinearType {

    @XmlElement(name = "StaticResource")
    protected StaticResource staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    @XmlCDATA
    protected String htmlResource;
    @XmlElement(name = "NonLinearClickThrough")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String nonLinearClickThrough;
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
    protected Boolean scalable;
    @XmlAttribute
    protected Boolean maintainAspectRatio;
    @XmlAttribute
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar minSuggestedDuration;
    @XmlAttribute
    protected String apiFramework;

    public StaticResource getStaticResource() {
        return staticResource;
    }

    public void setStaticResource(StaticResource staticResource) {
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

    public String getNonLinearClickThrough() {
        return nonLinearClickThrough;
    }

    public void setNonLinearClickThrough(String nonLinearClickThrough) {
        this.nonLinearClickThrough = nonLinearClickThrough;
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

}
