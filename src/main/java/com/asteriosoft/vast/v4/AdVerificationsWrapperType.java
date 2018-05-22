package com.asteriosoft.vast.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdVerificationsWrapper_type", propOrder = "verification")
public class AdVerificationsWrapperType {

    @XmlElement(name = "Verification")
    protected List<AdVerificationsWrapperType.Verification> verification;

    public List<AdVerificationsWrapperType.Verification> getVerification() {
        if (verification == null) {
            verification = new ArrayList<>();
        }
        return verification;
    }

    public void setVerification(List<Verification> verification) {
        this.verification = verification;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "viewableImpression")
    public static class Verification {

        @XmlElement(name = "ViewableImpression")
        protected AnyURIType viewableImpression;
        @XmlAttribute
        @XmlSchemaType(name = "anyURI")
        protected String vendor;

        public AnyURIType getViewableImpression() {
            return viewableImpression;
        }

        public void setViewableImpression(AnyURIType viewableImpression) {
            this.viewableImpression = viewableImpression;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

    }

}
