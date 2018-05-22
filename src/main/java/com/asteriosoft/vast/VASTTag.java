/**
 * VASTTags.java
 * <p>
 * Copyright 2015-2017 RhythmOne. All Rights Reserved.
 * <p>
 * This software is the proprietary information of RhythmOne.
 * Use is subject to license terms.
 */
package com.asteriosoft.vast;

public enum VASTTag {

    VastVersion("VAST_Version"),
    AdSystem("Ad_System"),
    BuildNumber("Build_Number"),
    Title("VAST_Title_field"),
    AdId("Ad_ID"),
    AdWidth("Ad_Width"),
    AdHeight("Ad_Height"),
    ApiFramework("Api_Framework"),
    UrlImpressions("Ad_Impression_URLs"),
    UrlClick("Click_URL"),
    UrlClicks("Ad_Click_URLs"),
    UrlErrors("Track_URL_Errors"),
    UrlThirdpartyVast("VAST_URL"),

    UrlsTrackingLinear("Ad_TrackingUrls_Linear"),
    UrlsTrackingNonLinear("Ad_TrackingUrls_NonLinear"),

    IframeSrc("Ad_IFrame_Src"),
    HtmlCode("Ad_HTML_Code"),
    AdMediaType("Ad_Media_Type"),
    AdMediaUrl("Ad_Media_URL"),
    AdMediaUrlGoogleCertMp4("Ad_Media_URL_Google_Cert_mp4"),
    AdMediaUrlGoogleCertFlv("Ad_Media_URL_Google_Cert_flv"),
    AdMediaUrlGoogleCertWebm("Ad_Media_URL_Google_Cert_webm"),

    AdLinear("Ad_Linear"),
    AdSkipOffset("Ad_SkipOffset"),
    DeliveryField("Delivery_Field"),
    DurationField("Duration_Field"),
    CompanionAds("Companion_Ads"),
    VastHeader("VAST_HEADER"),
    VastFooter("VAST_FOOTER"),
    AdSequence("Ad_Sequence"),
    AdParameters("Ad_Parameters"),

    MoatExtension("MOAT_Extension"),
    MoatCampaignId("MOAT_CampaignId"),
    MoatCampaignName("MOAT_CampaignName"),
    MoatBannerId("MOAT_BannerId"),
    MoatLineItemId("MOAT_AdGroupId"),
    MoatApplicationId("MOAT_ApplicationId"),
    MoatAdvertiserName("MOAT_AdvertiserName"),
    MoatRequestId("MOAT_RequestId"),

    VpaidExtension("VPAID_Extension"),
    VpaidConfirmUrl("view_confirm_url"),
    VpaidPatience("vpaid_patience"),
    VpaidMaxAttempts("vpaid_max_ad_attempts"),
    VpaidMaxDuration("vpaid_max_duration"),
    VpaidVastId("vpaid_vast_id"),
    VpaidWidth("vpaid_width"),
    VpaidHeight("vpaid_height"),
    VpaidWaterfallUrl("vpaid_waterfall_url"),
    VpaidManagerUrl("vpaid_manager_url"),
    VpaidJsMediaUrl("vpaid_js_media_url"),;

    private final String tagName;

    VASTTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
