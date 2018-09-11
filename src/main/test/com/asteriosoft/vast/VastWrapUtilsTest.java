package com.asteriosoft.vast;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class VastWrapUtilsTest {

    private static final String VAST3_WRAPPER_WITHOUT_COMPANIONS = "<VAST version=\"3.0\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "<Error>http://example.com/error</Error>" +
            "<Impression>http://example.com/track/impression</Impression>" +
            "<Creatives></Creatives></Wrapper></Ad></VAST>";

    @Test
    public void testTrackersInjection() throws VastUnwrapException {
        Map<String, Object> trackers = Collections.singletonMap(VASTTag.UrlImpressions.getTagName(), Collections.singletonList("http://test.com"));

        String resultVast = VastWrapUtils.addVastTrackingUrls(VAST3_WRAPPER_WITHOUT_COMPANIONS, trackers);

        Assert.assertTrue(resultVast.contains("<![CDATA[http://test.com]]>"));
    }
}
