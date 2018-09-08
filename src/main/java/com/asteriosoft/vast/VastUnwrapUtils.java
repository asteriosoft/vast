package com.asteriosoft.vast;

import com.asteriosoft.vast.v2.VAST;
import com.asteriosoft.vast.v4.AnyURIType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VastUnwrapUtils extends VastUtils {

    public static Object parseVast(String vast) throws VastUnwrapException {
        try {
            vast = cutVast(vast);
            BaseVastHelper vastHelper = getVastHelper(vast);
            return vastHelper == null
                    ? null
                    : vastHelper.unmarshal(vast);
        } catch (Exception err) {
            throw new VastUnwrapException(err);
        }
    }

    public static VastWrapperInfo getWrapperInfo(Object vast) {
        if (vast instanceof com.asteriosoft.vast.v2.VAST) {
            List<com.asteriosoft.vast.v2.VAST.Ad> adList = ((com.asteriosoft.vast.v2.VAST) vast).getAd();
            if (CollectionUtils.isEmpty(adList)) {
                return VastWrapperInfo.NO_ADS;
            }
            if (adList.size() > 1) {
                return VastWrapperInfo.MULTIPLE_ADS;
            }
            com.asteriosoft.vast.v2.VAST.Ad vast2Ad = adList.get(0);
            VAST.Ad.Wrapper wrapper = vast2Ad.getWrapper();
            if (wrapper == null) {
                return VastWrapperInfo.NOT_WRAPPER;
            }
            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
                return VastWrapperInfo.CREATIVES;
            }
            String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
            if (isUrl(vastAdTagURI)) {
                List<String> impressions = wrapper.getImpression();
                List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());

                return new VastWrapperInfo(vastAdTagURI, createTrackersMap(impressions, errors));
            } else {
                return VastWrapperInfo.EMPTY_VASTTAGURI;
            }
        } else if (vast instanceof com.asteriosoft.vast.v3.VAST) {
            List<com.asteriosoft.vast.v3.VAST.Ad> adList = ((com.asteriosoft.vast.v3.VAST) vast).getAd();
            if (CollectionUtils.isEmpty(adList)) {
                return VastWrapperInfo.NO_ADS;
            }
            if (adList.size() > 1) {
                return VastWrapperInfo.MULTIPLE_ADS;
            }
            com.asteriosoft.vast.v3.VAST.Ad vast3Ad = adList.get(0);
            com.asteriosoft.vast.v3.VAST.Ad.Wrapper wrapper = vast3Ad.getWrapper();
            if (wrapper == null) {
                return VastWrapperInfo.NOT_WRAPPER;
            }
            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
                return VastWrapperInfo.CREATIVES;
            }
            String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
            if (isUrl(vastAdTagURI)) {
                List<String> impressions = wrapper.getImpression();
                List<String> errors = StringUtils.isBlank(wrapper.getError()) ? Collections.emptyList() : Collections.singletonList(wrapper.getError());

                return new VastWrapperInfo(vastAdTagURI, createTrackersMap(impressions, errors));
            } else {
                return VastWrapperInfo.EMPTY_VASTTAGURI;
            }
        } else if (vast instanceof com.asteriosoft.vast.v4.VAST) {
            List<com.asteriosoft.vast.v4.VAST.Ad> adList = ((com.asteriosoft.vast.v4.VAST) vast).getAd();
            if (CollectionUtils.isEmpty(adList)) {
                return VastWrapperInfo.NO_ADS;
            }
            if (adList.size() > 1) {
                return VastWrapperInfo.MULTIPLE_ADS;
            }
            com.asteriosoft.vast.v4.VAST.Ad vast4Ad = adList.get(0);
            com.asteriosoft.vast.v4.VAST.Ad.Wrapper wrapper = vast4Ad.getWrapper();
            if (wrapper == null) {
                return VastWrapperInfo.NOT_WRAPPER;
            }
            if (wrapper.getCreatives() != null && CollectionUtils.isNotEmpty(wrapper.getCreatives().getCreative())) {
                return VastWrapperInfo.CREATIVES;
            }
            String vastAdTagURI = StringUtils.trimToEmpty(wrapper.getVASTAdTagURI());
            if (isUrl(vastAdTagURI)) {

                List<String> impressions = null;
                List<AnyURIType> anyURITypes = wrapper.getImpression();
                if (CollectionUtils.isNotEmpty(anyURITypes)) {
                    impressions = new ArrayList<>(anyURITypes.size());
                    for (AnyURIType anyURIType : anyURITypes) {
                        String value = anyURIType.getValue();
                        if (StringUtils.isNotBlank(value)) {
                            impressions.add(value);
                        }
                    }
                }

                return new VastWrapperInfo(vastAdTagURI, createTrackersMap(impressions, wrapper.getError()));
            } else {
                return VastWrapperInfo.EMPTY_VASTTAGURI;
            }
        } else {
            return VastWrapperInfo.UNSUPPORTED_VERSION;
        }
    }

    private static Map<String, Object> createTrackersMap(List<String> impressions, List<String> errors) {

        Map<String, Object> trackers = Collections.emptyMap();

        if (CollectionUtils.isEmpty(errors) && CollectionUtils.isNotEmpty(impressions)) {
            trackers = Collections.singletonMap(VASTTag.UrlImpressions.getTagName(), impressions);
        } else if (CollectionUtils.isEmpty(impressions) && CollectionUtils.isNotEmpty(errors)) {
            trackers = Collections.singletonMap(VASTTag.UrlErrors.getTagName(), errors);
        } else if (CollectionUtils.isNotEmpty(impressions) && CollectionUtils.isNotEmpty(errors)) {
            trackers = new HashMap<>(2);
            trackers.put(VASTTag.UrlImpressions.getTagName(), impressions);
            trackers.put(VASTTag.UrlErrors.getTagName(), errors);
        }

        return trackers;
    }

    private static boolean isUrl(String vastAdTagURI) {
        return StringUtils.startsWithIgnoreCase(vastAdTagURI, "http");
    }
}
