package com.gv.midway.pojo.kore;

public class DKoreResponseCode {

	private String d;

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
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
		DKoreResponseCode other = (DKoreResponseCode) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DKoreResponseCode [d=" + d + "]";
	}
}
