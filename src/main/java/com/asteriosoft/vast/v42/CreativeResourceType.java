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

package com.asteriosoft.vast.v42;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * A base creative resource type (sec 3.13) for non-video creative content. This specifies static, IFrame, or HTML content, or a combination thereof
 * 
 * <p>Java class for CreativeResource_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreativeResource_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HTMLResource" type="{http://www.iab.com/VAST}HTMLResource_type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IFrameResource" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="StaticResource" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                 &lt;attribute name="creativeType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreativeResource_type", propOrder = {
    "htmlResource",
    "iFrameResource",
    "staticResource"
})
@XmlSeeAlso({
    NonLinearAdInlineType.class,
    IconType.class,
    CompanionAdType.class
})
public class CreativeResourceType {

    @XmlElement(name = "HTMLResource")
    protected List<HTMLResourceType> htmlResource;
    @XmlElement(name = "IFrameResource")
    @XmlSchemaType(name = "anyURI")
    protected List<String> iFrameResource;
    @XmlElement(name = "StaticResource")
    protected List<CreativeResourceType.StaticResource> staticResource;

    /**
     * Gets the value of the htmlResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the htmlResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHTMLResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HTMLResourceType }
     * 
     * 
     */
    public List<HTMLResourceType> getHTMLResource() {
        if (htmlResource == null) {
            htmlResource = new ArrayList<HTMLResourceType>();
        }
        return this.htmlResource;
    }

    /**
     * Gets the value of the iFrameResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iFrameResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIFrameResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIFrameResource() {
        if (iFrameResource == null) {
            iFrameResource = new ArrayList<String>();
        }
        return this.iFrameResource;
    }

    /**
     * Gets the value of the staticResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the staticResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStaticResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreativeResourceType.StaticResource }
     * 
     * 
     */
    public List<CreativeResourceType.StaticResource> getStaticResource() {
        if (staticResource == null) {
            staticResource = new ArrayList<CreativeResourceType.StaticResource>();
        }
        return this.staticResource;
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
     *       &lt;attribute name="creativeType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class StaticResource {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        protected String value;
        @XmlAttribute(name = "creativeType", required = true)
        protected String creativeType;

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
         * Gets the value of the creativeType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreativeType() {
            return creativeType;
        }

        /**
         * Sets the value of the creativeType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreativeType(String value) {
            this.creativeType = value;
        }

    }

}
