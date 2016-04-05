package com.gv.midway.pojo.deactivateDevice.request;

import java.util.Arrays;

public class DeactivateDevices {
	
	private DeactivateDeviceId[] deactivateDeviceIds;

	public DeactivateDeviceId[] getActivateDeviceIds() {
		return deactivateDeviceIds;
	}

	public DeactivateDeviceId[] getDeactivateDeviceIds() {
		return deactivateDeviceIds;
	}

	public void setDeactivateDeviceIds(DeactivateDeviceId[] deactivateDeviceIds) {
		this.deactivateDeviceIds = deactivateDeviceIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(deactivateDeviceIds);
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
		DeactivateDevices other = (DeactivateDevices) obj;
		if (!Arrays.equals(deactivateDeviceIds, other.deactivateDeviceIds))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ActivateDevices [deactivateDeviceIds=");
		builder.append(Arrays.toString(deactivateDeviceIds));
		builder.append("]");
		return builder.toString();
	}

	
	
 
}
