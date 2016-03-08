package com.gv.midway.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CarrierInformations {
	private String status;

	private String currentDataPlan;

	private String state;

	private String carrierName;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCurrentDataPlan() {
		return currentDataPlan;
	}

	public void setCurrentDataPlan(String currentDataPlan) {
		this.currentDataPlan = currentDataPlan;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	@Override
	public String toString() {
		return "CarrierInformations [status = " + status
				+ ", currentDataPlan = " + currentDataPlan + ", state = "
				+ state + ", carrierName = " + carrierName + "]";
	}
}
