package com.gv.midway.pojo.server;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "serverDetail")
public class ServerDetail {

	private String name;
	private String ipAddress;
	private String jobParameter;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getJobParameter() {
		return jobParameter;
	}
	public void setJobParameter(String jobParameter) {
		this.jobParameter = jobParameter;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((jobParameter == null) ? 0 : jobParameter.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ServerDetail other = (ServerDetail) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (jobParameter == null) {
			if (other.jobParameter != null)
				return false;
		} else if (!jobParameter.equals(other.jobParameter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ServerDetail [name=" + name + ", ipAddress=" + ipAddress
				+ ", jobParameter=" + jobParameter + "]";
	}
}