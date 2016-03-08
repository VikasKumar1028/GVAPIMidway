package com.gv.midway.pojo.request;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "device")

public class Device {
	//@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Id
	private String id;

	
	private Bs_plan bs_plan;

    private String bs_id;

    private Cell cell;

    private String serial_num;

    private String mac;

    private String bs_carrier;

    public Bs_plan getBs_plan ()
    {
        return bs_plan;
    }

    public void setBs_plan (Bs_plan bs_plan)
    {
        this.bs_plan = bs_plan;
    }

    public String getBs_id ()
    {
        return bs_id;
    }

    public void setBs_id (String bs_id)
    {
        this.bs_id = bs_id;
    }

    public Cell getCell ()
    {
        return cell;
    }

    public void setCell (Cell cell)
    {
        this.cell = cell;
    }

    public String getSerial_num ()
    {
        return serial_num;
    }

    public void setSerial_num (String serial_num)
    {
        this.serial_num = serial_num;
    }

    public String getMac ()
    {
        return mac;
    }

    public void setMac (String mac)
    {
        this.mac = mac;
    }

    public String getBs_carrier ()
    {
        return bs_carrier;
    }

    public void setBs_carrier (String bs_carrier)
    {
        this.bs_carrier = bs_carrier;
    }

	@Override
	public String toString() {
		return "Device [id=" + id + ", bs_plan=" + bs_plan + ", bs_id=" + bs_id
				+ ", cell=" + cell + ", serial_num=" + serial_num + ", mac="
				+ mac + ", bs_carrier=" + bs_carrier + "]";
	}

  

}
