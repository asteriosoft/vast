package com.asteriosoft.vast.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VideoClicksWrapper_type", propOrder = {
        "clickTracking",
        "customClick"
})
public class VideoClicksWrapperType {

    @XmlElement(name = "ClickTracking")
    protected List<AnyURIType> clickTracking;
    @XmlElement(name = "CustomClick")
    protected List<AnyURIType> customClick;

    public List<AnyURIType> getClickTracking() {
        if (clickTracking == null) {
            clickTracking = new ArrayList<>();
        }
        return clickTracking;
    }

    public void setClickTracking(List<AnyURIType> clickTracking) {
        this.clickTracking = clickTracking;
    }

    public List<AnyURIType> getCustomClick() {
        if (customClick == null) {
            customClick = new ArrayList<>();
        }
        return customClick;
    }

    public void setCustomClick(List<AnyURIType> customClick) {
        this.customClick = customClick;
    }
}
