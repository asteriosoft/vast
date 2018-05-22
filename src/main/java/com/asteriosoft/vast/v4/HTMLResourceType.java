package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HTMLResource_type", propOrder = "value")
public class HTMLResourceType {

    @XmlValue
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String value;
    @XmlAttribute
    protected Boolean xmlEncoded;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean isXmlEncoded() {
        return xmlEncoded;
    }

    public void setXmlEncoded(Boolean xmlEncoded) {
        this.xmlEncoded = xmlEncoded;
    }

}
