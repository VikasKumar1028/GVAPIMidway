package com.gv.midway.pojo.usageInformation.kore.response;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class D {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((DataInBytesMtd == null) ? 0 : DataInBytesMtd.hashCode());
		result = prime * result
				+ ((SimNumber == null) ? 0 : SimNumber.hashCode());
		result = prime * result + ((SmsMtd == null) ? 0 : SmsMtd.hashCode());
		result = prime * result + Arrays.hashCode(Usage);
		result = prime * result + ((__type == null) ? 0 : __type.hashCode());
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
		D other = (D) obj;
		if (DataInBytesMtd == null) {
			if (other.DataInBytesMtd != null)
				return false;
		} else if (!DataInBytesMtd.equals(other.DataInBytesMtd))
			return false;
		if (SimNumber == null) {
			if (other.SimNumber != null)
				return false;
		} else if (!SimNumber.equals(other.SimNumber))
			return false;
		if (SmsMtd == null) {
			if (other.SmsMtd != null)
				return false;
		} else if (!SmsMtd.equals(other.SmsMtd))
			return false;
		if (!Arrays.equals(Usage, other.Usage))
			return false;
		if (__type == null) {
			if (other.__type != null)
				return false;
		} else if (!__type.equals(other.__type))
			return false;
		return true;
	}

	private String __type;

	private String SimNumber;

	private String SmsMtd;

	private Usage[] Usage;

	private String DataInBytesMtd;

	public String get__type() {
		return __type;
	}

	public void set__type(String __type) {
		this.__type = __type;
	}

	public String getSimNumber() {
		return SimNumber;
	}

	public void setSimNumber(String SimNumber) {
		this.SimNumber = SimNumber;
	}

	public String getSmsMtd() {
		return SmsMtd;
	}

	public void setSmsMtd(String SmsMtd) {
		this.SmsMtd = SmsMtd;
	}

	public Usage[] getUsage() {
		return Usage;
	}

	public void setUsage(Usage[] Usage) {
		this.Usage = Usage;
	}

	public String getDataInBytesMtd() {
		return DataInBytesMtd;
	}

	public void setDataInBytesMtd(String DataInBytesMtd) {
		this.DataInBytesMtd = DataInBytesMtd;
	}

	@Override
	public String toString() {
		return "D [__type=" + __type + ", SimNumber=" + SimNumber + ", SmsMtd="
				+ SmsMtd + ", Usage=" + Arrays.toString(Usage)
				+ ", DataInBytesMtd=" + DataInBytesMtd + "]";
	}
}
