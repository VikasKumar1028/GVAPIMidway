package com.gv.midway.pojo.job;

import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

public class JobinitializedResponse extends BaseResponse {

	@ApiModelProperty(value = "Data area for Job initialized status response")
	private JobinitializedResponseDataArea dataArea;

	public JobinitializedResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(JobinitializedResponseDataArea dataArea) {
		this.dataArea = dataArea;
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
		JobinitializedResponse other = (JobinitializedResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobinitializedResponse [dataArea=" + dataArea + "]";
	}

}
