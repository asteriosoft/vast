package com.asteriosoft.vast.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreativeExtensions_type", propOrder = "creativeExtension")
public final class CreativeExtensionsType {

    @XmlElement(name = "CreativeExtension")
    protected List<CreativeExtensionsType.CreativeExtension> creativeExtension;

    public List<CreativeExtensionsType.CreativeExtension> getCreativeExtension() {
        if (creativeExtension == null) {
            creativeExtension = new ArrayList<>();
        }
        return creativeExtension;
    }

    public void setCreativeExtension(List<CreativeExtension> creativeExtension) {
        this.creativeExtension = creativeExtension;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "any")
    public static final class CreativeExtension {

        @XmlAnyElement(lax = true)
        protected List<Object> any;
        @XmlAttribute
        protected String type;

        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return any;
        }

        public void setAny(List<Object> any) {
            this.any = any;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}
