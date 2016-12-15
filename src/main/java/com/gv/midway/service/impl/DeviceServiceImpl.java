package com.gv.midway.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gv.midway.exception.InvalidParameterException;
import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequest;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationRequestDataArea;
import com.gv.midway.pojo.session.SessionRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gv.midway.constant.IConstant;
import com.gv.midway.dao.IDeviceDao;
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse;
import com.gv.midway.pojo.connectionInformation.request.ConnectionInformationMidwayRequest;
import com.gv.midway.pojo.device.request.BulkDevices;
import com.gv.midway.pojo.device.request.SingleDevice;
import com.gv.midway.pojo.device.response.BatchDeviceId;
import com.gv.midway.pojo.device.response.UpdateDeviceResponse;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse;
import com.gv.midway.pojo.usageInformation.request.DevicesUsageByDayAndCarrierRequest;
import com.gv.midway.pojo.usageInformation.request.UsageInformationMidwayRequest;
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrierResponse;
import com.gv.midway.pojo.usageInformation.response.UsageInformationMidwayResponse;
import com.gv.midway.service.IDeviceService;

@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDao iDeviceDao;

    @SuppressWarnings("unused")  //Needed for framework
    public DeviceServiceImpl() {
    }

    //Added this constructor so that this class could be tested.
    public DeviceServiceImpl(IDeviceDao iDeviceDao) {
        this.iDeviceDao = iDeviceDao;
    }

    private static final Logger LOGGER = Logger.getLogger(DeviceServiceImpl.class.getName());

    @Override
    public UpdateDeviceResponse updateDeviceDetails(Exchange exchange) {
        final SingleDevice device = (SingleDevice) exchange.getIn().getBody();
        return iDeviceDao.updateDeviceDetails(device);
    }

    @Override
    public DeviceInformationResponse getDeviceInformationDB(Exchange exchange) {
        final DeviceInformationRequest deviceInformationRequest = (DeviceInformationRequest) exchange.getIn().getBody();

        LOGGER.debug("device information is.........." + deviceInformationRequest.toString());
        return iDeviceDao.getDeviceInformationDB(deviceInformationRequest);
    }

    @Override
    public void updateDevicesDetailsBulk(Exchange exchange) {
        final BulkDevices devices = (BulkDevices) exchange.getIn().getBody();
        final DeviceInformation[] deviceInformationArr = devices.getDataArea().getDevices();
        final List<DeviceInformation> deviceInformationList = Arrays.asList(deviceInformationArr);

        final List<BatchDeviceId> successList = new ArrayList<>();
        final List<BatchDeviceId> failureList = new ArrayList<>();

        LOGGER.debug("******************Before doing bulk insertion using seda***************");

        exchange.setProperty(IConstant.BULK_SUCCESS_LIST, successList);
        exchange.setProperty(IConstant.BULK_ERROR_LIST, failureList);
        exchange.getIn().setBody(deviceInformationList);

        LOGGER.debug("******************In the end of bulk insert***************");
    }

    @Override
    public void setDeviceInformationDB(Exchange exchange) {
        iDeviceDao.setDeviceInformationDB(exchange);
    }

    @Override
    public void updateDeviceInformationDB(Exchange exchange) {
        iDeviceDao.updateDeviceInformationDB(exchange);
    }

    @Override
    public void bulkOperationDeviceSyncInDB(Exchange exchange) {
        iDeviceDao.bulkOperationDeviceUpload(exchange);
    }

    @Override
    public ArrayList<DeviceInformation> getAllDevices() {
        return iDeviceDao.getAllDevices();
    }

    @Override
    public UsageInformationMidwayResponse getDeviceUsageInfoDB(Exchange exchange) {
        final UsageInformationMidwayRequest usageInformationMidwayRequest =
                (UsageInformationMidwayRequest) exchange.getIn().getBody();
        return iDeviceDao.getDeviceUsageInfoDB(usageInformationMidwayRequest);
    }

    @Override
    public ConnectionInformationMidwayResponse getDeviceConnectionHistoryInfoDB(Exchange exchange) {
        final ConnectionInformationMidwayRequest connectionInformationMidwayRequest =
                (ConnectionInformationMidwayRequest) exchange.getIn().getBody();
        return iDeviceDao.getDeviceConnectionHistoryInfoDB(connectionInformationMidwayRequest);
    }

    @Override
    public DevicesUsageByDayAndCarrierResponse getDevicesUsageByDayAndCarrierInfoDB(Exchange exchange) {
        final DevicesUsageByDayAndCarrierRequest devicesUsageByDayAndCarrierRequest =
                (DevicesUsageByDayAndCarrierRequest) exchange.getIn().getBody();

        return iDeviceDao.getDevicesUsageByDayAndCarrierInfoDB(devicesUsageByDayAndCarrierRequest);
    }

    @Override
    public ConnectionInformationRequest getDeviceSessionInfo(Exchange exchange) throws InvalidParameterException {
        final SessionRequest sessionRequest = exchange.getIn().getBody(SessionRequest.class);
        try {
            return iDeviceDao.getDeviceSessionInfo(sessionRequest);
        } catch (InvalidParameterException ex) {
            exchange.setProperty(IConstant.RESPONSE_CODE, "400");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, ex.getMessage());
            throw ex;
        }
    }

    @Override
    public UsageInformationRequest getDeviceSessionUsage(Exchange exchange) throws InvalidParameterException {
        final SessionRequest sessionRequest = exchange.getIn().getBody(SessionRequest.class);
        try {
            return iDeviceDao.getDeviceSessionUsage(sessionRequest);
        } catch (InvalidParameterException ex) {
            exchange.setProperty(IConstant.RESPONSE_CODE, "400");
            exchange.setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter");
            exchange.setProperty(IConstant.RESPONSE_DESCRIPTION, ex.getMessage());
            throw ex;
        }
    }

    @Override
    public ConnectionInformationRequest getDeviceSessionInfoMock(Exchange exchange) throws InvalidParameterException {
        Header header = new Header();

        ConnectionInformationRequestDataArea dataArea = new ConnectionInformationRequestDataArea();

        ConnectionInformationRequest request = new ConnectionInformationRequest();
        request.setHeader(header);
        request.setDataArea(dataArea);



        return request;
    }

    @Override
    public UsageInformationRequest getDeviceSessionUsageMock(Exchange exchange) throws InvalidParameterException {
        //Mocking the mongodb call process
        SessionRequest sessionRequest = exchange.getIn().getBody(SessionRequest.class);

        UsageInformationRequestDataArea usageInformationRequestDataArea = new UsageInformationRequestDataArea();
        DeviceId deviceId = new DeviceId();
        deviceId.setId("A10000438DBBE7");
        deviceId.setKind("meid");
        usageInformationRequestDataArea.setDeviceId(deviceId);
        UsageInformationRequest usageInformationRequest = new UsageInformationRequest();
        usageInformationRequest.setHeader(sessionRequest.getHeader());
        usageInformationRequest.setDataArea(usageInformationRequestDataArea);
        exchange.getIn().setBody(usageInformationRequest);

        return usageInformationRequest;
    }
}