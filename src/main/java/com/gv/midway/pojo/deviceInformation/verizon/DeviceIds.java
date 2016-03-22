package com.gv.midway.pojo.deviceInformation.verizon;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wordnik.swagger.annotations.ApiModelProperty;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class DeviceIds {
	@ApiModelProperty(value = "The value of the device identifier.")
	private String id;
	
	@ApiModelProperty(value = "The type of the device identifier.") 
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

	@ApiModelProperty(value = "IMSI Or MIN of the device.")
	private String imsiOrMIN;
	@ApiModelProperty(value = "MSISDN Or MDN of the device.")
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
