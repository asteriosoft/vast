package com.asteriosoft.vast;

import java.util.List;
import java.util.Map;

public final class VastTrackers {

	private final List<String> impressionUrls;
	private final List<String> errorUrls;
	private final Map<String, List<String>> linearTrackingUrls;
	private final List<String> linearClickUrls;
	private final Map<String, List<String>> nonLinearTrackingUrls;
	
	public VastTrackers(List<String> impressionUrls, List<String> errorUrls, Map<String, List<String>> linearTrackingUrls, List<String> linearClickUrls, Map<String, List<String>> nonLinearTrackingUrls) {
		super();
		this.impressionUrls = impressionUrls;
		this.errorUrls = errorUrls;
		this.linearTrackingUrls = linearTrackingUrls;
		this.linearClickUrls = linearClickUrls;
		this.nonLinearTrackingUrls = nonLinearTrackingUrls;
	}
	
	public VastTrackers(List<String> impressions, List<String> errors) {
		super();
		this.impressionUrls = impressions;
		this.errorUrls = errors;
		this.linearTrackingUrls = null;
		this.linearClickUrls = null;
		this.nonLinearTrackingUrls = null;
	}
	
	public List<String> getImpressionUrls() {
		return impressionUrls;
	}
	public List<String> getErrorUrls() {
		return errorUrls;
	}
	public Map<String, List<String>> getLinearTrackingUrls() {
		return linearTrackingUrls;
	}
	public List<String> getLinearClickUrls() {
		return linearClickUrls;
	}
	public Map<String, List<String>> getNonLinearTrackingUrls() {
		return nonLinearTrackingUrls;
	}

	@Override
	public String toString() {
		return "VastTrackers [impressionUrls=" + impressionUrls + ", errorUrls=" + errorUrls + ", linearTrackingUrls="
				+ linearTrackingUrls + ", linearClickUrls=" + linearClickUrls + ", nonLinearTrackingUrls="
				+ nonLinearTrackingUrls + "]";
	}

}
