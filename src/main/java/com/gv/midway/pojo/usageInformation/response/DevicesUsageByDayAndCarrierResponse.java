package com.gv.midway.pojo.usageInformation.response;


import com.gv.midway.pojo.BaseResponse;

public class DevicesUsageByDayAndCarrierResponse extends BaseResponse{
	
	private DevicesUsageByDayAndCarrierResponseDataArea dataArea;

	public DevicesUsageByDayAndCarrierResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(DevicesUsageByDayAndCarrierResponseDataArea dateArea) {
		this.dataArea = dateArea;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((dataArea == null) ? 0 : dataArea.hashCode());
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
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevicesUsageByDayRequest [dataArea=");
		builder.append(dataArea);
		builder.append("]");
		return builder.toString();
	}
	
	

}
