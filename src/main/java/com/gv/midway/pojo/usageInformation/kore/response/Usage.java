package com.gv.midway.pojo.usageInformation.kore.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Usage {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((DataInBytes == null) ? 0 : DataInBytes.hashCode());
		result = prime * result + ((Sms == null) ? 0 : Sms.hashCode());
		result = prime * result
				+ ((UsageDate == null) ? 0 : UsageDate.hashCode());
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
		Usage other = (Usage) obj;
		if (DataInBytes == null) {
			if (other.DataInBytes != null)
				return false;
		} else if (!DataInBytes.equals(other.DataInBytes))
			return false;
		if (Sms == null) {
			if (other.Sms != null)
				return false;
		} else if (!Sms.equals(other.Sms))
			return false;
		if (UsageDate == null) {
			if (other.UsageDate != null)
				return false;
		} else if (!UsageDate.equals(other.UsageDate))
			return false;
		if (__type == null) {
			if (other.__type != null)
				return false;
		} else if (!__type.equals(other.__type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usage [__type=" + __type + ", UsageDate=" + UsageDate
				+ ", Sms=" + Sms + ", DataInBytes=" + DataInBytes + "]";
	}

	private String __type;

	private String UsageDate;

	private String Sms;

	private String DataInBytes;

	public String get__type() {
		return __type;
	}

	public void set__type(String __type) {
		this.__type = __type;
	}

	public String getUsageDate() {
		return UsageDate;
	}

	public void setUsageDate(String UsageDate) {
		this.UsageDate = UsageDate;
	}

	public String getSms() {
		return Sms;
	}

	public void setSms(String Sms) {
		this.Sms = Sms;
	}

	public String getDataInBytes() {
		return DataInBytes;
	}

	public void setDataInBytes(String DataInBytes) {
		this.DataInBytes = DataInBytes;
	}
}
