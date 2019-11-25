package com.asteriosoft.vast.v41;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The AdVerification element is used to initiate a controlled container where code can be executed for collecting data to verify ad playback details.
 * 
 * <p>Java class for AdVerifications_type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AdVerifications_type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Verification" type="{http://www.iab.com/VAST}Verification_type" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdVerifications_type", propOrder = {
    "verification"
})
public class AdVerificationsType {

    @XmlElement(name = "Verification")
    protected List<VerificationType> verification;

    /**
     * Gets the value of the verification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerificationType }
     * 
     * 
     */
    public List<VerificationType> getVerification() {
        if (verification == null) {
            verification = new ArrayList<VerificationType>();
        }
        return this.verification;
    }

}
