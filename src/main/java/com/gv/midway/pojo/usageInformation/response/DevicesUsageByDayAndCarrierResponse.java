package com.gv.midway.pojo.usageInformation.response;


import com.gv.midway.pojo.BaseResponse;

public class DevicesUsageByDayAndCarrierResponse extends BaseResponse{
	
	private DevicesUsageByDayAndCarrierResponseDataArea dateArea;

	public DevicesUsageByDayAndCarrierResponseDataArea getDateArea() {
		return dateArea;
	}

	public void setDateArea(DevicesUsageByDayAndCarrierResponseDataArea dateArea) {
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
		DevicesUsageByDayAndCarrierResponse other = (DevicesUsageByDayAndCarrierResponse) obj;
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
