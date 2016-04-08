package com.gv.midway.pojo.callback;

public class CallbackSmsDeliveryResponse {
	private CallbackFaultResponse faultResponse;

    private String confirmation;

    public CallbackFaultResponse getFaultResponse ()
    {
        return faultResponse;
    }

    public void setFaultResponse (CallbackFaultResponse faultResponse)
    {
        this.faultResponse = faultResponse;
    }

    public String getConfirmation ()
    {
        return confirmation;
    }

    public void setConfirmation (String confirmation)
    {
        this.confirmation = confirmation;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [faultResponse = "+faultResponse+", confirmation = "+confirmation+"]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((confirmation == null) ? 0 : confirmation.hashCode());
		result = prime * result
				+ ((faultResponse == null) ? 0 : faultResponse.hashCode());
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
		if (!(obj instanceof CallbackSmsDeliveryResponse))
			return false;
		CallbackSmsDeliveryResponse other = (CallbackSmsDeliveryResponse) obj;
		if (confirmation == null) {
			if (other.confirmation != null)
				return false;
		} else if (!confirmation.equals(other.confirmation))
			return false;
		if (faultResponse == null) {
			if (other.faultResponse != null)
				return false;
		} else if (!faultResponse.equals(other.faultResponse))
			return false;
		return true;
	}
    
}
