package com.gv.midway.pojo.job;

import com.gv.midway.constant.CarrierType;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobParameter {

	@ApiModelProperty(value = "Date of Devices Usages identifier.", required = true)
	private String date;
	@ApiModelProperty(dataType = "string", allowableValues = "VERIZON,	KORE", value = "description", notes = "notes", required = true)
	private CarrierType carrierName;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public CarrierType getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(CarrierType carrierName) {
		this.carrierName = carrierName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
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
		JobParameter other = (JobParameter) obj;
		if (carrierName != other.carrierName)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobParameter [date=" + date + ", carrierName=" + carrierName
				+ "]";
	}

}
