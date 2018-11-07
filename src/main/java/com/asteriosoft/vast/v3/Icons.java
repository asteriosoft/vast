package com.asteriosoft.vast.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = "icon")
public final class Icons {

    @XmlElement(name = "Icon", required = true)
    protected List<IconType> icon;

    public List<IconType> getIcon() {
        if (icon == null) {
            icon = new ArrayList<>();
        }
        return icon;
    }

    public void setIcon(List<IconType> icon) {
        this.icon = icon;
    }
}