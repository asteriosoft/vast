package com.asteriosoft.vast.v41;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Video formatted ad that plays linearly
 * 
 * <p>Java class for Linear_Base_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Linear_Base_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Icons" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="Icon" type="{http://www.iab.com/VAST}Icon_type" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="skipoffset">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="(\d{2}:[0-5]\d:[0-5]\d(\.\d\d\d)?|1?\d?\d(\.?\d)*%)"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Linear_Base_type", propOrder = {
    "icons",
    "trackingEvents"
})
@XmlSeeAlso({
    LinearInlineType.class,
    LinearWrapperType.class
})
public class LinearBaseType {

    @XmlElement(name = "Icons")
    protected LinearBaseType.Icons icons;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsType trackingEvents;
    @XmlAttribute(name = "skipoffset")
    protected String skipoffset;

    /**
     * Gets the value of the icons property.
     * 
     * @return
     *     possible object is
     *     {@link LinearBaseType.Icons }
     *     
     */
    public LinearBaseType.Icons getIcons() {
        return icons;
    }

    /**
     * Sets the value of the icons property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearBaseType.Icons }
     *     
     */
    public void setIcons(LinearBaseType.Icons value) {
        this.icons = value;
    }

    /**
     * Gets the value of the trackingEvents property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingEventsType }
     *     
     */
    public TrackingEventsType getTrackingEvents() {
        return trackingEvents;
    }

    /**
     * Sets the value of the trackingEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingEventsType }
     *     
     */
    public void setTrackingEvents(TrackingEventsType value) {
        this.trackingEvents = value;
    }

    /**
     * Gets the value of the skipoffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkipoffset() {
        return skipoffset;
    }

    /**
     * Sets the value of the skipoffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkipoffset(String value) {
        this.skipoffset = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="Icon" type="{http://www.iab.com/VAST}Icon_type" minOccurs="0"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Icons {

        @XmlElement(name = "Icon")
        protected IconType icon;

        /**
         * Gets the value of the icon property.
         * 
         * @return
         *     possible object is
         *     {@link IconType }
         *     
         */
        public IconType getIcon() {
            return icon;
        }

        /**
         * Sets the value of the icon property.
         * 
         * @param value
         *     allowed object is
         *     {@link IconType }
         *     
         */
        public void setIcon(IconType value) {
            this.icon = value;
        }

    }

}
