package com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class DeviceSession {
	
	@ApiModelProperty(value = "Device's session begin information" )
	private String Begin;
	
	@ApiModelProperty(value = "Device's session end information" )
	private String End;



	public String getBegin() {
		return Begin;
	}

	public void setBegin(String begin) {
		Begin = begin;
	}

	public String getEnd() {
		return End;
	}

	public void setEnd(String end) {
		End = end;
	}
	

	@Override
	public String toString() {
		return "DeviceSession [Begin=" + Begin + ", End=" + End + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Begin == null) ? 0 : Begin.hashCode());
		result = prime * result + ((End == null) ? 0 : End.hashCode());
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
		DeviceSession other = (DeviceSession) obj;
		if (Begin == null) {
			if (other.Begin != null)
				return false;
		} else if (!Begin.equals(other.Begin))
			return false;
		if (End == null) {
			if (other.End != null)
				return false;
		} else if (!End.equals(other.End))
			return false;
		return true;
	}
}
