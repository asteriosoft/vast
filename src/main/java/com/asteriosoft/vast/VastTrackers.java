/*
 *
 *  * Copyright 2023 Asteriosoft Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.asteriosoft.vast;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

public final class VastTrackers {

	private final List<String> impressionUrls;
	private final List<String> errorUrls;
	//
	private final Map<String, Set<String>> linearTrackingUrls;
	private final Set<String> linearClickUrls;
	//
	private final Map<String, Set<String>> nonLinearTrackingUrls;
	private final Set<String> nonLinearClickUrls;	
	
	public VastTrackers(List<String> impressionUrls, List<String> errorUrls, Map<String, Set<String>> linearTrackingUrls, Set<String> linearClickUrls, Map<String, Set<String>> nonLinearTrackingUrls, Set<String> nonLinearClickUrls) {
		super();
		this.impressionUrls = impressionUrls == null ? Collections.emptyList() : impressionUrls;
		this.errorUrls = errorUrls == null ? Collections.emptyList() : errorUrls;
		this.linearTrackingUrls = linearTrackingUrls == null ? Collections.emptyMap() : linearTrackingUrls;
		this.linearClickUrls = linearClickUrls == null ? Collections.emptySet() : linearClickUrls;
		this.nonLinearTrackingUrls = nonLinearTrackingUrls == null ? Collections.emptyMap() : nonLinearTrackingUrls;
		this.nonLinearClickUrls = nonLinearClickUrls == null ? Collections.emptySet() : nonLinearClickUrls;		
	}
	
	public VastTrackers(List<String> impressions, List<String> errors) {
		this(impressions, errors, null, null, null, null);
	}
	
	public boolean isEmpty() {
		return CollectionUtils.isEmpty(errorUrls) && CollectionUtils.isEmpty(impressionUrls)
				&& CollectionUtils.isEmpty(linearClickUrls) && CollectionUtils.isEmpty(nonLinearClickUrls)
				&& MapUtils.isEmpty(linearTrackingUrls) && MapUtils.isEmpty(nonLinearTrackingUrls);
	}

	public List<String> getImpressionUrls() {
		return impressionUrls;
	}

	public List<String> getErrorUrls() {
		return errorUrls;
	}

	public Map<String, Set<String>> getLinearTrackingUrls() {
		return linearTrackingUrls;
	}

	public Set<String> getLinearClickUrls() {
		return linearClickUrls;
	}

	public Map<String, Set<String>> getNonLinearTrackingUrls() {
		return nonLinearTrackingUrls;
	}

	public Set<String> getNonLinearClickUrls() {
		return nonLinearClickUrls;
	}

	@Override
	public String toString() {
		return "VastTrackers [impressionUrls=" + impressionUrls + ", errorUrls=" + errorUrls + ", linearTrackingUrls="
				+ linearTrackingUrls + ", linearClickUrls=" + linearClickUrls + ", nonLinearTrackingUrls="
				+ nonLinearTrackingUrls + ", nonLinearClickUrls=" + nonLinearClickUrls + "]";
	}
}
