package com.asteriosoft.vast.v4;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MediaFiles_type", propOrder = {
        "mezzanine",
        "mediaFile",
        "interactiveCreativeFile"
})
public final class MediaFilesType {

    @XmlElement(name = "Mezzanine")
    @XmlSchemaType(name = "anyURI")
    @XmlCDATA
    protected String mezzanine;
    @XmlElement(name = "MediaFile", required = true)
    protected List<MediaFilesType.MediaFile> mediaFile;
    @XmlElement(name = "InteractiveCreativeFile")
    protected List<InteractiveCreativeFileType> interactiveCreativeFile;

    public String getMezzanine() {
        return mezzanine;
    }

    public void setMezzanine(String mezzanine) {
        this.mezzanine = mezzanine;
    }

    public List<MediaFilesType.MediaFile> getMediaFile() {
        if (mediaFile == null) {
            mediaFile = new ArrayList<>();
        }
        return mediaFile;
    }

    public void setMediaFile(List<MediaFile> mediaFile) {
        this.mediaFile = mediaFile;
    }

    public List<InteractiveCreativeFileType> getInteractiveCreativeFile() {
        if (interactiveCreativeFile == null) {
            interactiveCreativeFile = new ArrayList<>();
        }
        return interactiveCreativeFile;
    }

    public void setInteractiveCreativeFile(List<InteractiveCreativeFileType> interactiveCreativeFile) {
        this.interactiveCreativeFile = interactiveCreativeFile;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = "value")
    public static class MediaFile {

        @XmlValue
        @XmlSchemaType(name = "anyURI")
        @XmlCDATA
        protected String value;
        @XmlAttribute
        protected String id;
        @XmlAttribute(required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        protected String delivery;
        @XmlAttribute(required = true)
        protected String type;
        @XmlAttribute
        protected BigInteger bitrate;
        @XmlAttribute
        protected BigInteger minBitrate;
        @XmlAttribute
        protected BigInteger maxBitrate;
        @XmlAttribute(required = true)
        protected BigInteger width;
        @XmlAttribute(required = true)
        protected BigInteger height;
        @XmlAttribute
        protected Boolean scalable;
        @XmlAttribute
        protected Boolean maintainAspectRatio;
        @XmlAttribute
        protected String apiFramework;
        @XmlAttribute
        protected String codec;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigInteger getBitrate() {
            return bitrate;
        }

        public void setBitrate(BigInteger bitrate) {
            this.bitrate = bitrate;
        }

        public BigInteger getMinBitrate() {
            return minBitrate;
        }

        public void setMinBitrate(BigInteger minBitrate) {
            this.minBitrate = minBitrate;
        }

        public BigInteger getMaxBitrate() {
            return maxBitrate;
        }

        public void setMaxBitrate(BigInteger maxBitrate) {
            this.maxBitrate = maxBitrate;
        }

        public BigInteger getWidth() {
            return width;
        }

        public void setWidth(BigInteger width) {
            this.width = width;
        }

        public BigInteger getHeight() {
            return height;
        }

        public void setHeight(BigInteger height) {
            this.height = height;
        }

        public Boolean isScalable() {
            return scalable;
        }

        public void setScalable(Boolean scalable) {
            this.scalable = scalable;
        }

        public Boolean isMaintainAspectRatio() {
            return maintainAspectRatio;
        }

        public void setMaintainAspectRatio(Boolean maintainAspectRatio) {
            this.maintainAspectRatio = maintainAspectRatio;
        }

        public String getApiFramework() {
            return apiFramework;
        }

        public void setApiFramework(String apiFramework) {
            this.apiFramework = apiFramework;
        }

        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

    }

}
