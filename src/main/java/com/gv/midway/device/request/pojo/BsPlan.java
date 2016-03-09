package com.gv.midway.device.request.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "plan_id", "bill_day", "plan_name", "data_amt",
		"data_measure", "features" })
public class BsPlan {

	@JsonProperty("plan_id")
	private String planId;
	@JsonProperty("bill_day")
	private Integer billDay;
	@JsonProperty("plan_name")
	private String planName;
	@JsonProperty("data_amt")
	private Integer dataAmt;
	@JsonProperty("data_measure")
	private String dataMeasure;
	@JsonProperty("features")
	private List<Feature> features = new ArrayList<Feature>();
	

	
	/**
	 * 
	 * @return The planId
	 */
	
	public String getPlanId() {
		return planId;
	}

	/**
	 * 
	 * @param planId
	 *            The plan_id
	 */
	
	public void setPlanId(String planId) {
		this.planId = planId;
	}

	/**
	 * 
	 * @return The billDay
	 */
	
	public Integer getBillDay() {
		return billDay;
	}

	/**
	 * 
	 * @param billDay
	 *            The bill_day
	 */
	
	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}

	/**
	 * 
	 * @return The planName
	 */
	
	public String getPlanName() {
		return planName;
	}

	/**
	 * 
	 * @param planName
	 *            The plan_name
	 */
	
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * 
	 * @return The dataAmt
	 */
	
	public Integer getDataAmt() {
		return dataAmt;
	}

	/**
	 * 
	 * @param dataAmt
	 *            The data_amt
	 */
	
	public void setDataAmt(Integer dataAmt) {
		this.dataAmt = dataAmt;
	}

	/**
	 * 
	 * @return The dataMeasure
	 */
	
	public String getDataMeasure() {
		return dataMeasure;
	}

	/**
	 * 
	 * @param dataMeasure
	 *            The data_measure
	 */
	
	public void setDataMeasure(String dataMeasure) {
		this.dataMeasure = dataMeasure;
	}

	/**
	 * 
	 * @return The features
	 */
	
	public List<Feature> getFeatures() {
		return features;
	}

	/**
	 * 
	 * @param features
	 *            The features
	 */
	
	public void setFeatures(List<Feature> features) {
		this.features = features;
	}

	
	@Override
	public String toString() {
		return "BsPlan [planId=" + planId + ", billDay=" + billDay
				+ ", planName=" + planName + ", dataAmt=" + dataAmt
				+ ", dataMeasure=" + dataMeasure + ", features=" + features
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billDay == null) ? 0 : billDay.hashCode());
		result = prime * result + ((dataAmt == null) ? 0 : dataAmt.hashCode());
		result = prime * result
				+ ((dataMeasure == null) ? 0 : dataMeasure.hashCode());
		result = prime * result
				+ ((features == null) ? 0 : features.hashCode());
		result = prime * result + ((planId == null) ? 0 : planId.hashCode());
		result = prime * result
				+ ((planName == null) ? 0 : planName.hashCode());
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
		BsPlan other = (BsPlan) obj;
		if (billDay == null) {
			if (other.billDay != null)
				return false;
		} else if (!billDay.equals(other.billDay))
			return false;
		if (dataAmt == null) {
			if (other.dataAmt != null)
				return false;
		} else if (!dataAmt.equals(other.dataAmt))
			return false;
		if (dataMeasure == null) {
			if (other.dataMeasure != null)
				return false;
		} else if (!dataMeasure.equals(other.dataMeasure))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (planId == null) {
			if (other.planId != null)
				return false;
		} else if (!planId.equals(other.planId))
			return false;
		if (planName == null) {
			if (other.planName != null)
				return false;
		} else if (!planName.equals(other.planName))
			return false;
		return true;
	}

}
