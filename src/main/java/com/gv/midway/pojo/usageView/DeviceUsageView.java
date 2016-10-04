package com.gv.midway.pojo.usageView;

import java.util.Arrays;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "deviceUsageView")
public class DeviceUsageView {


    private String date;
    private String carrierName;
    private DeviceUsageViewElement[] elements;
    
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getCarrierName() {
        return carrierName;
    }
    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }
    public DeviceUsageViewElement[] getElements() {
        return elements;
    }
    public void setElements(DeviceUsageViewElement[] elements) {
        this.elements = elements;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((carrierName == null) ? 0 : carrierName.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + Arrays.hashCode(elements);
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
        DeviceUsageView other = (DeviceUsageView) obj;
        if (carrierName == null) {
            if (other.carrierName != null)
                return false;
        } else if (!carrierName.equals(other.carrierName))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (!Arrays.equals(elements, other.elements))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "DeviceUsageView [date=" + date + ", carrierName=" + carrierName
                + ", elements=" + Arrays.toString(elements) + "]";
    }
  
}
