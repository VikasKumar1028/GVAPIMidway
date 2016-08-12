package com.gv.midway.pojo.usageInformation.request;

import com.gv.midway.pojo.BaseRequest;

public class DevicesUsageByDayAndCarrierRequest extends BaseRequest {
	
	private DevicesUsageByDayAndCarrierRequestDataArea dateArea;

	public DevicesUsageByDayAndCarrierRequestDataArea getDateArea() {
		return dateArea;
	}

	public void setDateArea(DevicesUsageByDayAndCarrierRequestDataArea dateArea) {
		this.dateArea = dateArea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dateArea == null) ? 0 : dateArea.hashCode());
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
		DevicesUsageByDayAndCarrierRequest other = (DevicesUsageByDayAndCarrierRequest) obj;
		if (dateArea == null) {
			if (other.dateArea != null)
				return false;
		} else if (!dateArea.equals(other.dateArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevicesUsageByDayRequest [dateArea=");
		builder.append(dateArea);
		builder.append("]");
		return builder.toString();
	}
	
	

}
