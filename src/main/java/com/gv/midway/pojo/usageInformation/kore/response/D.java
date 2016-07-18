package com.gv.midway.pojo.usageInformation.kore.response;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("unused")
@JsonAutoDetect()
@JsonIgnoreProperties(ignoreUnknown = true)
public class D {

    @Override
    public String toString() {
        return "D [__type=" + __type + ", DataInBytesMtd=" + DataInBytesMtd
                + ", SimNumber=" + SimNumber + ", SmsMtd=" + SmsMtd
                + ", Usage=" + Usage + "]";
    }

    private String __type;

    private Double DataInBytesMtd;
    private String SimNumber;
    private Long SmsMtd;

    // private String Usage[];

    private List<Usage> Usage;

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("Usage")
    public List<Usage> getUsage() {
        return Usage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((DataInBytesMtd == null) ? 0 : DataInBytesMtd.hashCode());
        result = prime * result
                + ((SimNumber == null) ? 0 : SimNumber.hashCode());
        result = prime * result + ((SmsMtd == null) ? 0 : SmsMtd.hashCode());
        result = prime * result + ((Usage == null) ? 0 : Usage.hashCode());
        result = prime * result + ((__type == null) ? 0 : __type.hashCode());
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
        D other = (D) obj;
        if (DataInBytesMtd == null) {
            if (other.DataInBytesMtd != null)
                return false;
        } else if (!DataInBytesMtd.equals(other.DataInBytesMtd))
            return false;
        if (SimNumber == null) {
            if (other.SimNumber != null)
                return false;
        } else if (!SimNumber.equals(other.SimNumber))
            return false;
        if (SmsMtd == null) {
            if (other.SmsMtd != null)
                return false;
        } else if (!SmsMtd.equals(other.SmsMtd))
            return false;
        if (Usage == null) {
            if (other.Usage != null)
                return false;
        } else if (!Usage.equals(other.Usage))
            return false;
        if (__type == null) {
            if (other.__type != null)
                return false;
        } else if (!__type.equals(other.__type))
            return false;
        return true;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("Usage")
    public void setUsage(List<Usage> usage) {
        Usage = usage;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("__type")
    public String get__type() {
        return __type;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("__type")
    public void set__type(String __type) {
        this.__type = __type;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("DataInBytesMtd")
    public Double getDataInBytesMtd() {
        return DataInBytesMtd;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("DataInBytesMtd")
    public void setDataInBytesMtd(Double dataInBytesMtd) {
        DataInBytesMtd = dataInBytesMtd;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("SimNumber")
    public String getSimNumber() {
        return SimNumber;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("SimNumber")
    public void setSimNumber(String simNumber) {
        SimNumber = simNumber;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("SmsMtd")
    public Long getSmsMtd() {
        return SmsMtd;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("SmsMtd")
    public void setSmsMtd(Long smsMtd) {
        SmsMtd = smsMtd;
    }

}
