package com.gv.midway.pojo.usageView;

import java.util.Date;

import com.gv.midway.pojo.verizon.DeviceId;


public class DeviceUsageViewElement {

    private DeviceId deviceId;
    private Integer netSuiteId;
    private float dataUsed;
    private String jobId;
    private Date lastTimeStampUpdated;
    private Boolean isUpdatedElement;
    
    
    public DeviceId getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(DeviceId deviceId) {
        this.deviceId = deviceId;
    }
    public Integer getNetSuiteId() {
        return netSuiteId;
    }
    public void setNetSuiteId(Integer netSuiteId) {
        this.netSuiteId = netSuiteId;
    }
    public float getDataUsed() {
        return dataUsed;
    }
    public void setDataUsed(float dataUsed) {
        this.dataUsed = dataUsed;
    }
    public String getJobId() {
        return jobId;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public Date getLastTimeStampUpdated() {
        return lastTimeStampUpdated;
    }
    public void setLastTimeStampUpdated(Date lastTimeStampUpdated) {
        this.lastTimeStampUpdated = lastTimeStampUpdated;
    }
    public Boolean getIsUpdatedElement() {
        return isUpdatedElement;
    }
    public void setIsUpdatedElement(Boolean isUpdatedElement) {
        this.isUpdatedElement = isUpdatedElement;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Float.floatToIntBits(dataUsed);
        result = prime * result
                + ((deviceId == null) ? 0 : deviceId.hashCode());
        result = prime
                * result
                + ((isUpdatedElement == null) ? 0 : isUpdatedElement.hashCode());
        result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
        result = prime
                * result
                + ((lastTimeStampUpdated == null) ? 0 : lastTimeStampUpdated
                        .hashCode());
        result = prime * result
                + ((netSuiteId == null) ? 0 : netSuiteId.hashCode());
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
        DeviceUsageViewElement other = (DeviceUsageViewElement) obj;
        if (Float.floatToIntBits(dataUsed) != Float
                .floatToIntBits(other.dataUsed))
            return false;
        if (deviceId == null) {
            if (other.deviceId != null)
                return false;
        } else if (!deviceId.equals(other.deviceId))
            return false;
        if (isUpdatedElement == null) {
            if (other.isUpdatedElement != null)
                return false;
        } else if (!isUpdatedElement.equals(other.isUpdatedElement))
            return false;
        if (jobId == null) {
            if (other.jobId != null)
                return false;
        } else if (!jobId.equals(other.jobId))
            return false;
        if (lastTimeStampUpdated == null) {
            if (other.lastTimeStampUpdated != null)
                return false;
        } else if (!lastTimeStampUpdated.equals(other.lastTimeStampUpdated))
            return false;
        if (netSuiteId == null) {
            if (other.netSuiteId != null)
                return false;
        } else if (!netSuiteId.equals(other.netSuiteId))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "DeviceUsageViewElement [deviceId=" + deviceId + ", netSuiteId="
                + netSuiteId + ", dataUsed=" + dataUsed + ", jobId=" + jobId
                + ", lastTimeStampUpdated=" + lastTimeStampUpdated
                + ", isUpdatedElement=" + isUpdatedElement + "]";
    }
 

   
}
