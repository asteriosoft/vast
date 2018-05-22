package com.asteriosoft.vast;

import org.apache.commons.collections4.MapUtils;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class XmlMarshaling {

    private final Map<String, Object> marshallerProperties;

    private final JAXBContext jaxbContext;
    private final XMLInputFactory xmlInputFactory;
    private final XMLOutputFactory xmlOutputFactory;
    private final ThreadLocal<Unmarshaller> unmarshallerThreadLocal = new ThreadLocal<>();
    private final ThreadLocal<Marshaller> marshallerThreadLocal = new ThreadLocal<>();

    public XmlMarshaling(Class<?>... classesToBeBound) {
        this(null, null, classesToBeBound);
    }

    private XmlMarshaling(Map<String, Object> jaxbContextProperties, Map<String, Object> marshallerProperties, Class<?>... classesToBeBound) {
        xmlInputFactory = XMLInputFactory.newInstance();
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
            final XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(input));
            return getUnmarshaller().unmarshal(xmlStreamReader, clazz).getValue();
        } catch (Exception e) {
            throw new XmlMarshalingException(e);
        }
    }
}
