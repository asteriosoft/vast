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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Icon_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Icon_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}CreativeResource_type">
 *       &lt;sequence>
 *         &lt;element name="IconClicks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IconClickFallbackImages" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="IconClickFallbackImage" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="AltText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                       &lt;element name="StaticResource" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                     &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                     &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="IconClickThrough" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *                   &lt;element name="IconClickTracking" type="{http://www.iab.com/VAST}IconClickTracking_type" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IconViewTracking" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="program" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="xPosition">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="([0-9]*|left|right)"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="yPosition">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;pattern value="([0-9]*|top|bottom)"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="offset" type="{http://www.w3.org/2001/XMLSchema}time" />
 *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pxratio" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Icon_type", propOrder = {
    "iconClicks",
    "iconViewTracking"
})
public class IconType
    extends CreativeResourceType
{

    @XmlElement(name = "IconClicks")
    protected IconType.IconClicks iconClicks;
    @XmlElement(name = "IconViewTracking")
    @XmlSchemaType(name = "anyURI")
    protected List<String> iconViewTracking;
    @XmlAttribute(name = "program")
    protected String program;
    @XmlAttribute(name = "width")
    protected BigInteger width;
    @XmlAttribute(name = "height")
    protected BigInteger height;
    @XmlAttribute(name = "xPosition")
    protected String xPosition;
    @XmlAttribute(name = "yPosition")
    protected String yPosition;
    @XmlAttribute(name = "duration")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar duration;
    @XmlAttribute(name = "offset")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar offset;
    @XmlAttribute(name = "apiFramework")
    protected String apiFramework;
    @XmlAttribute(name = "pxratio")
    protected BigDecimal pxratio;

    /**
     * Gets the value of the iconClicks property.
     * 
     * @return
     *     possible object is
     *     {@link IconType.IconClicks }
     *     
     */
    public IconType.IconClicks getIconClicks() {
        return iconClicks;
    }

    /**
     * Sets the value of the iconClicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link IconType.IconClicks }
     *     
     */
    public void setIconClicks(IconType.IconClicks value) {
        this.iconClicks = value;
    }

    /**
     * Gets the value of the iconViewTracking property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the iconViewTracking property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIconViewTracking().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getIconViewTracking() {
        if (iconViewTracking == null) {
            iconViewTracking = new ArrayList<String>();
        }
        return this.iconViewTracking;
    }

    /**
     * Gets the value of the program property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgram() {
        return program;
    }

    /**
     * Sets the value of the program property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgram(String value) {
        this.program = value;
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
     * Gets the value of the xPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPosition() {
        return xPosition;
    }

    /**
     * Sets the value of the xPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPosition(String value) {
        this.xPosition = value;
    }

    /**
     * Gets the value of the yPosition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYPosition() {
        return yPosition;
    }

    /**
     * Sets the value of the yPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYPosition(String value) {
        this.yPosition = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDuration(XMLGregorianCalendar value) {
        this.duration = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOffset(XMLGregorianCalendar value) {
        this.offset = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="IconClickFallbackImages" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="IconClickFallbackImage" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="AltText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                             &lt;element name="StaticResource" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
     *                           &lt;/sequence>
     *                           &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                           &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="IconClickThrough" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
     *         &lt;element name="IconClickTracking" type="{http://www.iab.com/VAST}IconClickTracking_type" maxOccurs="unbounded" minOccurs="0"/>
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
        "iconClickFallbackImages",
        "iconClickThrough",
        "iconClickTracking"
    })
    public static class IconClicks {

        @XmlElement(name = "IconClickFallbackImages")
        protected IconType.IconClicks.IconClickFallbackImages iconClickFallbackImages;
        @XmlElement(name = "IconClickThrough")
        @XmlSchemaType(name = "anyURI")
        protected String iconClickThrough;
        @XmlElement(name = "IconClickTracking")
        protected List<IconClickTrackingType> iconClickTracking;

        /**
         * Gets the value of the iconClickFallbackImages property.
         * 
         * @return
         *     possible object is
         *     {@link IconType.IconClicks.IconClickFallbackImages }
         *     
         */
        public IconType.IconClicks.IconClickFallbackImages getIconClickFallbackImages() {
            return iconClickFallbackImages;
        }

        /**
         * Sets the value of the iconClickFallbackImages property.
         * 
         * @param value
         *     allowed object is
         *     {@link IconType.IconClicks.IconClickFallbackImages }
         *     
         */
        public void setIconClickFallbackImages(IconType.IconClicks.IconClickFallbackImages value) {
            this.iconClickFallbackImages = value;
        }

        /**
         * Gets the value of the iconClickThrough property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIconClickThrough() {
            return iconClickThrough;
        }

        /**
         * Sets the value of the iconClickThrough property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIconClickThrough(String value) {
            this.iconClickThrough = value;
        }

        /**
         * Gets the value of the iconClickTracking property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the iconClickTracking property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIconClickTracking().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link IconClickTrackingType }
         * 
         * 
         */
        public List<IconClickTrackingType> getIconClickTracking() {
            if (iconClickTracking == null) {
                iconClickTracking = new ArrayList<IconClickTrackingType>();
            }
            return this.iconClickTracking;
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
         *         &lt;element name="IconClickFallbackImage" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="AltText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *                   &lt;element name="StaticResource" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
         *                 &lt;/sequence>
         *                 &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *                 &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
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
        @XmlType(name = "", propOrder = {
            "iconClickFallbackImage"
        })
        public static class IconClickFallbackImages {

            @XmlElement(name = "IconClickFallbackImage", required = true)
            protected List<IconType.IconClicks.IconClickFallbackImages.IconClickFallbackImage> iconClickFallbackImage;

            /**
             * Gets the value of the iconClickFallbackImage property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the iconClickFallbackImage property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getIconClickFallbackImage().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link IconType.IconClicks.IconClickFallbackImages.IconClickFallbackImage }
             * 
             * 
             */
            public List<IconType.IconClicks.IconClickFallbackImages.IconClickFallbackImage> getIconClickFallbackImage() {
                if (iconClickFallbackImage == null) {
                    iconClickFallbackImage = new ArrayList<IconType.IconClicks.IconClickFallbackImages.IconClickFallbackImage>();
                }
                return this.iconClickFallbackImage;
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
             *         &lt;element name="AltText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
             *         &lt;element name="StaticResource" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
             *       &lt;/sequence>
             *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *       &lt;attribute name="width" type="{http://www.w3.org/2001/XMLSchema}integer" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "altText",
                "staticResource"
            })
            public static class IconClickFallbackImage {

                @XmlElement(name = "AltText")
                protected String altText;
                @XmlElement(name = "StaticResource")
                @XmlSchemaType(name = "anyURI")
                protected String staticResource;
                @XmlAttribute(name = "height")
                protected BigInteger height;
                @XmlAttribute(name = "width")
                protected BigInteger width;

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
                 * Gets the value of the staticResource property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStaticResource() {
                    return staticResource;
                }

                /**
                 * Sets the value of the staticResource property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStaticResource(String value) {
                    this.staticResource = value;
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

            }

        }

    }

}
