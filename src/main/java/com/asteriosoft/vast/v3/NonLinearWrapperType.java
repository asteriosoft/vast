package com.asteriosoft.vast.v3;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NonLinearWrapper_type", propOrder = {
        "creativeExtensions",
        "nonLinearClickTracking"
})
public class NonLinearWrapperType {

    @XmlElement(name = "CreativeExtensions")
    protected CreativeExtensionsType creativeExtensions;
    @XmlElement(name = "NonLinearClickTracking")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> nonLinearClickTracking;
    @XmlAttribute
    protected String id;
    @XmlAttribute
    protected BigInteger width;
    @XmlAttribute
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
