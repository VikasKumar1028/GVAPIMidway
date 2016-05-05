package com.gv.midway.pojo.callback;
import com.wordnik.swagger.annotations.ApiModelProperty;
public class CallbackFaultResponse {
	@ApiModelProperty(value = "Fault code in Response")
	private String faultcode;

	@ApiModelProperty(value = "Fault Details")
	private String detail;

	    
	@ApiModelProperty(value = "Fault String in Response")
	private String faultstring;

	@ApiModelProperty(value = "Fault Actor in Response")
	private String faultactor;

	    public String getFaultcode ()
	    {
	        return faultcode;
	    }

	    public void setFaultcode (String faultcode)
	    {
	        this.faultcode = faultcode;
	    }

	    public String getDetail ()
	    {
	        return detail;
	    }

	    public void setDetail (String detail)
	    {
	        this.detail = detail;
	    }

	    public String getFaultstring ()
	    {
	        return faultstring;
	    }

	    public void setFaultstring (String faultstring)
	    {
	        this.faultstring = faultstring;
	    }

	    public String getFaultactor ()
	    {
	        return faultactor;
	    }

	    public void setFaultactor (String faultactor)
	    {
	        this.faultactor = faultactor;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [faultcode = "+faultcode+", detail = "+detail+", faultstring = "+faultstring+", faultactor = "+faultactor+"]";
	    }

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((detail == null) ? 0 : detail.hashCode());
			result = prime * result
					+ ((faultactor == null) ? 0 : faultactor.hashCode());
			result = prime * result
					+ ((faultcode == null) ? 0 : faultcode.hashCode());
			result = prime * result
					+ ((faultstring == null) ? 0 : faultstring.hashCode());
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
			if (!(obj instanceof CallbackFaultResponse))
				return false;
			CallbackFaultResponse other = (CallbackFaultResponse) obj;
			if (detail == null) {
				if (other.detail != null)
					return false;
			} else if (!detail.equals(other.detail))
				return false;
			if (faultactor == null) {
				if (other.faultactor != null)
					return false;
			} else if (!faultactor.equals(other.faultactor))
				return false;
			if (faultcode == null) {
				if (other.faultcode != null)
					return false;
			} else if (!faultcode.equals(other.faultcode))
				return false;
			if (faultstring == null) {
				if (other.faultstring != null)
					return false;
			} else if (!faultstring.equals(other.faultstring))
				return false;
			return true;
		}
	    
	}