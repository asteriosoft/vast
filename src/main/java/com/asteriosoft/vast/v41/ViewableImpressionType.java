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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * The ViewableImpression element allows for tracking URIs to report viewability
 * 
 * <p>Java class for ViewableImpression_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ViewableImpression_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Viewable" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NotViewable" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ViewUndetermined" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewableImpression_type", propOrder = {
    "viewable",
    "notViewable",
    "viewUndetermined"
})
public class ViewableImpressionType {

    @XmlElement(name = "Viewable")
    @XmlSchemaType(name = "anyURI")
    protected List<String> viewable;
    @XmlElement(name = "NotViewable")
    @XmlSchemaType(name = "anyURI")
    protected List<String> notViewable;
    @XmlElement(name = "ViewUndetermined")
    @XmlSchemaType(name = "anyURI")
    protected List<String> viewUndetermined;
    @XmlAttribute(name = "id")
    protected String id;

    /**
     * Gets the value of the viewable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getViewable() {
        if (viewable == null) {
            viewable = new ArrayList<String>();
        }
        return this.viewable;
    }

    /**
     * Gets the value of the notViewable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notViewable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotViewable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNotViewable() {
        if (notViewable == null) {
            notViewable = new ArrayList<String>();
        }
        return this.notViewable;
    }

    /**
     * Gets the value of the viewUndetermined property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the viewUndetermined property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getViewUndetermined().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getViewUndetermined() {
        if (viewUndetermined == null) {
            viewUndetermined = new ArrayList<String>();
        }
        return this.viewUndetermined;
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

    public void setViewable(List<String> viewable) {
        this.viewable = viewable;
    }

    public void setNotViewable(List<String> notViewable) {
        this.notViewable = notViewable;
    }

    public void setViewUndetermined(List<String> viewUndetermined) {
        this.viewUndetermined = viewUndetermined;
    }
}
