package com.gv.midway.pojo.deviceInformation.kore.response;

import java.util.Arrays;
import java.util.Date;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("unused")
@JsonAutoDetect()
public class D {
    private String __type;

    private String requestStatus;

    private String MSISDNOrMDN;

    private String IMSIOrMIN;

    private String status;

    private String currentDataPlan;

    private String currentSMSPlan;

    private String futureDataPlan;

    private String futureSMSPlan;

    private Integer dailyDataThreshold;

    private Integer dailySMSThreshold;

    private Integer monthlyDataThreshold;

    private Integer monthlySMSThreshold;

    private String customField1;

    private String customField2;

    private String customField3;

    private String customField4;

    private String customField5;

    private String customField6;

    private String[] lstHistoryOverLastYear;

    private String[] lstFeatures;

    private String staticIP;

    private String voiceDispatchNumber;

    private Integer mostRecentLocateId;

    private Integer previousLocateId;

    private Object mostRecentLocateDate;

    private Double mostRecentLatitude;

    private Double mostRecentLongitude;

    private String mostRecentAddress;

    private Object previousLocateDate;

    private Double previousLatitude;

    private Double previousLongitude;

    private String previousAddress;

    private String[] lstExtFeatures;

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
    @JsonProperty("requestStatus")
    public String getRequestStatus() {
        return requestStatus;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("requestStatus")
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("MSISDNOrMDN")
    public String getMSISDNOrMDN() {
        return MSISDNOrMDN;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("MSISDNOrMDN")
    public void setMSISDNOrMDN(String mSISDNOrMDN) {
        MSISDNOrMDN = mSISDNOrMDN;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("IMSIOrMIN")
    public String getIMSIOrMIN() {
        return IMSIOrMIN;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("IMSIOrMIN")
    public void setIMSIOrMIN(String iMSIOrMIN) {
        IMSIOrMIN = iMSIOrMIN;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("currentDataPlan")
    public String getCurrentDataPlan() {
        return currentDataPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("currentDataPlan")
    public void setCurrentDataPlan(String currentDataPlan) {
        this.currentDataPlan = currentDataPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("currentSMSPlan")
    public String getCurrentSMSPlan() {
        return currentSMSPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("currentSMSPlan")
    public void setCurrentSMSPlan(String currentSMSPlan) {
        this.currentSMSPlan = currentSMSPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("futureDataPlan")
    public String getFutureDataPlan() {
        return futureDataPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("futureDataPlan")
    public void setFutureDataPlan(String futureDataPlan) {
        this.futureDataPlan = futureDataPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("futureSMSPlan")
    public String getFutureSMSPlan() {
        return futureSMSPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("futureSMSPlan")
    public void setFutureSMSPlan(String futureSMSPlan) {
        this.futureSMSPlan = futureSMSPlan;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("dailyDataThreshold")
    public Integer getDailyDataThreshold() {
        return dailyDataThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("dailyDataThreshold")
    public void setDailyDataThreshold(Integer dailyDataThreshold) {
        this.dailyDataThreshold = dailyDataThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("dailySMSThreshold")
    public Integer getDailySMSThreshold() {
        return dailySMSThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("dailySMSThreshold")
    public void setDailySMSThreshold(Integer dailySMSThreshold) {
        this.dailySMSThreshold = dailySMSThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("monthlyDataThreshold")
    public Integer getMonthlyDataThreshold() {
        return monthlyDataThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("monthlyDataThreshold")
    public void setMonthlyDataThreshold(Integer monthlyDataThreshold) {
        this.monthlyDataThreshold = monthlyDataThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("monthlySMSThreshold")
    public Integer getMonthlySMSThreshold() {
        return monthlySMSThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("monthlySMSThreshold")
    public void setMonthlySMSThreshold(Integer monthlySMSThreshold) {
        this.monthlySMSThreshold = monthlySMSThreshold;
    }

    @SuppressWarnings("unchecked")
    @JsonSerialize
    @JsonProperty("customField1")
    public String getCustomField1() {
        return customField1;
    }

    @SuppressWarnings("unchecked")
    @JsonDeserialize
    @JsonProperty("customField1")
    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public String getCustomField2() {
        return customField2;
    }

    public void setCustomField2(String customField2) {
        this.customField2 = customField2;
    }

    public String getCustomField3() {
        return customField3;
    }

    public void setCustomField3(String customField3) {
        this.customField3 = customField3;
    }

    public String getCustomField4() {
        return customField4;
    }

    public void setCustomField4(String customField4) {
        this.customField4 = customField4;
    }

    public String getCustomField5() {
        return customField5;
    }

    public void setCustomField5(String customField5) {
        this.customField5 = customField5;
    }

    public String getCustomField6() {
        return customField6;
    }

    public void setCustomField6(String customField6) {
        this.customField6 = customField6;
    }

    public String[] getLstHistoryOverLastYear() {
        return lstHistoryOverLastYear;
    }

    public void setLstHistoryOverLastYear(String[] lstHistoryOverLastYear) {
        this.lstHistoryOverLastYear = lstHistoryOverLastYear;
    }

    public String[] getLstFeatures() {
        return lstFeatures;
    }

    public void setLstFeatures(String[] lstFeatures) {
        this.lstFeatures = lstFeatures;
    }

    public String getStaticIP() {
        return staticIP;
    }

    public void setStaticIP(String staticIP) {
        this.staticIP = staticIP;
    }

    public String getVoiceDispatchNumber() {
        return voiceDispatchNumber;
    }

    public void setVoiceDispatchNumber(String voiceDispatchNumber) {
        this.voiceDispatchNumber = voiceDispatchNumber;
    }

    public Integer getMostRecentLocateId() {
        return mostRecentLocateId;
    }

    public void setMostRecentLocateId(Integer mostRecentLocateId) {
        this.mostRecentLocateId = mostRecentLocateId;
    }

    public Integer getPreviousLocateId() {
        return previousLocateId;
    }

    public void setPreviousLocateId(Integer previousLocateId) {
        this.previousLocateId = previousLocateId;
    }

    public Object getMostRecentLocateDate() {
        return mostRecentLocateDate;
    }

    public void setMostRecentLocateDate(Object mostRecentLocateDate) {
        this.mostRecentLocateDate = mostRecentLocateDate;
    }

    public Double getMostRecentLatitude() {
        return mostRecentLatitude;
    }

    public void setMostRecentLatitude(Double mostRecentLatitude) {
        this.mostRecentLatitude = mostRecentLatitude;
    }

    public Double getMostRecentLongitude() {
        return mostRecentLongitude;
    }

    public void setMostRecentLongitude(Double mostRecentLongitude) {
        this.mostRecentLongitude = mostRecentLongitude;
    }

    public String getMostRecentAddress() {
        return mostRecentAddress;
    }

    public void setMostRecentAddress(String mostRecentAddress) {
        this.mostRecentAddress = mostRecentAddress;
    }

    public Object getPreviousLocateDate() {
        return previousLocateDate;
    }

    public void setPreviousLocateDate(Object previousLocateDate) {
        this.previousLocateDate = previousLocateDate;
    }

    public Double getPreviousLatitude() {
        return previousLatitude;
    }

    public void setPreviousLatitude(Double previousLatitude) {
        this.previousLatitude = previousLatitude;
    }

    public Double getPreviousLongitude() {
        return previousLongitude;
    }

    public void setPreviousLongitude(Double previousLongitude) {
        this.previousLongitude = previousLongitude;
    }

    public String getPreviousAddress() {
        return previousAddress;
    }

    public void setPreviousAddress(String previousAddress) {
        this.previousAddress = previousAddress;
    }

    public String[] getLstExtFeatures() {
        return lstExtFeatures;
    }

    public void setLstExtFeatures(String[] lstExtFeatures) {
        this.lstExtFeatures = lstExtFeatures;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("D [__type=");
        builder.append(__type);
        builder.append(", requestStatus=");
        builder.append(requestStatus);
        builder.append(", MSISDNOrMDN=");
        builder.append(MSISDNOrMDN);
        builder.append(", IMSIOrMIN=");
        builder.append(IMSIOrMIN);
        builder.append(", status=");
        builder.append(status);
        builder.append(", currentDataPlan=");
        builder.append(currentDataPlan);
        builder.append(", currentSMSPlan=");
        builder.append(currentSMSPlan);
        builder.append(", futureDataPlan=");
        builder.append(futureDataPlan);
        builder.append(", futureSMSPlan=");
        builder.append(futureSMSPlan);
        builder.append(", dailyDataThreshold=");
        builder.append(dailyDataThreshold);
        builder.append(", dailySMSThreshold=");
        builder.append(dailySMSThreshold);
        builder.append(", monthlyDataThreshold=");
        builder.append(monthlyDataThreshold);
        builder.append(", monthlySMSThreshold=");
        builder.append(monthlySMSThreshold);
        builder.append(", customField1=");
        builder.append(customField1);
        builder.append(", customField2=");
        builder.append(customField2);
        builder.append(", customField3=");
        builder.append(customField3);
        builder.append(", customField4=");
        builder.append(customField4);
        builder.append(", customField5=");
        builder.append(customField5);
        builder.append(", customField6=");
        builder.append(customField6);
        builder.append(", lstHistoryOverLastYear=");
        builder.append(Arrays.toString(lstHistoryOverLastYear));
        builder.append(", lstFeatures=");
        builder.append(Arrays.toString(lstFeatures));
        builder.append(", staticIP=");
        builder.append(staticIP);
        builder.append(", voiceDispatchNumber=");
        builder.append(voiceDispatchNumber);
        builder.append(", mostRecentLocateId=");
        builder.append(mostRecentLocateId);
        builder.append(", previousLocateId=");
        builder.append(previousLocateId);
        builder.append(", mostRecentLocateDate=");
        builder.append(mostRecentLocateDate);
        builder.append(", mostRecentLatitude=");
        builder.append(mostRecentLatitude);
        builder.append(", mostRecentLongitude=");
        builder.append(mostRecentLongitude);
        builder.append(", mostRecentAddress=");
        builder.append(mostRecentAddress);
        builder.append(", previousLocateDate=");
        builder.append(previousLocateDate);
        builder.append(", previousLatitude=");
        builder.append(previousLatitude);
        builder.append(", previousLongitude=");
        builder.append(previousLongitude);
        builder.append(", previousAddress=");
        builder.append(previousAddress);
        builder.append(", lstExtFeatures=");
        builder.append(Arrays.toString(lstExtFeatures));
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((IMSIOrMIN == null) ? 0 : IMSIOrMIN.hashCode());
        result = prime * result
                + ((MSISDNOrMDN == null) ? 0 : MSISDNOrMDN.hashCode());
        result = prime * result + ((__type == null) ? 0 : __type.hashCode());
        result = prime * result
                + ((currentDataPlan == null) ? 0 : currentDataPlan.hashCode());
        result = prime * result
                + ((currentSMSPlan == null) ? 0 : currentSMSPlan.hashCode());
        result = prime * result
                + ((customField1 == null) ? 0 : customField1.hashCode());
        result = prime * result
                + ((customField2 == null) ? 0 : customField2.hashCode());
        result = prime * result
                + ((customField3 == null) ? 0 : customField3.hashCode());
        result = prime * result
                + ((customField4 == null) ? 0 : customField4.hashCode());
        result = prime * result
                + ((customField5 == null) ? 0 : customField5.hashCode());
        result = prime * result
                + ((customField6 == null) ? 0 : customField6.hashCode());
        result = prime
                * result
                + ((dailyDataThreshold == null) ? 0 : dailyDataThreshold
                        .hashCode());
        result = prime
                * result
                + ((dailySMSThreshold == null) ? 0 : dailySMSThreshold
                        .hashCode());
        result = prime * result
                + ((futureDataPlan == null) ? 0 : futureDataPlan.hashCode());
        result = prime * result
                + ((futureSMSPlan == null) ? 0 : futureSMSPlan.hashCode());
        result = prime * result + Arrays.hashCode(lstExtFeatures);
        result = prime * result + Arrays.hashCode(lstFeatures);
        result = prime * result + Arrays.hashCode(lstHistoryOverLastYear);
        result = prime
                * result
                + ((monthlyDataThreshold == null) ? 0 : monthlyDataThreshold
                        .hashCode());
        result = prime
                * result
                + ((monthlySMSThreshold == null) ? 0 : monthlySMSThreshold
                        .hashCode());
        result = prime
                * result
                + ((mostRecentAddress == null) ? 0 : mostRecentAddress
                        .hashCode());
        result = prime
                * result
                + ((mostRecentLatitude == null) ? 0 : mostRecentLatitude
                        .hashCode());
        result = prime
                * result
                + ((mostRecentLocateDate == null) ? 0 : mostRecentLocateDate
                        .hashCode());
        result = prime
                * result
                + ((mostRecentLocateId == null) ? 0 : mostRecentLocateId
                        .hashCode());
        result = prime
                * result
                + ((mostRecentLongitude == null) ? 0 : mostRecentLongitude
                        .hashCode());
        result = prime * result
                + ((previousAddress == null) ? 0 : previousAddress.hashCode());
        result = prime
                * result
                + ((previousLatitude == null) ? 0 : previousLatitude.hashCode());
        result = prime
                * result
                + ((previousLocateDate == null) ? 0 : previousLocateDate
                        .hashCode());
        result = prime
                * result
                + ((previousLocateId == null) ? 0 : previousLocateId.hashCode());
        result = prime
                * result
                + ((previousLongitude == null) ? 0 : previousLongitude
                        .hashCode());
        result = prime * result
                + ((requestStatus == null) ? 0 : requestStatus.hashCode());
        result = prime * result
                + ((staticIP == null) ? 0 : staticIP.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime
                * result
                + ((voiceDispatchNumber == null) ? 0 : voiceDispatchNumber
                        .hashCode());
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
        if (IMSIOrMIN == null) {
            if (other.IMSIOrMIN != null)
                return false;
        } else if (!IMSIOrMIN.equals(other.IMSIOrMIN))
            return false;
        if (MSISDNOrMDN == null) {
            if (other.MSISDNOrMDN != null)
                return false;
        } else if (!MSISDNOrMDN.equals(other.MSISDNOrMDN))
            return false;
        if (__type == null) {
            if (other.__type != null)
                return false;
        } else if (!__type.equals(other.__type))
            return false;
        if (currentDataPlan == null) {
            if (other.currentDataPlan != null)
                return false;
        } else if (!currentDataPlan.equals(other.currentDataPlan))
            return false;
        if (currentSMSPlan == null) {
            if (other.currentSMSPlan != null)
                return false;
        } else if (!currentSMSPlan.equals(other.currentSMSPlan))
            return false;
        if (customField1 == null) {
            if (other.customField1 != null)
                return false;
        } else if (!customField1.equals(other.customField1))
            return false;
        if (customField2 == null) {
            if (other.customField2 != null)
                return false;
        } else if (!customField2.equals(other.customField2))
            return false;
        if (customField3 == null) {
            if (other.customField3 != null)
                return false;
        } else if (!customField3.equals(other.customField3))
            return false;
        if (customField4 == null) {
            if (other.customField4 != null)
                return false;
        } else if (!customField4.equals(other.customField4))
            return false;
        if (customField5 == null) {
            if (other.customField5 != null)
                return false;
        } else if (!customField5.equals(other.customField5))
            return false;
        if (customField6 == null) {
            if (other.customField6 != null)
                return false;
        } else if (!customField6.equals(other.customField6))
            return false;
        if (dailyDataThreshold == null) {
            if (other.dailyDataThreshold != null)
                return false;
        } else if (!dailyDataThreshold.equals(other.dailyDataThreshold))
            return false;
        if (dailySMSThreshold == null) {
            if (other.dailySMSThreshold != null)
                return false;
        } else if (!dailySMSThreshold.equals(other.dailySMSThreshold))
            return false;
        if (futureDataPlan == null) {
            if (other.futureDataPlan != null)
                return false;
        } else if (!futureDataPlan.equals(other.futureDataPlan))
            return false;
        if (futureSMSPlan == null) {
            if (other.futureSMSPlan != null)
                return false;
        } else if (!futureSMSPlan.equals(other.futureSMSPlan))
            return false;
        if (!Arrays.equals(lstExtFeatures, other.lstExtFeatures))
            return false;
        if (!Arrays.equals(lstFeatures, other.lstFeatures))
            return false;
        if (!Arrays
                .equals(lstHistoryOverLastYear, other.lstHistoryOverLastYear))
            return false;
        if (monthlyDataThreshold == null) {
            if (other.monthlyDataThreshold != null)
                return false;
        } else if (!monthlyDataThreshold.equals(other.monthlyDataThreshold))
            return false;
        if (monthlySMSThreshold == null) {
            if (other.monthlySMSThreshold != null)
                return false;
        } else if (!monthlySMSThreshold.equals(other.monthlySMSThreshold))
            return false;
        if (mostRecentAddress == null) {
            if (other.mostRecentAddress != null)
                return false;
        } else if (!mostRecentAddress.equals(other.mostRecentAddress))
            return false;
        if (mostRecentLatitude == null) {
            if (other.mostRecentLatitude != null)
                return false;
        } else if (!mostRecentLatitude.equals(other.mostRecentLatitude))
            return false;
        if (mostRecentLocateDate == null) {
            if (other.mostRecentLocateDate != null)
                return false;
        } else if (!mostRecentLocateDate.equals(other.mostRecentLocateDate))
            return false;
        if (mostRecentLocateId == null) {
            if (other.mostRecentLocateId != null)
                return false;
        } else if (!mostRecentLocateId.equals(other.mostRecentLocateId))
            return false;
        if (mostRecentLongitude == null) {
            if (other.mostRecentLongitude != null)
                return false;
        } else if (!mostRecentLongitude.equals(other.mostRecentLongitude))
            return false;
        if (previousAddress == null) {
            if (other.previousAddress != null)
                return false;
        } else if (!previousAddress.equals(other.previousAddress))
            return false;
        if (previousLatitude == null) {
            if (other.previousLatitude != null)
                return false;
        } else if (!previousLatitude.equals(other.previousLatitude))
            return false;
        if (previousLocateDate == null) {
            if (other.previousLocateDate != null)
                return false;
        } else if (!previousLocateDate.equals(other.previousLocateDate))
            return false;
        if (previousLocateId == null) {
            if (other.previousLocateId != null)
                return false;
        } else if (!previousLocateId.equals(other.previousLocateId))
            return false;
        if (previousLongitude == null) {
            if (other.previousLongitude != null)
                return false;
        } else if (!previousLongitude.equals(other.previousLongitude))
            return false;
        if (requestStatus == null) {
            if (other.requestStatus != null)
                return false;
        } else if (!requestStatus.equals(other.requestStatus))
            return false;
        if (staticIP == null) {
            if (other.staticIP != null)
                return false;
        } else if (!staticIP.equals(other.staticIP))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (voiceDispatchNumber == null) {
            if (other.voiceDispatchNumber != null)
                return false;
        } else if (!voiceDispatchNumber.equals(other.voiceDispatchNumber))
            return false;
        return true;
    }

}