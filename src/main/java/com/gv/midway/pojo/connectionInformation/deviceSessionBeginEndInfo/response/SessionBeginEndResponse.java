package com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class SessionBeginEndResponse extends BaseResponse {
	
	@ApiModelProperty(value = "Data area for device's session begin and end information" )
	private SessionBeginEndResponseDataArea  dataArea;



	public SessionBeginEndResponseDataArea getDataArea() {
		return dataArea;
	}

	public void setDataArea(SessionBeginEndResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}
	
	@Override
	public String toString() {
		return "SessionBeginEndResponse [dataArea=" + dataArea + "]";
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
		SessionBeginEndResponse other = (SessionBeginEndResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null)
				return false;
		} else if (!dataArea.equals(other.dataArea))
			return false;
		return true;
	}

}
