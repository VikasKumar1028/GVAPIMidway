package com.gv.midway.pojo.connectionInformation.verizon.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceEvents {

	private String eventType;
	private String bytesUsed;
	private String occurredAt;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getBytesUsed() {
		return bytesUsed;
	}

	public void setBytesUsed(String bytesUsed) {
		this.bytesUsed = bytesUsed;
	}

	public String getOccurredAt() {
		return occurredAt;
	}

	public void setOccurredAt(String occurredAt) {
		this.occurredAt = occurredAt;
	}

	@Override
	public String toString() {
		return "DeviceEvents [eventType=" + eventType + ", bytesUsed="
				+ bytesUsed + ", occurredAt=" + occurredAt + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bytesUsed == null) ? 0 : bytesUsed.hashCode());
		result = prime * result
				+ ((eventType == null) ? 0 : eventType.hashCode());
		result = prime * result
				+ ((occurredAt == null) ? 0 : occurredAt.hashCode());
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
		DeviceEvents other = (DeviceEvents) obj;
		if (bytesUsed == null) {
			if (other.bytesUsed != null)
				return false;
		} else if (!bytesUsed.equals(other.bytesUsed))
			return false;
		if (eventType == null) {
			if (other.eventType != null)
				return false;
		} else if (!eventType.equals(other.eventType))
			return false;
		if (occurredAt == null) {
			if (other.occurredAt != null)
				return false;
		} else if (!occurredAt.equals(other.occurredAt))
			return false;
		return true;
	}

}
