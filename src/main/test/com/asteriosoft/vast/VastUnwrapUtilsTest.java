package com.asteriosoft.vast;

import org.junit.Test;

public class VastUnwrapUtilsTest {

    @Test
    public void test() throws VastUnwrapException {
        String vast4 = "<VAST version=\"4.0\">\n" +
                "  <Ad id=\"20001\" sequence=\"1\" conditionalAd=\"false\">\n" +
                "    <InLine>\n" +
                "      <AdSystem version=\"4.0\">iabtechlab</AdSystem>\n" +
                "      <Error>http://example.com/error</Error>\n" +
                "      <Extensions>\n" +
                "        <Extension type=\"iab-Count\">\n" +
                "          <total_available>\n" +
                "            <![CDATA[ 2 ]]>\n" +
                "          </total_available>\n" +
                "        </Extension>\n" +
                "      </Extensions>\n" +
                "      <Impression id=\"Impression-ID\">http://example.com/track/impression</Impression>\n" +
                "      <Pricing model=\"cpm\" currency=\"USD\">\n" +
                "        <![CDATA[ 25.00 ]]>\n" +
                "      </Pricing>\n" +
                "      <AdTitle>iabtechlab video ad</AdTitle>\n" +
                "      <AdVerifications>\n" +
                "        <Verification>\n" +
                "          <JavaScriptResource>\n" +
                "            <![CDATA[https://verificationcompany1.com/verification_script1.js]]>\n" +
                "          </JavaScriptResource>\n" +
                "        </Verification>\n" +
                "        <Verification>\n" +
                "          <JavaScriptResource>\n" +
                "            <![CDATA[https://verificationcompany.com/untrusted.js]]>\n" +
                "          </JavaScriptResource>\n" +
                "        </Verification>\n" +
                "      </AdVerifications>\n" +
                "      <Advertiser>IAB Sample Company</Advertiser>\n" +
                "      <Category authority=\"foo\" />\n" +
                "      <Creatives>\n" +
                "        <Creative id=\"5480\" sequence=\"1\" adId=\"2447226\">\n" +
                "          <UniversalAdId idRegistry=\"Ad-ID\" idValue=\"8465\">8465</UniversalAdId>\n" +
                "          <Linear>\n" +
                "            <TrackingEvents>\n" +
                "              <Tracking event=\"start\" offset=\"12:22:11\">http://example.com/tracking/start</Tracking>\n" +
                "              <Tracking event=\"firstQuartile\">http://example.com/tracking/firstQuartile</Tracking>\n" +
                "              <Tracking event=\"midpoint\">http://example.com/tracking/midpoint</Tracking>\n" +
                "              <Tracking event=\"thirdQuartile\">http://example.com/tracking/thirdQuartile</Tracking>\n" +
                "              <Tracking event=\"complete\">http://example.com/tracking/complete</Tracking>\n" +
                "            </TrackingEvents>\n" +
                "            <Duration>00:00:16</Duration>\n" +
                "            <MediaFiles>\n" +
                "              <MediaFile id=\"5241\" delivery=\"progressive\" type=\"video/mp4\" bitrate=\"2000\" width=\"1280\" height=\"720\" minBitrate=\"1500\" maxBitrate=\"2500\" scalable=\"1\" maintainAspectRatio=\"1\" codec=\"0\">\n" +
                "                <![CDATA[https://iabtechlab.com/wp-content/uploads/2016/07/VAST-4.0-Short-Intro.mp4]]>\n" +
                "              </MediaFile>\n" +
                "              <MediaFile id=\"5244\" delivery=\"progressive\" type=\"video/mp4\" bitrate=\"1000\" width=\"854\" height=\"480\" minBitrate=\"700\" maxBitrate=\"1500\" scalable=\"1\" maintainAspectRatio=\"1\" codec=\"0\">\n" +
                "                <![CDATA[https://iabtechlab.com/wp-content/uploads/2017/12/VAST-4.0-Short-Intro-mid-resolution.mp4]]>\n" +
                "              </MediaFile>\n" +
                "              <MediaFile id=\"5246\" delivery=\"progressive\" type=\"video/mp4\" bitrate=\"600\" width=\"640\" height=\"360\" minBitrate=\"500\" maxBitrate=\"700\" scalable=\"1\" maintainAspectRatio=\"1\" codec=\"0\">\n" +
                "                <![CDATA[https://iabtechlab.com/wp-content/uploads/2017/12/VAST-4.0-Short-Intro-low-resolution.mp4]]>\n" +
                "              </MediaFile>\n" +
                "            </MediaFiles>\n" +
                "            <VideoClicks>\n" +
                "              <ClickThrough id=\"blog\">\n" +
                "                <![CDATA[https://iabtechlab.com]]>\n" +
                "              </ClickThrough>\n" +
                "            </VideoClicks>\n" +
                "          </Linear>\n" +
                "        </Creative>\n" +
                "      </Creatives>\n" +
                "    </InLine>\n" +
                "  </Ad>\n" +
                "</VAST>";

        String vast2 = "<VAST version=\"2.0\"><Ad id=\"602833\"><Wrapper><AdSystem>Acudeo Compatible</AdSystem><VASTAdTagURI>http://demo.tremormedia.com/proddev/vast/vast_inline_linear.xml</VASTAdTagURI><Error>http://myErrorURL/wrapper/error</Error><Impression>http://myTrackingURL/wrapper/impression</Impression><Creatives><Creative AdID=\"602833\"><Linear><TrackingEvents><Tracking event=\"creativeView\">http://myTrackingURL/wrapper/creativeView</Tracking><Tracking event=\"start\">http://myTrackingURL/wrapper/start</Tracking><Tracking event=\"midpoint\">http://myTrackingURL/wrapper/midpoint</Tracking><Tracking event=\"firstQuartile\">http://myTrackingURL/wrapper/firstQuartile</Tracking><Tracking event=\"thirdQuartile\">http://myTrackingURL/wrapper/thirdQuartile</Tracking><Tracking event=\"complete\">http://myTrackingURL/wrapper/complete</Tracking><Tracking event=\"mute\">http://myTrackingURL/wrapper/mute</Tracking><Tracking event=\"unmute\">http://myTrackingURL/wrapper/unmute</Tracking><Tracking event=\"pause\">http://myTrackingURL/wrapper/pause</Tracking><Tracking event=\"resume\">http://myTrackingURL/wrapper/resume</Tracking><Tracking event=\"fullscreen\">http://myTrackingURL/wrapper/fullscreen</Tracking></TrackingEvents></Linear></Creative><Creative><Linear><VideoClicks><ClickTracking>http://myTrackingURL/wrapper/click</ClickTracking></VideoClicks></Linear></Creative><Creative AdID=\"602833-NonLinearTracking\"><NonLinearAds><TrackingEvents></TrackingEvents></NonLinearAds></Creative></Creatives></Wrapper></Ad></VAST>";
        String vast3 = "<VAST version=\"3.0\">\n" +
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
        Object vast = VastUnwrapUtils.parseVast(vast4);
    }
}
