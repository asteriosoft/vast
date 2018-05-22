package com.asteriosoft.vast.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreativeExtensions_type", propOrder = "creativeExtension")
public class CreativeExtensionsType {

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
    public static class CreativeExtension {

        @XmlAnyElement(lax = true)
        protected List<Object> any;
        @XmlAnyAttribute
        private Map<QName, String> otherAttributes = new HashMap<>();

        public List<Object> getAny() {
            if (any == null) {
                any = new ArrayList<>();
            }
            return any;
        }

        public void setAny(List<Object> any) {
            this.any = any;
        }

        public Map<QName, String> getOtherAttributes() {
            return otherAttributes;
        }

        public void setOtherAttributes(Map<QName, String> otherAttributes) {
            this.otherAttributes = otherAttributes;
        }
    }

}
