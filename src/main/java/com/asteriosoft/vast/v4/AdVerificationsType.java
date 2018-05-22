package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdVerifications_type", propOrder = "verification")
public class AdVerificationsType {

    @XmlElement(name = "Verification")
    protected List<AdVerificationsType.Verification> verification;

    public List<AdVerificationsType.Verification> getVerification() {
        if (verification == null) {
            verification = new ArrayList<>();
        }
        return verification;
    }

    public void setVerification(List<Verification> verification) {
        this.verification = verification;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "javaScriptResource",
            "flashResource",
            "viewableImpression"
    })
    public static class Verification {

        @XmlElement(name = "JavaScriptResource")
        protected AdVerificationsType.Verification.JavaScriptResource javaScriptResource;
        @XmlElement(name = "FlashResource")
        protected AdVerificationsType.Verification.FlashResource flashResource;
        @XmlElement(name = "ViewableImpression")
        protected AnyURIType viewableImpression;
        @XmlAttribute
        @XmlSchemaType(name = "anyURI")
        protected String vendor;

        public AdVerificationsType.Verification.JavaScriptResource getJavaScriptResource() {
            return javaScriptResource;
        }

        public void setJavaScriptResource(AdVerificationsType.Verification.JavaScriptResource javaScriptResource) {
            this.javaScriptResource = javaScriptResource;
        }

        public AdVerificationsType.Verification.FlashResource getFlashResource() {
            return flashResource;
        }

        public void setFlashResource(AdVerificationsType.Verification.FlashResource flashResource) {
            this.flashResource = flashResource;
        }

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

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = "value")
        public static class FlashResource {

            @XmlValue
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String value;
            @XmlAttribute
            protected String apiFramework;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getApiFramework() {
                return apiFramework;
            }

            public void setApiFramework(String apiFramework) {
                this.apiFramework = apiFramework;
            }

        }

        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = "value")
        public static class JavaScriptResource {

            @XmlValue
            @XmlSchemaType(name = "anyURI")
            @XmlCDATA
            protected String value;
            @XmlAttribute
            protected String apiFramework;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getApiFramework() {
                return apiFramework;
            }

            public void setApiFramework(String apiFramework) {
                this.apiFramework = apiFramework;
            }

        }

    }

}
