/*
 *
 *  * Copyright 2023 Asteriosoft Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.asteriosoft.vast.v41;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for CompanionAd_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompanionAd_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}CreativeResource_type">
 *       &lt;sequence>
 *         &lt;element name="AdParameters" type="{http://www.iab.com/VAST}AdParameters_type" minOccurs="0"/>
 *         &lt;element name="AltText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompanionClickThrough" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="CompanionClickTracking" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                 &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CreativeExtensions" type="{http://www.iab.com/VAST}CreativeExtensions_type" minOccurs="0"/>
 *         &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="assetWidth" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="assetHeight" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="expandedWidth" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="expandedHeight" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="adSlotId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pxratio" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="renderingMode">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *             &lt;enumeration value="default"/>
 *             &lt;enumeration value="end-card"/>
 *             &lt;enumeration value="concurrent"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanionAd_type", propOrder = {
    "adParameters",
    "altText",
    "companionClickThrough",
    "companionClickTracking",
    "creativeExtensions",
    "trackingEvents"
})
public class CompanionAdType
    extends CreativeResourceType
{

    @XmlElement(name = "AdParameters")
    protected AdParametersType adParameters;
    @XmlElement(name = "AltText")
    protected String altText;
    @XmlElement(name = "CompanionClickThrough")
    @XmlSchemaType(name = "anyURI")
    protected String companionClickThrough;
    @XmlElement(name = "CompanionClickTracking")
    protected List<CompanionAdType.CompanionClickTracking> companionClickTracking;
    @XmlElement(name = "CreativeExtensions")
    protected CreativeExtensionsType creativeExtensions;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsType trackingEvents;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "width", required = true)
    protected BigInteger width;
    @XmlAttribute(name = "height", required = true)
    protected BigInteger height;
    @XmlAttribute(name = "assetWidth")
    protected BigInteger assetWidth;
    @XmlAttribute(name = "assetHeight")
    protected BigInteger assetHeight;
    @XmlAttribute(name = "expandedWidth")
    protected BigInteger expandedWidth;
    @XmlAttribute(name = "expandedHeight")
    protected BigInteger expandedHeight;
    @XmlAttribute(name = "apiFramework")
    protected String apiFramework;
    @XmlAttribute(name = "adSlotId")
    protected String adSlotId;
    @XmlAttribute(name = "pxratio")
    protected BigDecimal pxratio;
    @XmlAttribute(name = "renderingMode")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String renderingMode;

    /**
     * Gets the value of the adParameters property.
     * 
     * @return
     *     possible object is
     *     {@link AdParametersType }
     *     
     */
    public AdParametersType getAdParameters() {
        return adParameters;
    }

    /**
     * Sets the value of the adParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdParametersType }
     *     
     */
    public void setAdParameters(AdParametersType value) {
        this.adParameters = value;
    }

    /**
     * Gets the value of the altText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltText() {
        return altText;
    }

    /**
     * Sets the value of the altText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltText(String value) {
        this.altText = value;
    }

    /**
     * Gets the value of the companionClickThrough property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanionClickThrough() {
        return companionClickThrough;
    }

    /**
     * Sets the value of the companionClickThrough property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanionClickThrough(String value) {
        this.companionClickThrough = value;
    }

    /**
     * Gets the value of the companionClickTracking property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the companionClickTracking property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompanionClickTracking().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompanionAdType.CompanionClickTracking }
     * 
     * 
     */
    public List<CompanionAdType.CompanionClickTracking> getCompanionClickTracking() {
        if (companionClickTracking == null) {
            companionClickTracking = new ArrayList<CompanionAdType.CompanionClickTracking>();
        }
        return this.companionClickTracking;
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
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getWidth() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setWidth(BigInteger value) {
        this.width = value;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHeight() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHeight(BigInteger value) {
        this.height = value;
    }

    /**
     * Gets the value of the assetWidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAssetWidth() {
        return assetWidth;
    }

    /**
     * Sets the value of the assetWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAssetWidth(BigInteger value) {
        this.assetWidth = value;
    }

    /**
     * Gets the value of the assetHeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAssetHeight() {
        return assetHeight;
    }

    /**
     * Sets the value of the assetHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAssetHeight(BigInteger value) {
        this.assetHeight = value;
    }

    /**
     * Gets the value of the expandedWidth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpandedWidth() {
        return expandedWidth;
    }

    /**
     * Sets the value of the expandedWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpandedWidth(BigInteger value) {
        this.expandedWidth = value;
    }

    /**
     * Gets the value of the expandedHeight property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpandedHeight() {
        return expandedHeight;
    }

    /**
     * Sets the value of the expandedHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpandedHeight(BigInteger value) {
        this.expandedHeight = value;
    }

    /**
     * Gets the value of the apiFramework property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApiFramework() {
        return apiFramework;
    }

    /**
     * Sets the value of the apiFramework property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApiFramework(String value) {
        this.apiFramework = value;
    }

    /**
     * Gets the value of the adSlotId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdSlotId() {
        return adSlotId;
    }

    /**
     * Sets the value of the adSlotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdSlotId(String value) {
        this.adSlotId = value;
    }

    /**
     * Gets the value of the pxratio property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPxratio() {
        return pxratio;
    }

    /**
     * Sets the value of the pxratio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPxratio(BigDecimal value) {
        this.pxratio = value;
    }

    /**
     * Gets the value of the renderingMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenderingMode() {
        return renderingMode;
    }

    /**
     * Sets the value of the renderingMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenderingMode(String value) {
        this.renderingMode = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class CompanionClickTracking {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        protected String value;
        @XmlAttribute(name = "id", required = true)
        protected String id;

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
         * Gets the value of the id property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Sets the value of the id property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

    }

}
