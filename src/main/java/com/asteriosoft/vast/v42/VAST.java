package com.asteriosoft.vast.v42;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Ad" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice>
 *                   &lt;element name="InLine" type="{http://www.iab.com/VAST}Inline_type" minOccurs="0"/>
 *                   &lt;element name="Wrapper" type="{http://www.iab.com/VAST}Wrapper_type" minOccurs="0"/>
 *                 &lt;/choice>
 *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="conditionalAd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                 &lt;attribute name="adType">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                       &lt;enumeration value="video"/>
 *                       &lt;enumeration value="audio"/>
 *                       &lt;enumeration value="hybrid"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/choice>
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ad",
    "error"
})
@XmlRootElement(name = "VAST")
public class VAST {

    @XmlElement(name = "Ad")
    protected List<VAST.Ad> ad;
    @XmlElement(name = "Error")
    @XmlSchemaType(name = "anyURI")
    protected List<String> error;
    @XmlAttribute(name = "version", required = true)
    protected String version;

    /**
     * Gets the value of the ad property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ad property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VAST.Ad }
     * 
     * 
     */
    public List<VAST.Ad> getAd() {
        if (ad == null) {
            ad = new ArrayList<VAST.Ad>();
        }
        return this.ad;
    }

    /**
     * Gets the value of the error property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the error property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getError().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getError() {
        if (error == null) {
            error = new ArrayList<String>();
        }
        return this.error;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
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
     *       &lt;choice>
     *         &lt;element name="InLine" type="{http://www.iab.com/VAST}Inline_type" minOccurs="0"/>
     *         &lt;element name="Wrapper" type="{http://www.iab.com/VAST}Wrapper_type" minOccurs="0"/>
     *       &lt;/choice>
     *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="sequence" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="conditionalAd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *       &lt;attribute name="adType">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *             &lt;enumeration value="video"/>
     *             &lt;enumeration value="audio"/>
     *             &lt;enumeration value="hybrid"/>
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
    @XmlType(name = "", propOrder = {
        "inLine",
        "wrapper"
    })
    public static class Ad {

        @XmlElement(name = "InLine")
        protected InlineType inLine;
        @XmlElement(name = "Wrapper")
        protected WrapperType wrapper;
        @XmlAttribute(name = "id")
        protected String id;
        @XmlAttribute(name = "sequence")
        protected BigInteger sequence;
        @XmlAttribute(name = "conditionalAd")
        protected Boolean conditionalAd;
        @XmlAttribute(name = "adType")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String adType;

        /**
         * Gets the value of the inLine property.
         * 
         * @return
         *     possible object is
         *     {@link InlineType }
         *     
         */
        public InlineType getInLine() {
            return inLine;
        }

        /**
         * Sets the value of the inLine property.
         * 
         * @param value
         *     allowed object is
         *     {@link InlineType }
         *     
         */
        public void setInLine(InlineType value) {
            this.inLine = value;
        }

        /**
         * Gets the value of the wrapper property.
         * 
         * @return
         *     possible object is
         *     {@link WrapperType }
         *     
         */
        public WrapperType getWrapper() {
            return wrapper;
        }

        /**
         * Sets the value of the wrapper property.
         * 
         * @param value
         *     allowed object is
         *     {@link WrapperType }
         *     
         */
        public void setWrapper(WrapperType value) {
            this.wrapper = value;
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
         * Gets the value of the sequence property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getSequence() {
            return sequence;
        }

        /**
         * Sets the value of the sequence property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setSequence(BigInteger value) {
            this.sequence = value;
        }

        /**
         * Gets the value of the conditionalAd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isConditionalAd() {
            return conditionalAd;
        }

        /**
         * Sets the value of the conditionalAd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setConditionalAd(Boolean value) {
            this.conditionalAd = value;
        }

        /**
         * Gets the value of the adType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdType() {
            return adType;
        }

        /**
         * Sets the value of the adType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdType(String value) {
            this.adType = value;
        }

    }

}
