package com.gv.midway.device.response.pojo;

import java.util.ArrayList;
import java.util.List;

import com.gv.midway.device.request.pojo.Cell;

public class BatchInsertResponse {

	private String message;
	
	private int insert_count;
	
	private int error_count;
	
	private List<InsertCell> insert_cells = new ArrayList<InsertCell>();
	
	private List<Cell> error_cells=new ArrayList<Cell>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getInsert_count() {
		return insert_count;
	}

	public void setInsert_count(int insert_count) {
		this.insert_count = insert_count;
	}

	public int getError_count() {
		return error_count;
	}

	public void setError_count(int error_count) {
		this.error_count = error_count;
	}

	public List<InsertCell> getInsert_cells() {
		return insert_cells;
	}

	public void setInsert_cells(List<InsertCell> insert_cells) {
		this.insert_cells = insert_cells;
	}

	public List<Cell> getError_cells() {
		return error_cells;
	}

	public void setError_cells(List<Cell> error_cells) {
		this.error_cells = error_cells;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((error_cells == null) ? 0 : error_cells.hashCode());
		result = prime * result + error_count;
		result = prime * result
				+ ((insert_cells == null) ? 0 : insert_cells.hashCode());
		result = prime * result + insert_count;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		BatchInsertResponse other = (BatchInsertResponse) obj;
		if (error_cells == null) {
			if (other.error_cells != null)
				return false;
		} else if (!error_cells.equals(other.error_cells))
			return false;
		if (error_count != other.error_count)
			return false;
		if (insert_cells == null) {
			if (other.insert_cells != null)
				return false;
		} else if (!insert_cells.equals(other.insert_cells))
			return false;
		if (insert_count != other.insert_count)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BatchInsertResponse [message=" + message + ", insert_count="
				+ insert_count + ", error_count=" + error_count
				+ ", insert_cells=" + insert_cells + ", error_cells="
				+ error_cells + "]";
	}
	
}
