package com.asteriosoft.vast.v41;

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
 * <p>Java class for Wrapper_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Wrapper_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}AdDefinitionBase_type">
 *       &lt;sequence>
 *         &lt;element name="AdVerifications" type="{http://www.iab.com/VAST}AdVerifications_type" minOccurs="0"/>
 *         &lt;element name="BlockedAdCategories" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="authority" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Creatives" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Creative" type="{http://www.iab.com/VAST}Creative_Wrapper_type" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="VASTAdTagURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *       &lt;attribute name="followAdditionalWrappers" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="allowMultipleAds" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="fallbackOnNoAd" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Wrapper_type", propOrder = {
    "adVerifications",
    "blockedAdCategories",
    "creatives",
    "vastAdTagURI"
})
public class WrapperType
    extends AdDefinitionBaseType
{

    @XmlElement(name = "AdVerifications")
    protected AdVerificationsType adVerifications;
    @XmlElement(name = "BlockedAdCategories")
    protected List<WrapperType.BlockedAdCategories> blockedAdCategories;
    @XmlElement(name = "Creatives")
    protected WrapperType.Creatives creatives;
    @XmlElement(name = "VASTAdTagURI", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String vastAdTagURI;
    @XmlAttribute(name = "followAdditionalWrappers")
    protected Boolean followAdditionalWrappers;
    @XmlAttribute(name = "allowMultipleAds")
    protected Boolean allowMultipleAds;
    @XmlAttribute(name = "fallbackOnNoAd")
    protected Boolean fallbackOnNoAd;

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
     * Gets the value of the blockedAdCategories property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the blockedAdCategories property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBlockedAdCategories().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WrapperType.BlockedAdCategories }
     * 
     * 
     */
    public List<WrapperType.BlockedAdCategories> getBlockedAdCategories() {
        if (blockedAdCategories == null) {
            blockedAdCategories = new ArrayList<WrapperType.BlockedAdCategories>();
        }
        return this.blockedAdCategories;
    }

    /**
     * Gets the value of the creatives property.
     * 
     * @return
     *     possible object is
     *     {@link WrapperType.Creatives }
     *     
     */
    public WrapperType.Creatives getCreatives() {
        return creatives;
    }

    /**
     * Sets the value of the creatives property.
     * 
     * @param value
     *     allowed object is
     *     {@link WrapperType.Creatives }
     *     
     */
    public void setCreatives(WrapperType.Creatives value) {
        this.creatives = value;
    }

    /**
     * Gets the value of the vastAdTagURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVASTAdTagURI() {
        return vastAdTagURI;
    }

    /**
     * Sets the value of the vastAdTagURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVASTAdTagURI(String value) {
        this.vastAdTagURI = value;
    }

    /**
     * Gets the value of the followAdditionalWrappers property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFollowAdditionalWrappers() {
        return followAdditionalWrappers;
    }

    /**
     * Sets the value of the followAdditionalWrappers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFollowAdditionalWrappers(Boolean value) {
        this.followAdditionalWrappers = value;
    }

    /**
     * Gets the value of the allowMultipleAds property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowMultipleAds() {
        return allowMultipleAds;
    }

    /**
     * Sets the value of the allowMultipleAds property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowMultipleAds(Boolean value) {
        this.allowMultipleAds = value;
    }

    /**
     * Gets the value of the fallbackOnNoAd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFallbackOnNoAd() {
        return fallbackOnNoAd;
    }

    /**
     * Sets the value of the fallbackOnNoAd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFallbackOnNoAd(Boolean value) {
        this.fallbackOnNoAd = value;
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
     *       &lt;attribute name="authority" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
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
    public static class BlockedAdCategories {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "authority")
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
     *         &lt;element name="Creative" type="{http://www.iab.com/VAST}Creative_Wrapper_type" maxOccurs="unbounded"/>
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
        protected List<CreativeWrapperType> creative;

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
         * {@link CreativeWrapperType }
         * 
         * 
         */
        public List<CreativeWrapperType> getCreative() {
            if (creative == null) {
                creative = new ArrayList<CreativeWrapperType>();
            }
            return this.creative;
        }

    }

}
