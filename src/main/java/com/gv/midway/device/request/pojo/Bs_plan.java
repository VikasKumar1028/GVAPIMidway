package com.gv.midway.device.request.pojo;

import java.util.Arrays;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Bs_plan {

	

	@ApiModelProperty(value = "data measure unit. like KB, MB")
	private String data_measure;

	@ApiModelProperty(value = "The list of featues for the device.")
	private Features[] features;

	@ApiModelProperty(value = "value of the data amount in plan.")
	private String data_amt;

	@ApiModelProperty(value = "Bill date for the customer.")
	private String bill_day;

	@ApiModelProperty(value = "Plan for the customer.")
	private String plan_name;

	@ApiModelProperty(value = "Plan Id for the customer.")
	private String plan_id;

	public String getData_measure() {
		return data_measure;
	}

	public void setData_measure(String data_measure) {
		this.data_measure = data_measure;
	}

	public Features[] getFeatures() {
		return features;
	}

	public void setFeatures(Features[] features) {
		this.features = features;
	}

	public String getData_amt() {
		return data_amt;
	}

	public void setData_amt(String data_amt) {
		this.data_amt = data_amt;
	}

	public String getBill_day() {
		return bill_day;
	}

	public void setBill_day(String bill_day) {
		this.bill_day = bill_day;
	}

	public String getPlan_name() {
		return plan_name;
	}

	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}

	public String getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(String plan_id) {
		this.plan_id = plan_id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Bs_plan [data_measure=");
		builder.append(data_measure);
		builder.append(", features=");
		builder.append(Arrays.toString(features));
		builder.append(", data_amt=");
		builder.append(data_amt);
		builder.append(", bill_day=");
		builder.append(bill_day);
		builder.append(", plan_name=");
		builder.append(plan_name);
		builder.append(", plan_id=");
		builder.append(plan_id);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bill_day == null) ? 0 : bill_day.hashCode());
		result = prime * result
				+ ((data_amt == null) ? 0 : data_amt.hashCode());
		result = prime * result
				+ ((data_measure == null) ? 0 : data_measure.hashCode());
		result = prime * result + Arrays.hashCode(features);
		result = prime * result + ((plan_id == null) ? 0 : plan_id.hashCode());
		result = prime * result
				+ ((plan_name == null) ? 0 : plan_name.hashCode());
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
		Bs_plan other = (Bs_plan) obj;
		if (bill_day == null) {
			if (other.bill_day != null)
				return false;
		} else if (!bill_day.equals(other.bill_day))
			return false;
		if (data_amt == null) {
			if (other.data_amt != null)
				return false;
		} else if (!data_amt.equals(other.data_amt))
			return false;
		if (data_measure == null) {
			if (other.data_measure != null)
				return false;
		} else if (!data_measure.equals(other.data_measure))
			return false;
		if (!Arrays.equals(features, other.features))
			return false;
		if (plan_id == null) {
			if (other.plan_id != null)
				return false;
		} else if (!plan_id.equals(other.plan_id))
			return false;
		if (plan_name == null) {
			if (other.plan_name != null)
				return false;
		} else if (!plan_name.equals(other.plan_name))
			return false;
		return true;
	}

}
