package com.gv.midway.pojo.callback;

public class CallbackSummary {
	private String devicesRequested;

    public String getDevicesRequested ()
    {
        return devicesRequested;
    }

    public void setDevicesRequested (String devicesRequested)
    {
        this.devicesRequested = devicesRequested;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [devicesRequested = "+devicesRequested+"]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((devicesRequested == null) ? 0 : devicesRequested.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CallbackSummary))
			return false;
		CallbackSummary other = (CallbackSummary) obj;
		if (devicesRequested == null) {
			if (other.devicesRequested != null)
				return false;
		} else if (!devicesRequested.equals(other.devicesRequested))
			return false;
		return true;
	}
    
}
