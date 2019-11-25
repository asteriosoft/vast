package com.asteriosoft.vast.v41;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Video formatted ad that plays linearly
 * 
 * <p>Java class for Linear_Wrapper_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Linear_Wrapper_type">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.iab.com/VAST}Linear_Base_type">
 *       &lt;sequence>
 *         &lt;element name="VideoClicks" type="{http://www.iab.com/VAST}VideoClicks_Base_type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Linear_Wrapper_type", propOrder = {
    "videoClicks"
})
public class LinearWrapperType
    extends LinearBaseType
{

    @XmlElement(name = "VideoClicks")
    protected VideoClicksBaseType videoClicks;

    /**
     * Gets the value of the videoClicks property.
     * 
     * @return
     *     possible object is
     *     {@link VideoClicksBaseType }
     *     
     */
    public VideoClicksBaseType getVideoClicks() {
        return videoClicks;
    }

    /**
     * Sets the value of the videoClicks property.
     * 
     * @param value
     *     allowed object is
     *     {@link VideoClicksBaseType }
     *     
     */
    public void setVideoClicks(VideoClicksBaseType value) {
        this.videoClicks = value;
    }

}
