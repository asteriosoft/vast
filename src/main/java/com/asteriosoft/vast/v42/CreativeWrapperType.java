package com.asteriosoft.vast.v42;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Creative_Wrapper_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Creative_Wrapper_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}Creative_Base_type">
 *       &lt;sequence>
 *         &lt;element name="CompanionAds" type="{http://www.iab.com/VAST}CompanionAds_Collection_type" minOccurs="0"/>
 *         &lt;element name="Linear" type="{http://www.iab.com/VAST}Linear_Wrapper_type" minOccurs="0"/>
 *         &lt;element name="NonLinearAds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_type" minOccurs="0"/>
 *                   &lt;element name="NonLinear" type="{http://www.iab.com/VAST}NonLinearAd_Base_type" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Creative_Wrapper_type", propOrder = {
    "companionAds",
    "linear",
    "nonLinearAds"
})
public class CreativeWrapperType extends CreativeBaseType {

    @XmlElement(name = "CompanionAds")
    protected CompanionAdsCollectionType companionAds;
    @XmlElement(name = "Linear")
    protected LinearWrapperType linear;
    @XmlElement(name = "NonLinearAds")
    protected CreativeWrapperType.NonLinearAds nonLinearAds;

    /**
     * Gets the value of the companionAds property.
     * 
     * @return
     *     possible object is
     *     {@link CompanionAdsCollectionType }
     *     
     */
    public CompanionAdsCollectionType getCompanionAds() {
        return companionAds;
    }

    /**
     * Sets the value of the companionAds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanionAdsCollectionType }
     *     
     */
    public void setCompanionAds(CompanionAdsCollectionType value) {
        this.companionAds = value;
    }

    /**
     * Gets the value of the linear property.
     * 
     * @return
     *     possible object is
     *     {@link LinearWrapperType }
     *     
     */
    public LinearWrapperType getLinear() {
        return linear;
    }

    /**
     * Sets the value of the linear property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearWrapperType }
     *     
     */
    public void setLinear(LinearWrapperType value) {
        this.linear = value;
    }

    /**
     * Gets the value of the nonLinearAds property.
     * 
     * @return
     *     possible object is
     *     {@link CreativeWrapperType.NonLinearAds }
     *     
     */
    public CreativeWrapperType.NonLinearAds getNonLinearAds() {
        return nonLinearAds;
    }

    /**
     * Sets the value of the nonLinearAds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreativeWrapperType.NonLinearAds }
     *     
     */
    public void setNonLinearAds(CreativeWrapperType.NonLinearAds value) {
        this.nonLinearAds = value;
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
     *       &lt;sequence>
     *         &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_type" minOccurs="0"/>
     *         &lt;element name="NonLinear" type="{http://www.iab.com/VAST}NonLinearAd_Base_type" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "trackingEvents",
        "nonLinear"
    })
    public static class NonLinearAds {

        @XmlElement(name = "TrackingEvents")
        protected TrackingEventsType trackingEvents;
        @XmlElement(name = "NonLinear")
        protected List<NonLinearAdBaseType> nonLinear;

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
         * Gets the value of the nonLinear property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the nonLinear property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getNonLinear().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link NonLinearAdBaseType }
         * 
         * 
         */
        public List<NonLinearAdBaseType> getNonLinear() {
            if (nonLinear == null) {
                nonLinear = new ArrayList<NonLinearAdBaseType>();
            }
            return this.nonLinear;
        }

    }

}
