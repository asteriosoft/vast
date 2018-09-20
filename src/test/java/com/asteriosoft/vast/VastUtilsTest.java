package com.asteriosoft.vast;

import org.junit.Assert;
import org.junit.Test;

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
			"</Creatives>\r\n" + 
			"</Wrapper>\r\n" + 
			"</Ad>\r\n" + 
			"</VAST>";
	
	
	@Test
	public void test() throws Exception {
		String wrapper = WRAPPER_LINEAR;
		
		VastVersion vastVersion = VastUtils.parseVastVersion(wrapper);
		Assert.assertEquals(VastVersion.VAST2, vastVersion);
		
		Object vast = VastUtils.parseVast(wrapper);
		Assert.assertNotNull(vast);

		wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "3.0");

		vastVersion = VastUtils.parseVastVersion(wrapper);
		Assert.assertEquals(VastVersion.VAST3, vastVersion);
		
		vast = VastUtils.parseVast(wrapper);
		Assert.assertNotNull(vast);

		wrapper = WRAPPER_LINEAR.replaceAll("2\\.0", "4.0");

		vastVersion = VastUtils.parseVastVersion(wrapper);
		Assert.assertEquals(VastVersion.VAST4, vastVersion);
		
		vast = VastUtils.parseVast(wrapper);
		Assert.assertNotNull(vast);		
	}
}
