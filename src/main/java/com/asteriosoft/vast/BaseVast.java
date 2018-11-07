package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.BiFunction;

abstract class BaseVast<V> {

    private static final char[] XML_NS = " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"\"".toCharArray();
    private static final char[] EXTENSIONS_START = "<Extensions>".toCharArray();
    private static final char[] EXTENSIONS_END = "</Extensions>".toCharArray();
    private static final String SYMBOL_PLUS = "+";
    private static final String SYMBOL_PLUS_ENCODED = "%20";

    static class InjectTrackersResult {
        final boolean linearTracksAdded;
        final boolean linearClickUrlsAdded;
        final boolean nonLinearTracksAdded;
        final boolean nonLinearClickUrlsAdded;

        public InjectTrackersResult(boolean linearTracksAdded, boolean linearClickUrlsAdded, boolean nonLinearTracksAdded, boolean nonLinearClickUrlsAdded) {
            super();
            this.linearTracksAdded = linearTracksAdded;
            this.linearClickUrlsAdded = linearClickUrlsAdded;
            this.nonLinearTracksAdded = nonLinearTracksAdded;
            this.nonLinearClickUrlsAdded = nonLinearClickUrlsAdded;
        }
    }

    private static String replaceAmpersandChar(String xml) {
        int max = xml.length();
        StringBuilder sb = new StringBuilder(max);

        boolean inCDATA = false;
        for (int pos = 0; pos < max; pos++) {
            char c = xml.charAt(pos);
            sb.append(c);

            if (c == '!') {
                if ((pos + 1 < max) && xml.charAt(pos + 1) == '[') {
                    if ((pos + 2 < max) && xml.charAt(pos + 2) == 'C') {
                        inCDATA = true;
                        continue;
                    }
                }
            }
            if (inCDATA && c == ']') {
                if ((pos + 1 < max) && xml.charAt(pos + 1) == ']') {
                    inCDATA = false;
                    continue;
                }
            }

            if (!inCDATA) {
                if (c == '&') {
                    if ((pos + 4 >= max) || xml.charAt(pos + 1) != 'a' || xml.charAt(pos + 2) != 'm' || xml.charAt(pos + 3) != 'p' || xml.charAt(pos + 4) != ';') {
                        sb.append("amp;");
                    }
                }
            }
        }

        return sb.toString();
    }

    private static boolean section(char[] chars, int pos, char[] tag) {
        if (pos + tag.length > chars.length) {
            return false;
        }
        for (int i = 0; i < tag.length; i++) {
            if (tag[i] != chars[i + pos]) {
                return false;
            }
        }
        return true;
    }

    private static String patchExtensions(String vast) {
        char[] chars = vast.toCharArray();
        int max = chars.length;
        StringBuilder sb = new StringBuilder(max);

        boolean inExtensions = false;
        for (int pos = 0; pos < max; pos++) {

            if (inExtensions) {
                if (section(chars, pos, XML_NS)) {
                    pos += XML_NS.length - 1;
                    continue;
                }
            }

            char c = chars[pos];
            sb.append(c);

            if (c == '<') {
                if (section(chars, pos, EXTENSIONS_START)) {
                    inExtensions = true;
                    continue;
                }

                if (section(chars, pos, EXTENSIONS_END)) {
                    inExtensions = false;
                }
            }
        }

        return sb.toString();
    }

    static String encodeUrl(String s) {
        if (StringUtils.isEmpty(s)) {
            return StringUtils.EMPTY;
        }
        try {
            return StringUtils.replace(URLEncoder.encode(s, StandardCharsets.UTF_8.name()), SYMBOL_PLUS, SYMBOL_PLUS_ENCODED);
        } catch (Exception e) {
            return s;
        }
    }

    @SuppressWarnings("unchecked")
    static <T> T get(String key, Map<String, ?> data) {
        return (T) data.get(key);
    }

    public String marshal(V vast) throws XmlMarshalingException {
        return patchExtensions(getXmlMarshaling().marshal(vast));
    }

    public V unmarshal(String str) throws XmlMarshalingException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        str = replaceAmpersandChar(str);
        return (V) getXmlMarshaling().unmarshal(str, getVastClass());
    }

    public void addVastTrackingUrls(V vast, VastTrackers trackers) throws XmlMarshalingException {
        injectTracking(vast, trackers);
    }

    public String transform(String str, VastTransformationInfo info) throws XmlMarshalingException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        V vast = unmarshal(str);
        if (info.getTrackers() != null) {
            addVastTrackingUrls(vast, info.getTrackers());
        }
        if (info.getHtmlContentApplier() != null) {
            convertAllResourcesToHTML(vast, info.getHtmlContentApplier());
        }
        return marshal(vast);
    }

    protected String createImg(String src, Number height, Number width) {
        StringBuilder sb = new StringBuilder(60 + src.length());
        if (width != null) {
            sb.append("<img width=\"").append(width);
        }
        if (height != null) {
            sb.append("\" height=\"").append(height);
        }
        return sb.append("\" src=\"").append(src)
                .append("\" border=\"0\"/>").toString();
    }

    protected String createIframe(String src, Number height, Number width) {
        StringBuilder sb = new StringBuilder(120 + src.length());
        if (width != null) {
            sb.append("<iframe width=\"").append(width);
        }
        if (height != null) {
            sb.append("\" height=\"").append(height);
        }

        return sb.append("\" scrolling=No frameborder=0 marginheight=0 marginwidth=0  src=\"")
                .append(src).append("\" />").toString();
    }

    protected abstract void convertAllResourcesToHTML(V vast, BiFunction<String, String, String> htmlContentApplier);

    protected abstract VastVersion getVastVersion();

    protected abstract void injectTracking(V vast, VastTrackers trackers) throws XmlMarshalingException;

    protected abstract XmlMarshaling getXmlMarshaling();

    protected abstract Class<?> getVastClass();

}
