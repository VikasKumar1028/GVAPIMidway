package com.gv.midway.pojo.callback;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CallbackRestoreResponse {
    @ApiModelProperty(value = "Restored Devices.")
    private String restored;

    public String getRestored() {
        return restored;
    }

    public void setRestored(String restored) {
        this.restored = restored;
    }

    @Override
    public String toString() {
        return "ClassPojo [restored = " + restored + "]";
    }

    /*
     * (non-Javadoc)
     * 
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

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof CallbackRestoreResponse))
            return false;
        CallbackRestoreResponse other = (CallbackRestoreResponse) obj;
        if (restored == null) {
            if (other.restored != null)
                return false;
        } else if (!restored.equals(other.restored))
            return false;
        return true;
    }

}
