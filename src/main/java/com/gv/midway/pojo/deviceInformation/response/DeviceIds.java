package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceIds {
	private String id;

	private String kind;

	public String getImsiOrMIN() {
		return imsiOrMIN;
	}

	public void setImsiOrMIN(String imsiOrMIN) {
		this.imsiOrMIN = imsiOrMIN;
	}

	public String getMsisdnOrMDN() {
		return msisdnOrMDN;
	}

	public void setMsisdnOrMDN(String msisdnOrMDN) {
		this.msisdnOrMDN = msisdnOrMDN;
	}

	private String imsiOrMIN;
	private String msisdnOrMDN;

	/*
	 * private String IMSIOrMIN; private String MSISDNOrMDN;
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * public String getIMSIOrMIN() { return IMSIOrMIN; }
	 * 
	 * public void setIMSIOrMIN(String IMSIOrMIN) { this.IMSIOrMIN = IMSIOrMIN;
	 * }
	 * 
	 * public String getMSISDNOrMDN() { return MSISDNOrMDN; }
	 * 
	 * public void setMSISDNOrMDN(String MSISDNOrMDN) { this.MSISDNOrMDN =
	 * MSISDNOrMDN; }
	 */
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Override
	public String toString() {
		return "DeviceIds [id=" + id + ", kind=" + kind + ", imsiOrMIN="
				+ imsiOrMIN + ", msisdnOrMDN=" + msisdnOrMDN
				+ ", getImsiOrMIN()=" + getImsiOrMIN() + ", getMsisdnOrMDN()="
				+ getMsisdnOrMDN() + ", getId()=" + getId() + ", getKind()="
				+ getKind() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
}
