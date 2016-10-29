package com.gv.midway.dao;

import java.util.ArrayList;

import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import org.apache.camel.Exchange;

import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;

public interface IDeviceDao {

    public UpdateDeviceResponse updateDeviceDetails(SingleDevice device);

    public DeviceInformationResponse getDeviceInformationDB(
            DeviceInformationRequest deviceInformationRequest);

    public void setDeviceInformationDB(Exchange e);

    public void updateDeviceInformationDB(Exchange e);

    public void bulkOperationDeviceUpload(Exchange exchange);

    public ArrayList<DeviceInformation> getAllDevices();

    public UsageInformationMidwayResponse getDeviceUsageInfoDB(
            UsageInformationMidwayRequest usageInformationMidwayRequest);

    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            ConnectionInformationMidwayRequest connectionInformationMidwayRequest);
    
    public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
    		DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest);

    UsageInformationRequest getDeviceSessionUsage(SessionRequest sessionRequest) throws InvalidParameterException;

    ConnectionInformationRequest getDeviceSessionInfo(SessionRequest sessionRequest) throws InvalidParameterException;
}
