package com.gv.midway.pojo.verizon.generic.callback;

public class RestoreResponse {
	private String restored;

    public String getRestored ()
    {
        return restored;
    }

    public void setRestored (String restored)
    {
        this.restored = restored;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [restored = "+restored+"]";
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((restored == null) ? 0 : restored.hashCode());
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
		if (!(obj instanceof RestoreResponse))
			return false;
		RestoreResponse other = (RestoreResponse) obj;
		if (restored == null) {
			if (other.restored != null)
				return false;
		} else if (!restored.equals(other.restored))
			return false;
		return true;
	}
    
}
