package com.gv.midway.service;

import java.util.ArrayList;

import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import org.apache.camel.Exchange;

import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;

public interface IDeviceService {

    public UpdateDeviceResponse updateDeviceDetails(Exchange exchange);

    public DeviceInformationResponse getDeviceInformationDB(Exchange exchange);

    public void updateDevicesDetailsBulk(Exchange exchange);

    public void setDeviceInformationDB(Exchange exchange);

    public void updateDeviceInformationDB(Exchange exchange);

    public void bulkOperationDeviceSyncInDB(Exchange exchange);

    public ArrayList<DeviceInformation> getAllDevices();

    public UsageInformationMidwayResponse getDeviceUsageInfoDB(Exchange exchange);

    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(
            Exchange exchange);
    
    public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(
            Exchange exchange);

    public ConnectionInformationRequest getDeviceSessionInfo(Exchange exchange) throws InvalidParameterException;

    public UsageInformationRequest getDeviceSessionUsage(Exchange exchange) throws InvalidParameterException;
}
