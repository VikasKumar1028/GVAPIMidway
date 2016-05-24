package com.gv.midway.job;

public class JobParameter {

	private String date;
	private String carrierName;
	private boolean reRunComplete;
	private boolean renRunTransactinFailure;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public boolean isReRunComplete() {
		return reRunComplete;
	}

	public void setReRunComplete(boolean reRunComplete) {
		this.reRunComplete = reRunComplete;
	}

	public boolean isRenRunTransactinFailure() {
		return renRunTransactinFailure;
	}

	public void setRenRunTransactinFailure(boolean renRunTransactinFailure) {
		this.renRunTransactinFailure = renRunTransactinFailure;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((carrierName == null) ? 0 : carrierName.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (reRunComplete ? 1231 : 1237);
		result = prime * result + (renRunTransactinFailure ? 1231 : 1237);
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
		JobParameter other = (JobParameter) obj;
		if (carrierName == null) {
			if (other.carrierName != null)
				return false;
		} else if (!carrierName.equals(other.carrierName))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (reRunComplete != other.reRunComplete)
			return false;
		if (renRunTransactinFailure != other.renRunTransactinFailure)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JobParameter [date=" + date + ", carrierName=" + carrierName
				+ ", reRunComplete=" + reRunComplete
				+ ", renRunTransactinFailure=" + renRunTransactinFailure + "]";
	}
}
