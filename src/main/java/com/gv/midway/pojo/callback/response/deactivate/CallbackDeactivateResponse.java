package com.gv.midway.pojo.callback.response.deactivate;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class CallbackDeactivateResponse {
    @ApiModelProperty(value = "Deactivated devices in Callback Response .")
    private String deactivated;

    public String getDeactivated() {
        return deactivated;
    }

    public void setDeactivated(String deactivated) {
        this.deactivated = deactivated;
    }

    @Override
    public String toString() {
        return "ClassPojo [deactivated = " + deactivated + "]";
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
                + ((deactivated == null) ? 0 : deactivated.hashCode());
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
        if (!(obj instanceof CallbackDeactivateResponse))
            return false;
        CallbackDeactivateResponse other = (CallbackDeactivateResponse) obj;
        if (deactivated == null) {
            if (other.deactivated != null)
                return false;
        } else if (!deactivated.equals(other.deactivated))
            return false;
        return true;
    }

}
