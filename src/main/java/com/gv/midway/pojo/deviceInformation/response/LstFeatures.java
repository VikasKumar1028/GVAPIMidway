package com.gv.midway.pojo.deviceInformation.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class LstFeatures {
	private String FEAT000602;

	private String FEAT000603;

	private String FEAT000601;

	public String getFEAT000602() {
		return FEAT000602;
	}

	public void setFEAT000602(String FEAT000602) {
		this.FEAT000602 = FEAT000602;
	}

	public String getFEAT000603() {
		return FEAT000603;
	}

	public void setFEAT000603(String FEAT000603) {
		this.FEAT000603 = FEAT000603;
	}

	public String getFEAT000601() {
		return FEAT000601;
	}

	public void setFEAT000601(String FEAT000601) {
		this.FEAT000601 = FEAT000601;
	}

	@Override
	public String toString() {
		return "LstFeatures [FEAT000602 = " + FEAT000602 + ", FEAT000603 = "
				+ FEAT000603 + ", FEAT000601 = " + FEAT000601 + "]";
	}
}
