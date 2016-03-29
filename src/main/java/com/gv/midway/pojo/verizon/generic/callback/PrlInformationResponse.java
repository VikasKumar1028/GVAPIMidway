package com.gv.midway.pojo.verizon.generic.callback;

public class PrlInformationResponse {
	 private String prlVersion;

	    private FaultResponse faultResponse;

	    public String getPrlVersion ()
	    {
	        return prlVersion;
	    }

	    public void setPrlVersion (String prlVersion)
	    {
	        this.prlVersion = prlVersion;
	    }

	    public FaultResponse getFaultResponse ()
	    {
	        return faultResponse;
	    }

	    public void setFaultResponse (FaultResponse faultResponse)
	    {
	        this.faultResponse = faultResponse;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [prlVersion = "+prlVersion+", faultResponse = "+faultResponse+"]";
	    }

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((faultResponse == null) ? 0 : faultResponse.hashCode());
			result = prime * result
					+ ((prlVersion == null) ? 0 : prlVersion.hashCode());
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
			if (!(obj instanceof PrlInformationResponse))
				return false;
			PrlInformationResponse other = (PrlInformationResponse) obj;
			if (faultResponse == null) {
				if (other.faultResponse != null)
					return false;
			} else if (!faultResponse.equals(other.faultResponse))
				return false;
			if (prlVersion == null) {
				if (other.prlVersion != null)
					return false;
			} else if (!prlVersion.equals(other.prlVersion))
				return false;
			return true;
		}
	    
	}
