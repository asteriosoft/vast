package com.asteriosoft.vast.v42;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for Creative_Inline_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Creative_Inline_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}Creative_Base_type">
 *       &lt;sequence>
 *         &lt;element name="CompanionAds" type="{http://www.iab.com/VAST}CompanionAds_Collection_type" minOccurs="0"/>
 *         &lt;element name="CreativeExtensions" type="{http://www.iab.com/VAST}CreativeExtensions_type" minOccurs="0"/>
 *         &lt;element name="Linear" type="{http://www.iab.com/VAST}Linear_Inline_type" minOccurs="0"/>
 *         &lt;element name="NonLinearAds" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_type" minOccurs="0"/>
 *                   &lt;element name="NonLinear" type="{http://www.iab.com/VAST}NonLinearAd_Inline_type" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="UniversalAdId" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="idRegistry" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
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
@XmlType(name = "Creative_Inline_type", propOrder = {
    "companionAds",
    "creativeExtensions",
    "linear",
    "nonLinearAds",
    "universalAdId"
})
public class CreativeInlineType
    extends CreativeBaseType
{

    @XmlElement(name = "CompanionAds")
    protected CompanionAdsCollectionType companionAds;
    @XmlElement(name = "CreativeExtensions")
    protected CreativeExtensionsType creativeExtensions;
    @XmlElement(name = "Linear")
    protected LinearInlineType linear;
    @XmlElement(name = "NonLinearAds")
    protected CreativeInlineType.NonLinearAds nonLinearAds;
    @XmlElement(name = "UniversalAdId", required = true)
    protected List<CreativeInlineType.UniversalAdId> universalAdId;

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
     * Gets the value of the creativeExtensions property.
     * 
     * @return
     *     possible object is
     *     {@link CreativeExtensionsType }
     *     
     */
    public CreativeExtensionsType getCreativeExtensions() {
        return creativeExtensions;
    }

    /**
     * Sets the value of the creativeExtensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreativeExtensionsType }
     *     
     */
    public void setCreativeExtensions(CreativeExtensionsType value) {
        this.creativeExtensions = value;
    }

    /**
     * Gets the value of the linear property.
     * 
     * @return
     *     possible object is
     *     {@link LinearInlineType }
     *     
     */
    public LinearInlineType getLinear() {
        return linear;
    }

    /**
     * Sets the value of the linear property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearInlineType }
     *     
     */
    public void setLinear(LinearInlineType value) {
        this.linear = value;
    }

    /**
     * Gets the value of the nonLinearAds property.
     * 
     * @return
     *     possible object is
     *     {@link CreativeInlineType.NonLinearAds }
     *     
     */
    public CreativeInlineType.NonLinearAds getNonLinearAds() {
        return nonLinearAds;
    }

    /**
     * Sets the value of the nonLinearAds property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreativeInlineType.NonLinearAds }
     *     
     */
    public void setNonLinearAds(CreativeInlineType.NonLinearAds value) {
        this.nonLinearAds = value;
    }

    /**
     * Gets the value of the universalAdId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the universalAdId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUniversalAdId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreativeInlineType.UniversalAdId }
     * 
     * 
     */
    public List<CreativeInlineType.UniversalAdId> getUniversalAdId() {
        if (universalAdId == null) {
            universalAdId = new ArrayList<CreativeInlineType.UniversalAdId>();
        }
        return this.universalAdId;
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
     *         &lt;element name="NonLinear" type="{http://www.iab.com/VAST}NonLinearAd_Inline_type" maxOccurs="unbounded" minOccurs="0"/>
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
        protected List<NonLinearAdInlineType> nonLinear;

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
         * {@link NonLinearAdInlineType }
         * 
         * 
         */
        public List<NonLinearAdInlineType> getNonLinear() {
            if (nonLinear == null) {
                nonLinear = new ArrayList<NonLinearAdInlineType>();
            }
            return this.nonLinear;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="idRegistry" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class UniversalAdId {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "idRegistry", required = true)
        protected String idRegistry;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the idRegistry property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdRegistry() {
            return idRegistry;
        }

        /**
         * Sets the value of the idRegistry property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdRegistry(String value) {
            this.idRegistry = value;
        }

    }

}
