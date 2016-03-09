package com.gv.midway.device.request.pojo;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "esn", "sim", "mdn" })
public class Cell {

	

	@JsonProperty("esn")
	private String esn;
	@JsonProperty("sim")
	private String sim;
	@JsonProperty("mdn")
	private String mdn;
	

	/**
	 * 
	 * @return The esn
	 */
	
	public String getEsn() {
		return esn;
	}

	/**
	 * 
	 * @param esn
	 *            The esn
	 */
	
	public void setEsn(String esn) {
		this.esn = esn;
	}

	/**
	 * 
	 * @return The sim
	 */
	
	public String getSim() {
		return sim;
	}

	/**
	 * 
	 * @param sim
	 *            The sim
	 */
	
	public void setSim(String sim) {
		this.sim = sim;
	}

	/**
	 * 
	 * @return The mdn
	 */
	
	public String getMdn() {
		return mdn;
	}

	/**
	 * 
	 * @param mdn
	 *            The mdn
	 */
	
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	@Override
	public String toString() {
		return "Cell [esn=" + esn + ", sim=" + sim + ", mdn=" + mdn + "]";
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
