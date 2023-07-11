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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Video formatted ad that plays linearly
 * 
 * <p>Java class for Linear_Inline_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Linear_Inline_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}Linear_Base_type">
 *       &lt;sequence>
 *         &lt;element name="AdParameters" type="{http://www.iab.com/VAST}AdParameters_type" minOccurs="0"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="MediaFiles">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ClosedCaptionFiles" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="ClosedCaptionFile" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                                     &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="MediaFile" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="delivery" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="streaming"/>
 *                                 &lt;enumeration value="progressive"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="bitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="minBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="maxBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="scalable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="maintainAspectRatio" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                           &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Mezzanine" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="delivery" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *                                 &lt;enumeration value="streaming"/>
 *                                 &lt;enumeration value="progressive"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                           &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="InteractiveCreativeFile" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="variableDuration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="VideoClicks" type="{http://www.iab.com/VAST}VideoClicks_type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Linear_Inline_type", propOrder = {
    "adParameters",
    "duration",
    "mediaFiles",
    "videoClicks"
})
public class LinearInlineType
    extends LinearBaseType
{

    @XmlElement(name = "AdParameters")
    protected AdParametersType adParameters;
    @XmlElement(name = "Duration", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar duration;
    @XmlElement(name = "MediaFiles", required = true)
    protected LinearInlineType.MediaFiles mediaFiles;
    @XmlElement(name = "VideoClicks")
    protected VideoClicksType videoClicks;

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
     * Gets the value of the mediaFiles property.
     * 
     * @return
     *     possible object is
     *     {@link LinearInlineType.MediaFiles }
     *     
     */
    public LinearInlineType.MediaFiles getMediaFiles() {
        return mediaFiles;
    }

    /**
     * Sets the value of the mediaFiles property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinearInlineType.MediaFiles }
     *     
     */
    public void setMediaFiles(LinearInlineType.MediaFiles value) {
        this.mediaFiles = value;
    }

    /**
     * Gets the value of the videoClicks property.
     * 
     * @return
     *     possible object is
     *     {@link VideoClicksType }
     *     
     */
    public VideoClicksType getVideoClicks() {
        return videoClicks;
    }

    /**
     * Sets the value of the videoClicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoClicksType }
     *     
     */
    public void setVideoClicks(VideoClicksType value) {
        this.videoClicks = value;
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
     *         &lt;element name="ClosedCaptionFiles" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="ClosedCaptionFile" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="MediaFile" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="delivery" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="streaming"/>
     *                       &lt;enumeration value="progressive"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="bitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="minBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="maxBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="scalable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="maintainAspectRatio" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *                 &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Mezzanine" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="delivery" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
     *                       &lt;enumeration value="streaming"/>
     *                       &lt;enumeration value="progressive"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *                 &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="InteractiveCreativeFile" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="variableDuration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    @XmlType(name = "", propOrder = {
        "closedCaptionFiles",
        "mediaFile",
        "mezzanine",
        "interactiveCreativeFile"
    })
    public static class MediaFiles {

        @XmlElement(name = "ClosedCaptionFiles")
        protected LinearInlineType.MediaFiles.ClosedCaptionFiles closedCaptionFiles;
        @XmlElement(name = "MediaFile", required = true)
        protected List<LinearInlineType.MediaFiles.MediaFile> mediaFile;
        @XmlElement(name = "Mezzanine")
        protected List<LinearInlineType.MediaFiles.Mezzanine> mezzanine;
        @XmlElement(name = "InteractiveCreativeFile")
        protected List<LinearInlineType.MediaFiles.InteractiveCreativeFile> interactiveCreativeFile;

        /**
         * Gets the value of the closedCaptionFiles property.
         * 
         * @return
         *     possible object is
         *     {@link LinearInlineType.MediaFiles.ClosedCaptionFiles }
         *     
         */
        public LinearInlineType.MediaFiles.ClosedCaptionFiles getClosedCaptionFiles() {
            return closedCaptionFiles;
        }

        /**
         * Sets the value of the closedCaptionFiles property.
         * 
         * @param value
         *     allowed object is
         *     {@link LinearInlineType.MediaFiles.ClosedCaptionFiles }
         *     
         */
        public void setClosedCaptionFiles(LinearInlineType.MediaFiles.ClosedCaptionFiles value) {
            this.closedCaptionFiles = value;
        }

        /**
         * Gets the value of the mediaFile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mediaFile property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMediaFile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LinearInlineType.MediaFiles.MediaFile }
         * 
         * 
         */
        public List<LinearInlineType.MediaFiles.MediaFile> getMediaFile() {
            if (mediaFile == null) {
                mediaFile = new ArrayList<LinearInlineType.MediaFiles.MediaFile>();
            }
            return this.mediaFile;
        }

        /**
         * Gets the value of the mezzanine property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mezzanine property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMezzanine().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LinearInlineType.MediaFiles.Mezzanine }
         * 
         * 
         */
        public List<LinearInlineType.MediaFiles.Mezzanine> getMezzanine() {
            if (mezzanine == null) {
                mezzanine = new ArrayList<LinearInlineType.MediaFiles.Mezzanine>();
            }
            return this.mezzanine;
        }

        /**
         * Gets the value of the interactiveCreativeFile property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the interactiveCreativeFile property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getInteractiveCreativeFile().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LinearInlineType.MediaFiles.InteractiveCreativeFile }
         * 
         * 
         */
        public List<LinearInlineType.MediaFiles.InteractiveCreativeFile> getInteractiveCreativeFile() {
            if (interactiveCreativeFile == null) {
                interactiveCreativeFile = new ArrayList<LinearInlineType.MediaFiles.InteractiveCreativeFile>();
            }
            return this.interactiveCreativeFile;
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
         *         &lt;element name="ClosedCaptionFile" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>anyURI">
         *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        @XmlType(name = "", propOrder = {
            "closedCaptionFile"
        })
        public static class ClosedCaptionFiles {

            @XmlElement(name = "ClosedCaptionFile")
            protected List<LinearInlineType.MediaFiles.ClosedCaptionFiles.ClosedCaptionFile> closedCaptionFile;

            /**
             * Gets the value of the closedCaptionFile property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the closedCaptionFile property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getClosedCaptionFile().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link LinearInlineType.MediaFiles.ClosedCaptionFiles.ClosedCaptionFile }
             * 
             * 
             */
            public List<LinearInlineType.MediaFiles.ClosedCaptionFiles.ClosedCaptionFile> getClosedCaptionFile() {
                if (closedCaptionFile == null) {
                    closedCaptionFile = new ArrayList<LinearInlineType.MediaFiles.ClosedCaptionFiles.ClosedCaptionFile>();
                }
                return this.closedCaptionFile;
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
             *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
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
            public static class ClosedCaptionFile {

                @XmlValue
                @XmlSchemaType(name = "anyURI")
                protected String value;
                @XmlAttribute(name = "type")
                protected String type;
                @XmlAttribute(name = "language")
                protected String language;

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

                /**
                 * Gets the value of the language property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getLanguage() {
                    return language;
                }

                /**
                 * Sets the value of the language property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setLanguage(String value) {
                    this.language = value;
                }

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
         *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="variableDuration" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
        public static class InteractiveCreativeFile {

            @XmlValue
            @XmlSchemaType(name = "anyURI")
            protected String value;
            @XmlAttribute(name = "type")
            protected String type;
            @XmlAttribute(name = "apiFramework")
            protected String apiFramework;
            @XmlAttribute(name = "variableDuration")
            protected Boolean variableDuration;

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
             * Gets the value of the variableDuration property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isVariableDuration() {
                return variableDuration;
            }

            /**
             * Sets the value of the variableDuration property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setVariableDuration(Boolean value) {
                this.variableDuration = value;
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
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="delivery" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="streaming"/>
         *             &lt;enumeration value="progressive"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="bitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="minBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="maxBitrate" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="scalable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="maintainAspectRatio" type="{http://www.w3.org/2001/XMLSchema}boolean" />
         *       &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="apiFramework" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        public static class MediaFile {

            @XmlValue
            @XmlSchemaType(name = "anyURI")
            protected String value;
            @XmlAttribute(name = "id")
            protected String id;
            @XmlAttribute(name = "delivery", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String delivery;
            @XmlAttribute(name = "type", required = true)
            protected String type;
            @XmlAttribute(name = "width", required = true)
            protected BigInteger width;
            @XmlAttribute(name = "height", required = true)
            protected BigInteger height;
            @XmlAttribute(name = "codec")
            protected String codec;
            @XmlAttribute(name = "bitrate")
            protected BigInteger bitrate;
            @XmlAttribute(name = "minBitrate")
            protected BigInteger minBitrate;
            @XmlAttribute(name = "maxBitrate")
            protected BigInteger maxBitrate;
            @XmlAttribute(name = "scalable")
            protected Boolean scalable;
            @XmlAttribute(name = "maintainAspectRatio")
            protected Boolean maintainAspectRatio;
            @XmlAttribute(name = "fileSize")
            protected BigInteger fileSize;
            @XmlAttribute(name = "mediaType")
            protected String mediaType;
            @XmlAttribute(name = "apiFramework")
            protected String apiFramework;

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

            /**
             * Gets the value of the delivery property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDelivery() {
                return delivery;
            }

            /**
             * Sets the value of the delivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDelivery(String value) {
                this.delivery = value;
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
             * Gets the value of the codec property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodec() {
                return codec;
            }

            /**
             * Sets the value of the codec property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodec(String value) {
                this.codec = value;
            }

            /**
             * Gets the value of the bitrate property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getBitrate() {
                return bitrate;
            }

            /**
             * Sets the value of the bitrate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setBitrate(BigInteger value) {
                this.bitrate = value;
            }

            /**
             * Gets the value of the minBitrate property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getMinBitrate() {
                return minBitrate;
            }

            /**
             * Sets the value of the minBitrate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setMinBitrate(BigInteger value) {
                this.minBitrate = value;
            }

            /**
             * Gets the value of the maxBitrate property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getMaxBitrate() {
                return maxBitrate;
            }

            /**
             * Sets the value of the maxBitrate property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setMaxBitrate(BigInteger value) {
                this.maxBitrate = value;
            }

            /**
             * Gets the value of the scalable property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isScalable() {
                return scalable;
            }

            /**
             * Sets the value of the scalable property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setScalable(Boolean value) {
                this.scalable = value;
            }

            /**
             * Gets the value of the maintainAspectRatio property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isMaintainAspectRatio() {
                return maintainAspectRatio;
            }

            /**
             * Sets the value of the maintainAspectRatio property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setMaintainAspectRatio(Boolean value) {
                this.maintainAspectRatio = value;
            }

            /**
             * Gets the value of the fileSize property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getFileSize() {
                return fileSize;
            }

            /**
             * Sets the value of the fileSize property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setFileSize(BigInteger value) {
                this.fileSize = value;
            }

            /**
             * Gets the value of the mediaType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMediaType() {
                return mediaType;
            }

            /**
             * Sets the value of the mediaType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMediaType(String value) {
                this.mediaType = value;
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
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="delivery" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
         *             &lt;enumeration value="streaming"/>
         *             &lt;enumeration value="progressive"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="width" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="height" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="codec" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="fileSize" type="{http://www.w3.org/2001/XMLSchema}integer" />
         *       &lt;attribute name="mediaType" type="{http://www.w3.org/2001/XMLSchema}string" />
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
        public static class Mezzanine {

            @XmlValue
            @XmlSchemaType(name = "anyURI")
            protected String value;
            @XmlAttribute(name = "id")
            protected String id;
            @XmlAttribute(name = "delivery", required = true)
            @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
            protected String delivery;
            @XmlAttribute(name = "type", required = true)
            protected String type;
            @XmlAttribute(name = "width", required = true)
            protected BigInteger width;
            @XmlAttribute(name = "height", required = true)
            protected BigInteger height;
            @XmlAttribute(name = "codec")
            protected String codec;
            @XmlAttribute(name = "fileSize")
            protected BigInteger fileSize;
            @XmlAttribute(name = "mediaType")
            protected String mediaType;

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

            /**
             * Gets the value of the delivery property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDelivery() {
                return delivery;
            }

            /**
             * Sets the value of the delivery property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDelivery(String value) {
                this.delivery = value;
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
             * Gets the value of the codec property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCodec() {
                return codec;
            }

            /**
             * Sets the value of the codec property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCodec(String value) {
                this.codec = value;
            }

            /**
             * Gets the value of the fileSize property.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getFileSize() {
                return fileSize;
            }

            /**
             * Sets the value of the fileSize property.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setFileSize(BigInteger value) {
                this.fileSize = value;
            }

            /**
             * Gets the value of the mediaType property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMediaType() {
                return mediaType;
            }

            /**
             * Sets the value of the mediaType property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMediaType(String value) {
                this.mediaType = value;
            }

        }

    }

}
