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


/**
 * <p>Java class for Inline_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Inline_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}AdDefinitionBase_type">
 *       &lt;sequence>
 *         &lt;element name="AdServingId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdTitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AdVerifications" type="{http://www.iab.com/VAST}AdVerifications_type" minOccurs="0"/>
 *         &lt;element name="Advertiser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Category" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="authority" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Creatives">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Creative" type="{http://www.iab.com/VAST}Creative_Inline_type" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Expires" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="Survey" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
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
@XmlType(name = "Inline_type", propOrder = {
    "adServingId",
    "adTitle",
    "adVerifications",
    "advertiser",
    "category",
    "creatives",
    "description",
    "expires",
    "survey"
})
public class InlineType
    extends AdDefinitionBaseType
{

    @XmlElement(name = "AdServingId", required = true)
    protected String adServingId;
    @XmlElement(name = "AdTitle", required = true)
    protected String adTitle;
    @XmlElement(name = "AdVerifications")
    protected AdVerificationsType adVerifications;
    @XmlElement(name = "Advertiser")
    protected String advertiser;
    @XmlElement(name = "Category")
    protected List<InlineType.Category> category;
    @XmlElement(name = "Creatives", required = true)
    protected InlineType.Creatives creatives;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Expires")
    protected BigInteger expires;
    @XmlElement(name = "Survey")
    protected InlineType.Survey survey;

    /**
     * Gets the value of the adServingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdServingId() {
        return adServingId;
    }

    /**
     * Sets the value of the adServingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdServingId(String value) {
        this.adServingId = value;
    }

    /**
     * Gets the value of the adTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdTitle() {
        return adTitle;
    }

    /**
     * Sets the value of the adTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdTitle(String value) {
        this.adTitle = value;
    }

    /**
     * Gets the value of the adVerifications property.
     * 
     * @return
     *     possible object is
     *     {@link AdVerificationsType }
     *     
     */
    public AdVerificationsType getAdVerifications() {
        return adVerifications;
    }

    /**
     * Sets the value of the adVerifications property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdVerificationsType }
     *     
     */
    public void setAdVerifications(AdVerificationsType value) {
        this.adVerifications = value;
    }

    /**
     * Gets the value of the advertiser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdvertiser() {
        return advertiser;
    }

    /**
     * Sets the value of the advertiser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdvertiser(String value) {
        this.advertiser = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the category property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InlineType.Category }
     * 
     * 
     */
    public List<InlineType.Category> getCategory() {
        if (category == null) {
            category = new ArrayList<InlineType.Category>();
        }
        return this.category;
    }

    /**
     * Gets the value of the creatives property.
     * 
     * @return
     *     possible object is
     *     {@link InlineType.Creatives }
     *     
     */
    public InlineType.Creatives getCreatives() {
        return creatives;
    }

    /**
     * Sets the value of the creatives property.
     * 
     * @param value
     *     allowed object is
     *     {@link InlineType.Creatives }
     *     
     */
    public void setCreatives(InlineType.Creatives value) {
        this.creatives = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the expires property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpires() {
        return expires;
    }

    /**
     * Sets the value of the expires property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpires(BigInteger value) {
        this.expires = value;
    }

    /**
     * Gets the value of the survey property.
     * 
     * @return
     *     possible object is
     *     {@link InlineType.Survey }
     *     
     */
    public InlineType.Survey getSurvey() {
        return survey;
    }

    /**
     * Sets the value of the survey property.
     * 
     * @param value
     *     allowed object is
     *     {@link InlineType.Survey }
     *     
     */
    public void setSurvey(InlineType.Survey value) {
        this.survey = value;
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
     *       &lt;attribute name="authority" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
    public static class Category {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "authority", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String authority;

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
         * Gets the value of the authority property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthority() {
            return authority;
        }

        /**
         * Sets the value of the authority property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthority(String value) {
            this.authority = value;
        }

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
     *         &lt;element name="Creative" type="{http://www.iab.com/VAST}Creative_Inline_type" maxOccurs="unbounded"/>
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
        "creative"
    })
    public static class Creatives {

        @XmlElement(name = "Creative", required = true)
        protected List<CreativeInlineType> creative;

        /**
         * Gets the value of the creative property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the creative property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCreative().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CreativeInlineType }
         * 
         * 
         */
        public List<CreativeInlineType> getCreative() {
            if (creative == null) {
                creative = new ArrayList<CreativeInlineType>();
            }
            return this.creative;
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
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Survey {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        protected String value;
        @XmlAttribute(name = "type")
        protected String type;

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
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

    }

}
