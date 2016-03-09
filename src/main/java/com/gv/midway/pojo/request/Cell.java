package com.gv.midway.pojo.request;

public class Cell {

	private String esn;

	private String sim;

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
		return "ClassPojo [esn = " + esn + ", sim = " + sim + ", mdn = " + mdn
				+ "]";
	}

}
