package com.asteriosoft.vast;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class VastWrapUtilsTest {

    private static final String VAST2_CODE = "<VAST version=\"2.0\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "</Wrapper></Ad></VAST>";

    private static final String VAST3_CODE = "<VAST version=\"3.0\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "</Wrapper></Ad></VAST>";

    private static final String VAST4_CODE = "<VAST version=\"4.0\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "</Wrapper></Ad></VAST>";

    private static final String VAST41_CODE = "<VAST version=\"4.1\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "</Wrapper></Ad></VAST>";

    private static final String VAST42_CODE = "<VAST version=\"4.2\"><Ad id=\"20011\"><Wrapper><AdSystem version=\"4.0\">iabtechlab</AdSystem>" +
            "<VASTAdTagURI><![CDATA[https://raw.githubusercontent.com/InteractiveAdvertisingBureau/VAST_Samples/master/VAST%203.0%20Samples/Inline_Companion_Tag-test.xml]]></VASTAdTagURI>" +
            "</Wrapper></Ad></VAST>";

    @Test
    public void testTrackersInjection() throws Exception {
        VastTrackers vastTrackers = new VastTrackers(
                Collections.singletonList("http://impr"),
                Collections.singletonList("http://error"),
                Collections.singletonMap("start", Collections.singleton("http://start")),
                Collections.singleton("http://click"),
                null, null
        );

        for (String code : new String[]{VAST2_CODE, VAST3_CODE, VAST4_CODE, VAST41_CODE, VAST42_CODE}) {
            String resultVast = VastUtils.addVastTrackingUrls(code, vastTrackers);
            System.out.println(resultVast);

            Assert.assertTrue(resultVast.contains("http://impr"));
            Assert.assertTrue(resultVast.contains("http://error"));
            Assert.assertTrue(resultVast.contains("http://start"));
            Assert.assertTrue(resultVast.contains("http://click"));
        }
    }
}
