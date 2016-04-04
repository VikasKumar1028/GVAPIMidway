package com.gv.midway.pojo.deviceInformation.response;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class Cell {

	
	@ApiModelProperty(value = "esn number of the device.")
	private String esn;

	@ApiModelProperty(value = "sim number of the device.")
	private String sim;

	@ApiModelProperty(value = "mdn number of the device.")
	private String mdn;

	public String getEsn() {
		return esn;
	}

	public void setEsn(String esn) {
		this.esn = esn;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cell [esn=");
		builder.append(esn);
		builder.append(", sim=");
		builder.append(sim);
		builder.append(", mdn=");
		builder.append(mdn);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((esn == null) ? 0 : esn.hashCode());
		result = prime * result + ((mdn == null) ? 0 : mdn.hashCode());
		result = prime * result + ((sim == null) ? 0 : sim.hashCode());
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
		Cell other = (Cell) obj;
		if (esn == null) {
			if (other.esn != null)
				return false;
		} else if (!esn.equals(other.esn))
			return false;
		if (mdn == null) {
			if (other.mdn != null)
				return false;
		} else if (!mdn.equals(other.mdn))
			return false;
		if (sim == null) {
			if (other.sim != null)
				return false;
		} else if (!sim.equals(other.sim))
			return false;
		return true;
	}


}
