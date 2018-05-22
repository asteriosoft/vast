package com.asteriosoft.vast;

import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

abstract class BaseVastHelper {

    private static final char[] XML_NS = " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"\"".toCharArray();
    private static final char[] EXTENSIONS_START = "<Extensions>".toCharArray();
    private static final char[] EXTENSIONS_END = "</Extensions>".toCharArray();
    private static final String SYMBOL_PLUS = "+";
    private static final String SYMBOL_PLUS_ENCODED = "%20";

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

    String marshal(Object vast, XmlMarshaling xmlMarshaling) throws XmlMarshalingException {
        return xmlMarshaling.marshal(vast);
    }

    <T> T unmarshal(String str, XmlMarshaling xmlMarshaling, Class<T> clazz) throws XmlMarshalingException {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        str = replaceAmpersandChar(str);
        return xmlMarshaling.unmarshal(str, clazz);
    }

    String addVastTrackingUrls(String vastStr, Map<String, Object> vastdata) throws XmlMarshalingException {
        String result = injectTracking(vastStr, vastdata);
        return patchExtensions(result);
    }

    protected abstract VastVersion getVastVersion();

    protected abstract String injectTracking(String vastStr, Map<String, Object> vastdata) throws XmlMarshalingException;
}
