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
@XmlType(name = "Extensions_type", propOrder = "extension")
public final class ExtensionsType {

    @XmlElement(name = "Extension")
    protected List<ExtensionsType.Extension> extension;

    public List<ExtensionsType.Extension> getExtension() {
        if (extension == null) {
            extension = new ArrayList<>();
        }
        return extension;
    }

    public void setExtension(List<Extension> extension) {
        this.extension = extension;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "any")
    public static final class Extension {

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
