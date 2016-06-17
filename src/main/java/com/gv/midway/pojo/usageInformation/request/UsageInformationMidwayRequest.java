package com.gv.midway.pojo.usageInformation.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gv.midway.pojo.BaseRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsageInformationMidwayRequest extends BaseRequest {

	private UsageInformationRequestMidwayDataArea usageInformationRequestMidwayDataArea;

	public UsageInformationRequestMidwayDataArea getUsageInformationRequestMidwayDataArea() {
		return usageInformationRequestMidwayDataArea;
	}

	public void setUsageInformationRequestMidwayDataArea(
			UsageInformationRequestMidwayDataArea usageInformationRequestMidwayDataArea) {
		this.usageInformationRequestMidwayDataArea = usageInformationRequestMidwayDataArea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime
				* result
				+ ((usageInformationRequestMidwayDataArea == null) ? 0
						: usageInformationRequestMidwayDataArea.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsageInformationMidwayRequest other = (UsageInformationMidwayRequest) obj;
		if (usageInformationRequestMidwayDataArea == null) {
			if (other.usageInformationRequestMidwayDataArea != null)
				return false;
		} else if (!usageInformationRequestMidwayDataArea
				.equals(other.usageInformationRequestMidwayDataArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsageInformationMidwayRequest [usageInformationRequestMidwayDataArea="
				+ usageInformationRequestMidwayDataArea + "]";
	}
}
