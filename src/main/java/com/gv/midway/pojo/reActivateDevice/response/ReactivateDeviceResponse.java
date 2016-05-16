package com.gv.midway.pojo.reActivateDevice.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gv.midway.pojo.BaseResponse;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class ReactivateDeviceResponse extends BaseResponse {
	@ApiModelProperty(value = "Data area for Reactivate device response")
	private ReactivateDeviceResponseDataArea dataArea;

	/**
	 * @return the dataArea
	 */
	public ReactivateDeviceResponseDataArea getDataArea() {
		return dataArea;
	}

	/**
	 * @param dataArea the dataArea to set
	 */
	public void setDataArea(ReactivateDeviceResponseDataArea dataArea) {
		this.dataArea = dataArea;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataArea == null) ? 0 : dataArea.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof ReactivateDeviceResponse)) {
			return false;
		}
		ReactivateDeviceResponse other = (ReactivateDeviceResponse) obj;
		if (dataArea == null) {
			if (other.dataArea != null) {
				return false;
			}
		} else if (!dataArea.equals(other.dataArea)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReactivateDeviceResponse [dataArea=" + dataArea + "]";
	}
	
}
