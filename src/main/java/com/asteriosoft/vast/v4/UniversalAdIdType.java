package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UniversalAdId_type", propOrder = "value")
public class UniversalAdIdType {

    @XmlValue
    @XmlCDATA
    protected String value;
    @XmlAttribute(required = true)
    protected String idRegistry;
    @XmlAttribute(required = true)
    protected String idValue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIdRegistry() {
        return idRegistry;
    }

    public void setIdRegistry(String idRegistry) {
        this.idRegistry = idRegistry;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

}
