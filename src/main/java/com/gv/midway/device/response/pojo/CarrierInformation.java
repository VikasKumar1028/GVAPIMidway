package com.gv.midway.device.response.pojo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "carrierName", "servicePlan", "state" })
public class CarrierInformation {

	

	@JsonProperty("carrierName")
	private String carrierName;
	@JsonProperty("servicePlan")
	private String servicePlan;
	@JsonProperty("state")
	private String state;

	/**
	 * 
	 * @return The carrierName
	 */
	
	public String getCarrierName() {
		return carrierName;
	}

	/**
	 * 
	 * @param carrierName
	 *            The carrierName
	 */
	
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	/**
	 * 
	 * @return The servicePlan
	 */
	
	public String getServicePlan() {
		return servicePlan;
	}

	/**
	 * 
	 * @param servicePlan
	 *            The servicePlan
	 */
	
	public void setServicePlan(String servicePlan) {
		this.servicePlan = servicePlan;
	}

	/**
	 * 
	 * @return The state
	 */
	
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	
	public void setState(String state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "CarrierInformation [carrierName=" + carrierName
				+ ", servicePlan=" + servicePlan + ", state=" + state + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result
				+ ((servicePlan == null) ? 0 : servicePlan.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		CarrierInformation other = (CarrierInformation) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (servicePlan == null) {
			if (other.servicePlan != null)
				return false;
		} else if (!servicePlan.equals(other.servicePlan))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

}