package com.asteriosoft.vast;

import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class VastUtilsTest {

    private static final String WRAPPER_LINEAR = "<VAST version=\"2.0\">\r\n" +
            "<Ad id=\"602833\">\r\n" +
            "<Wrapper>\r\n" +
            "<AdSystem>Acudeo Compatible</AdSystem>\r\n" +
            "<VASTAdTagURI>\r\n" +
            "http://demo.tremormedia.com/proddev/vast/vast_inline_linear.xml\r\n" +
            "</VASTAdTagURI>\r\n" +
            "<Error>http://myErrorURL/wrapper/error</Error>\r\n" +
            "<Impression>http://myTrackingURL/wrapper/impression</Impression>\r\n" +
            "<Creatives>\r\n" +
            "<Creative AdID=\"602833\">\r\n" +
            "<Linear>\r\n" +
            "<TrackingEvents>\r\n" +
            "<Tracking event=\"creativeView\">http://myTrackingURL/wrapper/creativeView</Tracking>\r\n" +
            "<Tracking event=\"start\">http://myTrackingURL/wrapper/start</Tracking>\r\n" +
            "<Tracking event=\"midpoint\">http://myTrackingURL/wrapper/midpoint</Tracking>\r\n" +
            "<Tracking event=\"firstQuartile\">http://myTrackingURL/wrapper/firstQuartile</Tracking>\r\n" +
            "<Tracking event=\"thirdQuartile\">http://myTrackingURL/wrapper/thirdQuartile</Tracking>\r\n" +
            "<Tracking event=\"complete\">http://myTrackingURL/wrapper/complete</Tracking>\r\n" +
            "<Tracking event=\"mute\">http://myTrackingURL/wrapper/mute</Tracking>\r\n" +
            "<Tracking event=\"unmute\">http://myTrackingURL/wrapper/unmute</Tracking>\r\n" +
            "<Tracking event=\"pause\">http://myTrackingURL/wrapper/pause</Tracking>\r\n" +
            "<Tracking event=\"resume\">http://myTrackingURL/wrapper/resume</Tracking>\r\n" +
            "<Tracking event=\"fullscreen\">http://myTrackingURL/wrapper/fullscreen</Tracking>\r\n" +
            "</TrackingEvents>\r\n" +
            "</Linear>\r\n" +
            "</Creative>\r\n" +
            "<Creative>\r\n" +
            "<Linear>\r\n" +
            "<VideoClicks>\r\n" +
            "<ClickTracking>http://myTrackingURL/wrapper/click</ClickTracking>\r\n" +
            "</VideoClicks>\r\n" +
            "</Linear>\r\n" +
            "</Creative>\r\n" +
            "<Creative AdID=\"602833-NonLinearTracking\">\r\n" +
            "<NonLinearAds>\r\n" +
            "<TrackingEvents> </TrackingEvents>\r\n" +
            "</NonLinearAds>\r\n" +
            "</Creative>\r\n" +
            "                <Creative>\r\n" +
            "                    <NonLinearAds>\r\n" +
            "                        <TrackingEvents>\r\n" +
            "                            <Tracking event=\"expand\"><![CDATA[http://example.com/expand]]></Tracking>\r\n" +
            "                        </TrackingEvents>\r\n" +
            "                        <NonLinear>\r\n" +
            "                            <NonLinearClickTracking><![CDATA[http://example.com/click]]></NonLinearClickTracking>\r\n" +
            "                        </NonLinear>\r\n" +
            "                    </NonLinearAds>\r\n" +
            "                </Creative>\r\n" +
            "</Creatives>\r\n" +
            "</Wrapper>\r\n" +
            "</Ad>\r\n" +
            "</VAST>";

    @Test
    public void testWrapper() throws Exception {
        String wrapper = WRAPPER_LINEAR;
        testWrapper(wrapper, VastVersion.VAST2);

        wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "3.0");
        testWrapper(wrapper, VastVersion.VAST3);

        wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "4.0");
        testWrapper(wrapper, VastVersion.VAST4);

        wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "4.1");
        testWrapper(wrapper, VastVersion.VAST41);

        wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "4.2");
        testWrapper(wrapper, VastVersion.VAST42);
    }

    private void testWrapper(String wrapper, VastVersion version) throws Exception {
        VastVersion vastVersion = VastUtils.parseVastVersion(wrapper);
        assertEquals(version, vastVersion);

        Object vast = VastUtils.parseVast(wrapper);
        Assert.assertNotNull(vast);

        VastTrackers trackers = VastUtils.getWrapperTrackers(vast);
        assertEquals(1, trackers.getImpressionUrls().size());
        assertEquals(1, trackers.getErrorUrls().size());
        assertEquals(1, trackers.getLinearClickUrls().size());
        assertEquals(11, trackers.getLinearTrackingUrls().size());
        if (version == VastVersion.VAST2) {
            assertEquals(0, trackers.getNonLinearClickUrls().size());
        } else {
            assertEquals(1, trackers.getNonLinearClickUrls().size());
        }
        assertEquals(1, trackers.getNonLinearTrackingUrls().size());

        for (String s : trackers.getImpressionUrls()) {
            Assert.assertTrue(s.contains("impression"));
        }
        for (String s : trackers.getErrorUrls()) {
            Assert.assertTrue(s.contains("error"));
        }
        for (String s : trackers.getLinearClickUrls()) {
            Assert.assertTrue(s.contains("click"));
        }
        for (Map.Entry<String, Set<String>> e : trackers.getLinearTrackingUrls().entrySet()) {
            String event = e.getKey();
            for (String s : e.getValue()) {
                Assert.assertTrue(s.contains(event));
            }
        }
    }

    public String getFile(String name) throws Exception {
        try (InputStream is = getClass().getResourceAsStream(name);
             Scanner scanner = new Scanner(is, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    @Test
    public void testHTMLResourceWithoutCdata() throws Exception {
        String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><VAST version=\"3.0\"><Ad id=\"156\"><InLine><AdSystem>In Loco</AdSystem><AdTitle>Momentos Alegra</AdTitle><Description>Alegra é a melhor opção para todos os momentos. Alegra a sua vida e o coração!</Description><Impression><![CDATA[http://us-nj-e29.rtbtradein.com/?w=i&p=2.32&ds=602_4708fdfe9fc0808cb3eb13e4ae81287b&type=v&uq=c9996d12053d83ba96df24ac5488e633]]></Impression><Impression><![CDATA[http://exchange.ubee.in/rtb/win_notice/v1?platform_key=clickky&request_id=644-0bf9a7addbea025-577&price=3.2359354703101&bid_id=&imp_id=12342&seat_id=28d86010-5879-11e8-9c2d-fa7ae01bbebc&ad_id=&currency=USD&response=2B81Aw83eueuogqe9jndStYmbyyIEwkiHu8j1Pp0Hn2m26_-CLCnZNBbCAXjK8sJ16FXQX9RcsNHknfT8jPw3QOdYBhS04a6g5uyxt0NTlwjjsxoiYfRnuteOvgHA3yh]]></Impression><Impression><![CDATA[https://app-05-marketplace.clickky.biz/rtb/impression?price=2.48092&uid=hnrMY8pNlheQx1MASfYQKw&s=120&d=398&tt=inapp&at=video&t=1542203525]]></Impression><Impression id=\"score\"><![CDATA[https://check.fraudscore.mobi/px/Loch5asaeew9Eequ.png?http_referer=com.cootek.smartinputv5.skin.keyboard_theme_water&offer_id=398_kVEkfV0-tWXW-Km7JB3nsg==&affiliate_id=120&affiliate_name=smarty_video&aff_sub1=75252&aff_sub2=pZ5CmcwKQK2HTpTiZcpP1Q]]></Impression><Impression><![CDATA[https://tracking.inlocomedia.com/v?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq]]></Impression><Impression><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=impression]]></Impression><Creatives><Creative><Linear><Duration>00:00:30.080</Duration><MediaFiles><MediaFile delivery=\"progressive\" type=\"video/mp4\" maintainAspectRatio=\"true\" width=\"640.0\" height=\"480.0\"><![CDATA[https://35352h.ha.azioncdn.net/outputs/5be4abf4ba4eff000b000695/filme_momentos_30_ts_1541712816742/r480p.mp4]]></MediaFile></MediaFiles><TrackingEvents><Tracking event=\"firstQuartile\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=first_quartile]]></Tracking><Tracking event=\"unmute\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=unmute]]></Tracking><Tracking event=\"complete\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=completed]]></Tracking><Tracking event=\"pause\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=paused]]></Tracking><Tracking event=\"thirdQuartile\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=third_quartile]]></Tracking><Tracking event=\"resume\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=playing]]></Tracking><Tracking event=\"timeSpentViewing\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=time_spent_viewing&time_spent_viewing=[TIME_SPENT_VIEWING]]]></Tracking><Tracking event=\"skip\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=skip]]></Tracking><Tracking event=\"start\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=started]]></Tracking><Tracking event=\"rewind\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=restart]]></Tracking><Tracking event=\"midpoint\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=midpoint]]></Tracking><Tracking event=\"mute\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=mute]]></Tracking><Tracking event=\"progress\" offset=\"00:00:30.000\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=progress&video_progress_time=00%3A00%3A30.000]]></Tracking><Tracking event=\"progress\" offset=\"80%\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=progress&video_progress_time=80%25]]></Tracking><Tracking event=\"progress\" offset=\"85%\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=progress&video_progress_time=85%25]]></Tracking><Tracking event=\"progress\" offset=\"90%\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=progress&video_progress_time=90%25]]></Tracking><Tracking event=\"progress\" offset=\"95%\"><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=progress&video_progress_time=95%25]]></Tracking></TrackingEvents><VideoClicks><ClickThrough><![CDATA[https://tracking.inlocomedia.com/c?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq]]></ClickThrough><ClickTracking><![CDATA[https://tracking.inlocomedia.com/video?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq&event=click_thru]]></ClickTracking></VideoClicks></Linear></Creative><Creative><CompanionAds required=\"true\"><Companion width=\"640.0\" height=\"480.0\"><HTMLResource><html lang=\"pt-br, en\"><head><script src=\"sdkResourcesValidator.js\"></script><script src=\"videoPostRoll.js\"></script><meta charset=\"UTF-8\"><meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\"><meta content=\"width=device-width,initial-scale=1,user-scalable=no\" name=\"viewport\"><title>Video Ad Postroll</title> <style>a,body{-webkit-user-select:none}.icon,body{overflow:hidden}.icon,.wrap-subtitle,.wrap-title{text-align:center}.action-button,a{-webkit-tap-highlight-color:transparent}body{position:relative;margin:0!important;padding:0!important;width:100%!important;height:100%!important;font-family:Roboto,sans-serif;-webkit-margin-before:0;-webkit-margin-after:0;-webkit-margin-start:0;-webkit-margin-end:0}#adContainer,#containerSpace,.top-bar{position:absolute;top:0;left:0}#adContainer{width:100%;height:100%;right:0;bottom:0;margin:0;padding:0;background-color:#FFF}#containerSpace,.infos{background-color:#333;bottom:0;right:0}#containerSpace{width:100%;height:100%;margin:auto}.infos{box-sizing:border-box;z-index:1000}.icon{margin:25px 0;height:50px}.icon img{height:50px;width:50px;margin:0 auto}.wrap-title{margin-bottom:20px}h2{color:#FFF;margin:0!important}.wrap-title h2{font-weight:700;font-size:1.2em}.wrap-subtitle h2{font-weight:400;font-size:.9em}.wrap-action-button{height:48px;text-align:center}.action-button{outline:0!important;cursor:pointer;height:44px;font-size:.85em;font-weight:400;letter-spacing:1px;-webkit-border-radius:4px;border-radius:4px;padding:10px 12%;margin:0 auto;border:0;text-decoration:none;-webkit-box-shadow:1px 4px 5px rgba(0,0,0,.35);box-shadow:1px 4px 5px rgba(0,0,0,.35);background-color:#1D6ADA;color:#FFF}.action-button:active{outline:0!important;opacity:.8}.top-bar{background-color:transparent;right:0;z-index:10000}.btn-refresh{float:left;padding:10px}.btn-close{float:right;padding:10px}.btn-close img,.btn-refresh img{width:32px;height:32px}@media(orientation:landscape){#image_ad,.infos{top:0;position:absolute}#image_ad{height:100%;bottom:0;left:0;right:0}.infos{width:33%;padding:0 2%}.icon{width:96%}.wrap-action-button{position:absolute;bottom:0;width:88%}}@media(orientation:portrait){#image_ad,.icon,.infos{width:100%}.infos{display:block;left:0;padding:0 10%}.wrap-action-button{position:relative;bottom:5%;width:100%}.wrap-subtitle{margin-bottom:40px}}</style></head><body><script src=\"https://tracking.inlocomedia.com/v?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq\" width=\"0\" height=\"0\"></script><div id=\"adContainer\"><div class=\"top-bar\"><div class=\"btn-refresh\" onclick=\"videoPostRoll.restart()\"><img src=\"https://25352h.ha.azioncdn.net/icons/v2/btn-back.png\" alt=\"botão de reproduzir novamente o vídeo\"></div><div class=\"btn-close\" onclick=\"videoPostRoll.close()\"><img src=\"https://25352h.ha.azioncdn.net/icons/v2/btn-close.png\" alt=\"botão para fechar o anúncio\"></div></div><div id=\"containerSpace\"><a class=\"rtb-click-tracking\" href=\"https://tracking.inlocomedia.com/c?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq\"><img id=\"image_ad\" src=\"https://35352h.ha.azioncdn.net/inputs/5be4abf4ba4eff000b000695/filme_momentos_30_ts_1541712816742/post_roll_image.jpg\"></a><div class=\"infos\"><div class=\"icon\"><img id=\"icon-color\" src=\"https://35352h.ha.azioncdn.net/inputs/5be4abf4ba4eff000b000695/filme_momentos_30_ts_1541712816742/icon.jpg\" alt=\"imagem do icone do anúncio\"></div><div class=\"wrap-title\"><h2>Momentos Alegra</h2></div><div class=\"wrap-subtitle\"><h2>Alegra é a melhor opção para todos os momentos. Alegra a sua vida e o coração!</h2></div><div class=\"wrap-action-button\"><a id=\"cta-btn\" class=\"action-button rtb-click-tracking\" href=\"https://tracking.inlocomedia.com/c?token=2B81Aw83eueuogqe9jndSizwB_HOatFtq34zjI1GMwBaOj4kfXcFVc4B8652DoNa_iH4aBra6IoycRw_lj0HfeaOCAnRVr6gNwzv7Kzy0pPTJPms5jthIPv32qzCIQ71-HUqrQAOWE0oa6gizJ3f2FE0ax-VZDE295s8W2wFUshXcYgRxgrd0K92E_BnDDxpoMANYRVpDqbi1XYzAPv86jSww1GYWjbKsPNgZyPnmnDtFLhd22Gde5PUKk8W-NMq\">Conheça!</a></div></div></div></div></body></html></HTMLResource></Companion></CompanionAds></Creative></Creatives></InLine></Ad></VAST>";
        Object vast = VastUtils.parseVast(s);
        Assert.assertNotNull(vast);
    }
}
