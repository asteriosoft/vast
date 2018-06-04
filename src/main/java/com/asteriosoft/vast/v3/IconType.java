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
@XmlType(name = "Icon_type", propOrder = {
        "staticResource",
        "iFrameResource",
        "htmlResource",
        "iconClicks",
        "iconViewTracking"
})
public final class IconType {

    @XmlElement(name = "StaticResource")
    protected IconType.StaticResource staticResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String iFrameResource;
    @XmlElement(name = "HTMLResource")
    protected HTMLResourceType htmlResource;
    @XmlElement(name = "IconClicks")
    protected IconType.IconClicks iconClicks;
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

    public IconType.StaticResource getStaticResource() {
        return staticResource;
    }

    public void setStaticResource(IconType.StaticResource staticResource) {
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

    public IconType.IconClicks getIconClicks() {
        return iconClicks;
    }

    public void setIconClicks(IconType.IconClicks iconClicks) {
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

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "iconClickTracking",
            "iconClickThrough"
    })
    public static final class IconClicks {

        @XmlElement(name = "IconClickTracking")
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected List<String> iconClickTracking;
        @XmlElement(name = "IconClickThrough")
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String iconClickThrough;

        public List<String> getIconClickTracking() {
            if (iconClickTracking == null) {
                iconClickTracking = new ArrayList<>();
            }
            return iconClickTracking;
        }

        public void setIconClickTracking(List<String> iconClickTracking) {
            this.iconClickTracking = iconClickTracking;
        }

        public String getIconClickThrough() {
            return iconClickThrough;
        }

        public void setIconClickThrough(String iconClickThrough) {
            this.iconClickThrough = iconClickThrough;
        }

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
