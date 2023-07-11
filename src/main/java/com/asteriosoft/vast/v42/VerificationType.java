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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Verification elements are nested under AdVerifications. The Verification element is used to contain the executable and bootstrapping required to run the measurement code for a single verification vendor. Multiple Verification elements may be used in cases where more than one verification vendor needs to collect data or when different API frameworks are used. At lease one JavaScriptResource or ExecutableResource should be provided. At most one of these resources should selected for execution, as best matches the technology available in the current environment. If the player is willing and able to run one of these resources, it should execute them BEFORE creative playback begins. Otherwise, if no resource can be executed, any appropriate tracking events listed under the <Verification> element must be fired.
 * 
 * <p>Java class for Verification_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Verification_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExecutableResource" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                 &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="JavaScriptResource" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                 &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="browserOptional" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="TrackingEvents" type="{http://www.iab.com/VAST}TrackingEvents_Verification_type" minOccurs="0"/>
 *         &lt;element name="VerificationParameters" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Verification_type", propOrder = {
    "executableResource",
    "javaScriptResource",
    "trackingEvents",
    "verificationParameters"
})
public class VerificationType {

    @XmlElement(name = "ExecutableResource")
    protected List<VerificationType.ExecutableResource> executableResource;
    @XmlElement(name = "JavaScriptResource")
    protected List<VerificationType.JavaScriptResource> javaScriptResource;
    @XmlElement(name = "TrackingEvents")
    protected TrackingEventsVerificationType trackingEvents;
    @XmlElement(name = "VerificationParameters")
    protected String verificationParameters;
    @XmlAttribute(name = "vendor")
    protected String vendor;

    /**
     * Gets the value of the executableResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the executableResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExecutableResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerificationType.ExecutableResource }
     * 
     * 
     */
    public List<VerificationType.ExecutableResource> getExecutableResource() {
        if (executableResource == null) {
            executableResource = new ArrayList<VerificationType.ExecutableResource>();
        }
        return this.executableResource;
    }

    /**
     * Gets the value of the javaScriptResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the javaScriptResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getJavaScriptResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerificationType.JavaScriptResource }
     * 
     * 
     */
    public List<VerificationType.JavaScriptResource> getJavaScriptResource() {
        if (javaScriptResource == null) {
            javaScriptResource = new ArrayList<VerificationType.JavaScriptResource>();
        }
        return this.javaScriptResource;
    }

    /**
     * Gets the value of the trackingEvents property.
     * 
     * @return
     *     possible object is
     *     {@link TrackingEventsVerificationType }
     *     
     */
    public TrackingEventsVerificationType getTrackingEvents() {
        return trackingEvents;
    }

    /**
     * Sets the value of the trackingEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link TrackingEventsVerificationType }
     *     
     */
    public void setTrackingEvents(TrackingEventsVerificationType value) {
        this.trackingEvents = value;
    }

    /**
     * Gets the value of the verificationParameters property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVerificationParameters() {
        return verificationParameters;
    }

    /**
     * Sets the value of the verificationParameters property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVerificationParameters(String value) {
        this.verificationParameters = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
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
     *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class ExecutableResource {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        protected String value;
        @XmlAttribute(name = "apiFramework")
        protected String apiFramework;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="browserOptional" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    public static class JavaScriptResource {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        protected String value;
        @XmlAttribute(name = "apiFramework")
        protected String apiFramework;
        @XmlAttribute(name = "browserOptional")
        protected Boolean browserOptional;

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
         * Gets the value of the browserOptional property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBrowserOptional() {
            return browserOptional;
        }

        /**
         * Sets the value of the browserOptional property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBrowserOptional(Boolean value) {
            this.browserOptional = value;
        }

    }

}
