package com.gv.midway.pojo.usageInformation.kore.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("unused")
@JsonAutoDetect()
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usage {

	@SuppressWarnings("unchecked")
	@JsonSerialize
	@JsonProperty("__type")
	public String get__type() {
		return __type;
	}

	@SuppressWarnings("unchecked")
	@JsonDeserialize
	@JsonProperty("__type")
	public void set__type(String __type) {
		this.__type = __type;
	}

	@SuppressWarnings("unchecked")
	@JsonSerialize
	@JsonProperty("DataInBytes")
	public Double getDataInBytes() {
		return DataInBytes;
	}

	@SuppressWarnings("unchecked")
	@JsonDeserialize
	@JsonProperty("DataInBytes")
	public void setDataInBytes(Double dataInBytes) {
		DataInBytes = dataInBytes;
	}

	@Override
	public String toString() {
		return "Usage [__type=" + __type + ", DataInBytes=" + DataInBytes
				+ ", UsageDate=" + UsageDate + ", Sms=" + Sms + "]";
	}

	@SuppressWarnings("unchecked")
	@JsonSerialize
	@JsonProperty("Sms")
	public Long getSms() {
		return Sms;
	}

	@SuppressWarnings("unchecked")
	@JsonDeserialize
	@JsonProperty("Sms")
	public void setSms(Long sms) {
		Sms = sms;
	}

	private String __type;

	private Double DataInBytes;

	@SuppressWarnings("unchecked")
	@JsonSerialize
	@JsonProperty("UsageDate")
	public String getUsageDate() {
		return UsageDate;
	}

	@SuppressWarnings("unchecked")
	@JsonDeserialize
	@JsonProperty("UsageDate")
	public void setUsageDate(String usageDate) {
		UsageDate = usageDate;
	}

	@JsonIgnore
	private String UsageDate;

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

	private Long Sms;
}
