package com.gv.midway.pojo.device.response;

import java.util.Arrays;

public class BatchDeviceResponseDataArea {

	private Integer successCount;
	
	private Integer failedCount;
	
	private BatchDeviceId[] successDevices;
	
	private BatchDeviceId[] failedDevices;

	public Integer getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}

	public Integer getFailedCount() {
		return failedCount;
	}

	public void setFailedCount(Integer failedCount) {
		this.failedCount = failedCount;
	}

	public BatchDeviceId[] getSuccessDevices() {
		return successDevices;
	}

	public void setSuccessDevices(BatchDeviceId[] successDevices) {
		this.successDevices = successDevices;
	}

	public BatchDeviceId[] getFailedDevices() {
		return failedDevices;
	}

	public void setFailedDevices(BatchDeviceId[] failedDevices) {
		this.failedDevices = failedDevices;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((failedCount == null) ? 0 : failedCount.hashCode());
		result = prime * result + Arrays.hashCode(failedDevices);
		result = prime * result
				+ ((successCount == null) ? 0 : successCount.hashCode());
		result = prime * result + Arrays.hashCode(successDevices);
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
		BatchDeviceResponseDataArea other = (BatchDeviceResponseDataArea) obj;
		if (failedCount == null) {
			if (other.failedCount != null)
				return false;
		} else if (!failedCount.equals(other.failedCount))
			return false;
		if (!Arrays.equals(failedDevices, other.failedDevices))
			return false;
		if (successCount == null) {
			if (other.successCount != null)
				return false;
		} else if (!successCount.equals(other.successCount))
			return false;
		if (!Arrays.equals(successDevices, other.successDevices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BatchDeviceResponseDataArea [successCount=");
		builder.append(successCount);
		builder.append(", failedCount=");
		builder.append(failedCount);
		builder.append(", successDevices=");
		builder.append(Arrays.toString(successDevices));
		builder.append(", failedDevices=");
		builder.append(Arrays.toString(failedDevices));
		builder.append("]");
		return builder.toString();
	}
	
	
}
