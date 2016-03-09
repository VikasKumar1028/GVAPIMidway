package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceIds {
	private String id;

	private String IMSIOrMIN;

	private String MSISDNOrMDN;

	private String kind;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIMSIOrMIN() {
		return IMSIOrMIN;
	}

	public void setIMSIOrMIN(String IMSIOrMIN) {
		this.IMSIOrMIN = IMSIOrMIN;
	}

	public String getMSISDNOrMDN() {
		return MSISDNOrMDN;
	}

	public void setMSISDNOrMDN(String MSISDNOrMDN) {
		this.MSISDNOrMDN = MSISDNOrMDN;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "DeviceIds [id = " + id + ", IMSIOrMIN = " + IMSIOrMIN
				+ ", MSISDNOrMDN = " + MSISDNOrMDN + ", kind = " + kind + "]";
	}
}
