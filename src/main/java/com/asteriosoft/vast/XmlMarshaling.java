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

package com.asteriosoft.vast;

import org.apache.commons.collections4.MapUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class XmlMarshaling {

    private final Map<String, Object> marshallerProperties;

    private final JAXBContext jaxbContext;
    private final XMLOutputFactory xmlOutputFactory;
    private final ThreadLocal<Unmarshaller> unmarshallerThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Marshaller> marshallerThreadLocal = new ThreadLocal<>();

    public XmlMarshaling(Class<?>... classesToBeBound) {
        this(null, null, classesToBeBound);
    }

    private XmlMarshaling(Map<String, Object> jaxbContextProperties, Map<String, Object> marshallerProperties, Class<?>... classesToBeBound) {
        xmlOutputFactory = XMLOutputFactory.newInstance();
        this.marshallerProperties = marshallerProperties;

        try {
            jaxbContext = JAXBContextFactory.createContext(classesToBeBound, jaxbContextProperties);

            getMarshaller();
            getUnmarshaller();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Marshaller getMarshaller() throws JAXBException {
        Marshaller instance = marshallerThreadLocal.get();
        if (instance == null) {
            instance = createMarshaller();
            marshallerThreadLocal.set(instance);
        }
        return instance;
    }

    private Marshaller createMarshaller() throws JAXBException {
        Marshaller instance = jaxbContext.createMarshaller();
        if (MapUtils.isEmpty(marshallerProperties)) {
            instance.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
            instance.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
        } else {
            for (Map.Entry<String, Object> entry : marshallerProperties.entrySet()) {
                instance.setProperty(entry.getKey(), entry.getValue());
            }
        }
        return instance;
    }

    private Unmarshaller getUnmarshaller() throws JAXBException {
        Unmarshaller instance = unmarshallerThreadLocal.get();
        if (instance == null) {
            instance = createUnmarshaller();
            unmarshallerThreadLocal.set(instance);
        }
        return instance;
    }

    private Unmarshaller createUnmarshaller() throws JAXBException {
        return jaxbContext.createUnmarshaller();
    }

    public String marshal(Object jaxbElement) throws XmlMarshalingException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        marshal(jaxbElement, outputStream);
        return new String(outputStream.toByteArray());
    }

    private void marshal(Object jaxbElement, OutputStream outputStream) throws XmlMarshalingException {
        try {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStream);
            getMarshaller().marshal(jaxbElement, xmlStreamWriter);
        } catch (Exception e) {
            throw new XmlMarshalingException(e);
        }
    }

    public <T> T unmarshal(String input, Class<T> clazz) throws XmlMarshalingException {
        try {
            return getUnmarshaller().unmarshal(new StreamSource(new StringReader(input)), clazz).getValue();
        } catch (Exception e) {
            throw new XmlMarshalingException(e);
        }
    }
}
