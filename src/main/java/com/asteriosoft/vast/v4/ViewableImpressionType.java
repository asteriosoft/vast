package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ViewableImpression_type", propOrder = {
        "viewable",
        "notViewable",
        "viewUndetermined"
})
public class ViewableImpressionType {

    @XmlElement(name = "Viewable")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> viewable;
    @XmlElement(name = "NotViewable")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> notViewable;
    @XmlElement(name = "ViewUndetermined")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected List<String> viewUndetermined;
    @XmlAttribute
    protected String id;

    public List<String> getViewable() {
        if (viewable == null) {
            viewable = new ArrayList<>();
        }
        return viewable;
    }

    public void setViewable(List<String> viewable) {
        this.viewable = viewable;
    }

    public List<String> getNotViewable() {
        if (notViewable == null) {
            notViewable = new ArrayList<>();
        }
        return notViewable;
    }

    public void setNotViewable(List<String> notViewable) {
        this.notViewable = notViewable;
    }

    public List<String> getViewUndetermined() {
        if (viewUndetermined == null) {
            viewUndetermined = new ArrayList<>();
        }
        return viewUndetermined;
    }

    public void setViewUndetermined(List<String> viewUndetermined) {
        this.viewUndetermined = viewUndetermined;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
