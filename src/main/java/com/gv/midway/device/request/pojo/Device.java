package com.gv.midway.device.request.pojo;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Document(collection = "deviceInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"cell",
"bs_carrier",
"bs_id",
"serial_num",
"mac",
"bs_plan"
})
public class Device {
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	private String id;

	
	@JsonProperty("cell")
	private Cell cell;
	
	@JsonProperty("bs_carrier")
	private String bsCarrier;
	
	@JsonProperty("bs_id")
	private Integer bsId;
	
	@JsonProperty("serial_num")
	private String serialNum;
	
	@JsonProperty("mac")
	private String mac;
	
	@JsonProperty("bs_plan")
	private BsPlan bsPlan;
	
	@JsonProperty("status")
	private String status;
	

	@JsonProperty("ipAddress")
	private String ipAddress;
	
	@JsonProperty("lastUpdated")
	private String lastUpdated;
	
	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public String getBsCarrier() {
		return bsCarrier;
	}

	public void setBsCarrier(String bsCarrier) {
		this.bsCarrier = bsCarrier;
	}

	public Integer getBsId() {
		return bsId;
	}

	public void setBsId(Integer bsId) {
		this.bsId = bsId;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public BsPlan getBsPlan() {
		return bsPlan;
	}

	public void setBsPlan(BsPlan bsPlan) {
		this.bsPlan = bsPlan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bsCarrier == null) ? 0 : bsCarrier.hashCode());
		result = prime * result + ((bsId == null) ? 0 : bsId.hashCode());
		result = prime * result + ((bsPlan == null) ? 0 : bsPlan.hashCode());
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result
				+ ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result
				+ ((serialNum == null) ? 0 : serialNum.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Device other = (Device) obj;
		if (bsCarrier == null) {
			if (other.bsCarrier != null)
				return false;
		} else if (!bsCarrier.equals(other.bsCarrier))
			return false;
		if (bsId == null) {
			if (other.bsId != null)
				return false;
		} else if (!bsId.equals(other.bsId))
			return false;
		if (bsPlan == null) {
			if (other.bsPlan != null)
				return false;
		} else if (!bsPlan.equals(other.bsPlan))
			return false;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (serialNum == null) {
			if (other.serialNum != null)
				return false;
		} else if (!serialNum.equals(other.serialNum))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", cell=" + cell + ", bsCarrier="
				+ bsCarrier + ", bsId=" + bsId + ", serialNum=" + serialNum
				+ ", mac=" + mac + ", bsPlan=" + bsPlan + ", status=" + status
				+ ", ipAddress=" + ipAddress + ", lastUpdated=" + lastUpdated
				+ "]";
	}
	
	

}
