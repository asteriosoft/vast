package com.asteriosoft.vast;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class VastUnwrapUtilsTest {

    private static final String VAST2_WRAPPER_WITH_COMPANIONS = "<VAST version=\"2.0\"><Ad id=\"602867\"><Wrapper><AdSystem>Acudeo Compatible</AdSystem><VASTAdTagURI>http://demo.tremormedia.com/proddev/vast/vast_inline_nonlinear3.xml</VASTAdTagURI><Impression>http://myTrackingURL/wrapper/impression</Impression><Creatives><Creative AdID=\"602867\"><Linear><TrackingEvents>\n" +
            "\t\t\t\t</TrackingEvents></Linear></Creative><Creative AdID=\"602867-NonLinearTracking\"><NonLinearAds><TrackingEvents>\n" +
            "\t\t\t\t</TrackingEvents></NonLinearAds></Creative><Creative AdID=\"602867-Companion\"><CompanionAds><Companion width=\"300\" height=\"250\"><StaticResource creativeType=\"image/jpeg\">http://demo.tremormedia.com/proddev/vast/300x250_banner1.jpg</StaticResource><TrackingEvents><Tracking event=\"creativeView\">http://myTrackingURL/wrapper/firstCompanionCreativeView</Tracking></TrackingEvents><CompanionClickThrough>http://www.tremormedia.com</CompanionClickThrough></Companion><Companion width=\"728\" height=\"90\"><StaticResource creativeType=\"image/jpeg\">http://demo.tremormedia.com/proddev/vast/728x90_banner1.jpg</StaticResource><CompanionClickThrough>http://www.tremormedia.com</CompanionClickThrough></Companion></CompanionAds></Creative></Creatives></Wrapper></Ad></VAST>";

    private static final String VAST2_WRAPPER_WITHOUT_COMPANIONS = "<VAST version=\"2.0\"><Ad id=\"602867\"><Wrapper><AdSystem>Acudeo Compatible</AdSystem><VASTAdTagURI>http://demo.tremormedia.com/proddev/vast/vast_inline_nonlinear3.xml</VASTAdTagURI><Impression>http://myTrackingURL/wrapper/impression</Impression></Wrapper></Ad></VAST>";

    private static final String VAST3_WRAPPER_WITH_COMPANIONS = "<VAST version=\"3.0\">\n" +
            "    <Ad id=\"20011\">\n" +
            "        <Wrapper>\n" +
            "            <AdSystem version=\"4.0\">iabtechlab</AdSystem>\n" +
            "            <VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>\n" +
            "\n" +
            "            <Error>http://example.com/error</Error>\n" +
            "\n" +
            "            <Impression>http://example.com/track/impression</Impression>\n" +
            "            <Creatives>\n" +
            "                <Creative id=\"5480\" sequence=\"1\">\n" +
            "                    <CompanionAds>\n" +
            "                        <Companion id=\"1232\" width=\"100\" height=\"150\" assetWidth=\"250\" assetHeight=\"200\" expandedWidth=\"350\" expandedHeight=\"250\">\n" +
            "                            <StaticResource creativeType=\"image/png\">\n" +
            "                                    <![CDATA[https://www.iab.com/wp-content/uploads/2014/09/iab-tech-lab-6-644x290.png]]>\n" +
            "                            </StaticResource>\n" +
            "                            <CompanionClickThrough>\n" +
            "                                <![CDATA[https://iabtechlab.com]]>\n" +
            "                            </CompanionClickThrough>\n" +
            "                        </Companion>\n" +
            "                    </CompanionAds>\n" +
            "                </Creative>\n" +
            "            </Creatives>\n" +
            "        </Wrapper>\n" +
            "    </Ad>\n" +
            "</VAST>";

    private static final String VAST3_WRAPPER_WITHOUT_COMPANIONS = "<VAST version=\"3.0\">\n" +
            "    <Ad id=\"20011\">\n" +
            "        <Wrapper>\n" +
            "            <AdSystem version=\"4.0\">iabtechlab</AdSystem>\n" +
            "            <VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>\n" +
            "\n" +
            "            <Error>http://example.com/error</Error>\n" +
            "\n" +
            "            <Impression>http://example.com/track/impression</Impression>\n" +
            "            <Creatives>\n" +
            "            </Creatives>\n" +
            "        </Wrapper>\n" +
            "    </Ad>\n" +
            "</VAST>";

    private static final String VAST4_WRAPPER_WITH_COMPANIONS = "<VAST version=\"4.0\" xmlns=\"http://www.iab.com/VAST\">\n" +
            "  <Ad id=\"20011\" sequence=\"1\" conditionalAd=\"false\">\n" +
            "    <Wrapper followAdditionalWrappers=\"0\" allowMultipleAds=\"1\" fallbackOnNoAd=\"0\">\n" +
            "      <AdSystem version=\"4.0\">iabtechlab</AdSystem>\n" +
            "      <Error>http://example.com/error</Error>\n" +
            "      <Impression id=\"Impression-ID\">http://example.com/track/impression</Impression>\n" +
            "      <Creatives>\n" +
            "        <Creative id=\"5480\" sequence=\"1\" adId=\"2447226\">\n" +
            "          <CompanionAds>\n" +
            "            <Companion id=\"1232\" width=\"100\" height=\"150\" assetWidth=\"250\" assetHeight=\"200\" expandedWidth=\"350\" expandedHeight=\"250\" apiFramework=\"VPAID\" adSlotID=\"3214\" pxratio=\"1400\">\n" +
            "              <StaticResource creativeType=\"image/png\">\n" +
            "                <![CDATA[https://www.iab.com/wp-content/uploads/2014/09/iab-tech-lab-6-644x290.png]]>\n" +
            "              </StaticResource>\n" +
            "              <CompanionClickThrough>\n" +
            "                <![CDATA[https://iabtechlab.com]]>\n" +
            "              </CompanionClickThrough>\n" +
            "            </Companion>\n" +
            "          </CompanionAds>\n" +
            "        </Creative>\n" +
            "      </Creatives>\n" +
            "      <VASTAdTagURI>\n" +
            "        <![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%204.0%20Samples/Inline_Companion_Tag-test.xml]]>\n" +
            "      </VASTAdTagURI>\n" +
            "    </Wrapper>\n" +
            "  </Ad>\n" +
            "</VAST>";

    private static final String VAST4_WRAPPER_WITHOUT_COMPANIONS = "<VAST version=\"4.0\" xmlns=\"http://www.iab.com/VAST\">\n" +
            "  <Ad id=\"20011\" sequence=\"1\" conditionalAd=\"false\">\n" +
            "    <Wrapper followAdditionalWrappers=\"0\" allowMultipleAds=\"1\" fallbackOnNoAd=\"0\">\n" +
            "      <AdSystem version=\"4.0\">iabtechlab</AdSystem>\n" +
            "      <Error>http://example.com/error</Error>\n" +
            "      <Impression id=\"Impression-ID\">http://example.com/track/impression</Impression>\n" +
            "      <VASTAdTagURI>\n" +
            "        <![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%204.0%20Samples/Inline_Companion_Tag-test.xml]]>\n" +
            "      </VASTAdTagURI>\n" +
            "    </Wrapper>\n" +
            "  </Ad>\n" +
            "</VAST>";

    @Test
    public void testVast2() throws VastUnwrapException {
        com.asteriosoft.vast.v2.VAST vast2WithCompanions = (com.asteriosoft.vast.v2.VAST) VastUnwrapUtils.parseVast(VAST2_WRAPPER_WITH_COMPANIONS);
        assertNotNull(vast2WithCompanions);
        assertNotNull(vast2WithCompanions.getAd());
        assertEquals(1, vast2WithCompanions.getAd().size());

        assertNull(VastUnwrapUtils.getWrapperInfo(vast2WithCompanions));

        com.asteriosoft.vast.v2.VAST vast2WithoutCompanions = (com.asteriosoft.vast.v2.VAST) VastUnwrapUtils.parseVast(VAST2_WRAPPER_WITHOUT_COMPANIONS);
        assertNotNull(vast2WithoutCompanions);
        assertNotNull(vast2WithoutCompanions.getAd());
        assertEquals(1, vast2WithoutCompanions.getAd().size());

        Pair<String, Map<String, List<String>>> wrapperInfo = VastUnwrapUtils.getWrapperInfo(vast2WithoutCompanions);
        assertNotNull(wrapperInfo);
        assertEquals("http://demo.tremormedia.com/proddev/vast/vast_inline_nonlinear3.xml", wrapperInfo.getLeft());

        Map<String, List<String>> trackers = wrapperInfo.getRight();
        assertEquals(1, trackers.size());
        List<String> impressions = trackers.get(VASTTag.UrlImpressions.getTagName());
        assertTrue(CollectionUtils.isNotEmpty(impressions) && impressions.size() == 1);
        assertEquals("http://myTrackingURL/wrapper/impression", impressions.get(0));
    }

    @Test
    public void testVast3() throws VastUnwrapException {
        com.asteriosoft.vast.v3.VAST vast3WithCompanions = (com.asteriosoft.vast.v3.VAST) VastUnwrapUtils.parseVast(VAST3_WRAPPER_WITH_COMPANIONS);
        assertNotNull(vast3WithCompanions);
        assertNotNull(vast3WithCompanions.getAd());
        assertEquals(1, vast3WithCompanions.getAd().size());

        assertNull(VastUnwrapUtils.getWrapperInfo(vast3WithCompanions));

        com.asteriosoft.vast.v3.VAST vast3WithoutCompanions = (com.asteriosoft.vast.v3.VAST) VastUnwrapUtils.parseVast(VAST3_WRAPPER_WITHOUT_COMPANIONS);
        assertNotNull(vast3WithoutCompanions);
        assertNotNull(vast3WithoutCompanions.getAd());
        assertEquals(1, vast3WithoutCompanions.getAd().size());

        Pair<String, Map<String, List<String>>> wrapperInfo = VastUnwrapUtils.getWrapperInfo(vast3WithoutCompanions);
        assertNotNull(wrapperInfo);
        assertEquals("https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml", wrapperInfo.getLeft());

        Map<String, List<String>> trackers = wrapperInfo.getRight();
        assertEquals(2, trackers.size());
        List<String> impressions = trackers.get(VASTTag.UrlImpressions.getTagName());
        assertTrue(CollectionUtils.isNotEmpty(impressions) && impressions.size() == 1);
        assertEquals("http://example.com/track/impression", impressions.get(0));

        List<String> errors = trackers.get(VASTTag.UrlErrors.getTagName());
        assertTrue(CollectionUtils.isNotEmpty(errors) && errors.size() == 1);
        assertEquals("http://example.com/error", errors.get(0));
    }

    @Test
    public void testVast4() throws VastUnwrapException {
        com.asteriosoft.vast.v4.VAST vast4WithCompanions = (com.asteriosoft.vast.v4.VAST) VastUnwrapUtils.parseVast(VAST4_WRAPPER_WITH_COMPANIONS);
        assertNotNull(vast4WithCompanions);
        assertNotNull(vast4WithCompanions.getAd());
        assertEquals(1, vast4WithCompanions.getAd().size());

        assertNull(VastUnwrapUtils.getWrapperInfo(vast4WithCompanions));

        com.asteriosoft.vast.v4.VAST vast4WithoutCompanions = (com.asteriosoft.vast.v4.VAST) VastUnwrapUtils.parseVast(VAST4_WRAPPER_WITHOUT_COMPANIONS);
        assertNotNull(vast4WithoutCompanions);
        assertNotNull(vast4WithoutCompanions.getAd());
        assertEquals(1, vast4WithoutCompanions.getAd().size());

        Pair<String, Map<String, List<String>>> wrapperInfo = VastUnwrapUtils.getWrapperInfo(vast4WithoutCompanions);
        assertNotNull(wrapperInfo);
        assertEquals("https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%204.0%20Samples/Inline_Companion_Tag-test.xml", wrapperInfo.getLeft());

        Map<String, List<String>> trackers = wrapperInfo.getRight();
        assertEquals(2, trackers.size());
        List<String> impressions = trackers.get(VASTTag.UrlImpressions.getTagName());
        assertTrue(CollectionUtils.isNotEmpty(impressions) && impressions.size() == 1);
        assertEquals("http://example.com/track/impression", impressions.get(0));

        List<String> errors = trackers.get(VASTTag.UrlErrors.getTagName());
        assertTrue(CollectionUtils.isNotEmpty(errors) && errors.size() == 1);
        assertEquals("http://example.com/error", errors.get(0));
    }
}
