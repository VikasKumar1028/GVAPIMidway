package com.gv.midway.pojo.usageInformation.request;

public class DevicesUsageByDayAndCarrierRequestDataArea {

	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DevicesUsageByDayAndCarrierRequestDataArea other = (DevicesUsageByDayAndCarrierRequestDataArea) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DevicesUsageByDayRequestDataArea [date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}
	
	
}
