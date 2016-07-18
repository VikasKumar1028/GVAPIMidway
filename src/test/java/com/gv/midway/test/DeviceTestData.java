package com.gv.midway.test;

import org.apache.camel.Exchange;

import com.gv.midway.pojo.Header;
import com.gv.midway.pojo.MidWayDeviceId;
import com.gv.midway.pojo.MidWayDevices;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceId;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest;
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequestDataArea;
import com.gv.midway.pojo.activateDevice.request.ActivateDevices;
import com.gv.midway.pojo.activateDevice.response.ActivateDeviceResponse;
import com.gv.midway.pojo.callback.request.CallBackVerizonRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceId;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequestDataArea;
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDevices;
import com.gv.midway.pojo.device.request.DeviceDataArea;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest;
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequestDataArea;
import com.gv.midway.pojo.deviceInformation.response.Cell;
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest;
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequestDataArea;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest;
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequestDataArea;
import com.gv.midway.pojo.verizon.DeviceId;
import com.gv.midway.pojo.verizon.Devices;

public class DeviceTestData extends Header {
    // for header Testing
    protected Header expectedHeaderBsCarrier;
    protected Header expectedHeaderParameters;
    protected Header expectedHeaderSource;

    protected ActivateDeviceRequest expectedActivateDeviceRequest;
    protected DeviceInformationRequest expectDeviceInfo;
    protected DeactivateDeviceRequest expectDeactiveDevice;
    protected ActivateDeviceResponse expectedActivateDeviceResponse;
    protected CallBackVerizonRequest expectedActivateCallback;
    protected RestoreDeviceRequest expectRestoreDevice;
    protected SuspendDeviceRequest expectSuspendDevice;
    Exchange exchange;
    Header header = new Header();

    public void setExpectedHeaderBsCarrier(Header header) {
        this.expectedHeaderBsCarrier = header;
    }

    public void setExpectedHeaderParameters(Header header) {
        this.expectedHeaderParameters = header;
    }

    public void setExpectedHeaderSource(Header header) {
        this.expectedHeaderSource = header;
    }

    // check for header Parameters-it can not be blank
    public void verifyHeaderParameters() {

        // null pointer check
        System.out.println("Header parameter Values are:"
                + expectedHeaderParameters);

        // check for Reason
        if (expectedHeaderParameters.getRegion() == null
                || expectedHeaderParameters.getRegion().equals("")) {
            throw new AssertionError("Region can not be Blank.");

        }

        // check for Application name
        if (expectedHeaderParameters.getApplicationName() == null
                || expectedHeaderParameters.getApplicationName().equals("")) {
            throw new AssertionError("Application Name can not be Blank.");

        }

        // check for Organization
        if (expectedHeaderParameters.getOrganization() == null
                || expectedHeaderParameters.getOrganization().equals("")) {
            throw new AssertionError("Organisation Name can not be Blank.");

        }

        /*
         * //check for Time stamp
         * if(expectedHeaderParameters.getTimestamp()==null
         * ||expectedHeaderParameters.getTimestamp().equals("")) { throw new
         * AssertionError("Time Stamp is not defind");
         * 
         * }
         */

        // check for Transaction Id
        if (expectedHeaderParameters.getTransactionId() == null
                || expectedHeaderParameters.getTransactionId().equals("")) {
            throw new AssertionError("Transaction Id  can not be Blank.");

        }
    }

    public void verifyHeaderSource() {

        // check for Header Source Name-it can not be blank
        if (expectedHeaderSource.getSourceName() == null
                || expectedHeaderSource.getSourceName().equals("")) {
            throw new AssertionError("Source Name can not be Blank.");
        }
        // check source name for Verizon and Kore
        if (!expectedHeaderSource.getSourceName().equals("NetSuit")) {
            throw new AssertionError("Invalid Source Name");
        }
    }

    public void verifyHeaderBsCarrier() {

        // check for Header Carrier-it can not be blank
        if (expectedHeaderBsCarrier.getBsCarrier() == null
                || expectedHeaderBsCarrier.getBsCarrier().equals("")) {
            throw new AssertionError("Bs Carrier can not be Blank.");
        }
        // check Bs Carrier for Verizon and Kore
        if (!expectedHeaderBsCarrier.getBsCarrier().equals("VERIZON")
                && !expectedHeaderBsCarrier.getBsCarrier().equals("KORE")) {
            throw new AssertionError("Invalid Bs Carrier.");
        }
    }

    // set expected dummy data to Device Information request
    public void setExpectedDeviceInformation(
            DeviceInformationRequest deviceInformationRequest) {
        // TODO Auto-generated method stub
        this.expectDeviceInfo = deviceInformationRequest;
    }

    // test case for device Information
    public void verifyDeviceInformationData() {
        // TODO Auto-generated method stub
        DeviceInformationRequest req = new DeviceInformationRequest();
        DeviceInformationRequestDataArea dataArea = new DeviceInformationRequestDataArea();

        DeviceInformation deviceInformation = new DeviceInformation();
        deviceInformation.setAccountName("Test");

        DeviceDataArea deviceDataArea = new DeviceDataArea();
        deviceDataArea.setDevices(deviceInformation);

        DeviceId deviceId = new DeviceId();
        deviceId.setId("01");
        deviceId.setKind("k01");

        dataArea.setDeviceId(deviceId);

        req.setDataArea(dataArea);

        header.setSourceName("KORE");
        req.setHeader(header);

        System.out.println("Delivered Request  Name is.....----------------"
                + req.getDataArea().getDeviceId().getId());

        // null pointer check for device Id
        if (req.getDataArea().getDeviceId().getId() == null
                || req.getDataArea().getDeviceId().getId().equals("")
                || req.getDataArea().getDeviceId().getId().isEmpty()) {
            throw new AssertionError("Device Id can not be Blank");
        }
        if (!expectDeviceInfo.getDataArea().getDeviceId().getId()
                .equals(req.getDataArea().getDeviceId().getId())) {
            throw new AssertionError("Invalid Device Id");
        }

    }

    // set expected dummy data to Activate Device request
    public void setExpectedActivateDevice(
            ActivateDeviceRequest activateDeviceRequest) {
        // TODO Auto-generated method stub
        this.expectedActivateDeviceRequest = activateDeviceRequest;
    }

    // test case for Activate device with null EAP Code
    public void verifyDeviceActivationData() {
        // TODO Auto-generated method stub
        ActivateDeviceRequest req = new ActivateDeviceRequest();
        ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

        ActivateDevices[] decs = new ActivateDevices[1];
        ActivateDevices adevices = new ActivateDevices();

        ActivateDeviceId[] ActivateDeviceIdArray = new ActivateDeviceId[1];

        ActivateDeviceId deviceId = new ActivateDeviceId();

        deviceId.setId("89014103277405946190");
        deviceId.setKind("ghgjg");
        /* deviceId.seteAPCode("eAPCode"); */

        ActivateDeviceIdArray[0] = deviceId;

        adevices.setDeviceIds(ActivateDeviceIdArray);
        decs[0] = adevices;
        // dataArea.setDevices(decs);
        dataArea.setDevices(adevices);
        req.setDataArea(dataArea);

        header.setSourceName("KORE");
        req.setHeader(header);

        System.out.println("Delivered Source Name----------------"
                + req.getHeader().getSourceName());

        // null pointer check for EAP Code if Source is 'KORE'
        /*
         * if(req.getHeader().getSourceName()!=null &&
         * req.getHeader().getSourceName().equalsIgnoreCase("KORE") &&
         * req.getDataArea
         * ().getDevices()[0].getDeviceIds()[0].geteAPCode().isEmpty()){ throw
         * new AssertionError("EAP Code is Mandatory for KORE"); }
         */

        if (!expectedActivateDeviceRequest.getHeader().getSourceName()
                .equals(req.getHeader().getSourceName())) {
            throw new AssertionError("Source Name is not correct");
        }

    }

    // set expected dummy data to Deactivate Device request
    public void setExpectedDeactivateDevice(
            DeactivateDeviceRequest deactivateDeviceRequest) {
        // TODO Auto-generated method stub
        this.expectDeactiveDevice = deactivateDeviceRequest;
    }

    // test case for Deactivate device with null device Id
    public void verifyDeviceDeactivationData() {
        // TODO Auto-generated method stub

        DeactivateDeviceRequest req = new DeactivateDeviceRequest();
        DeactivateDeviceRequestDataArea dataArea = new DeactivateDeviceRequestDataArea();

        DeactivateDevices[] deDevices = new DeactivateDevices[1];
        DeactivateDevices ddevices = new DeactivateDevices();

        DeactivateDeviceId[] DeActivateDeviceIdArray = new DeactivateDeviceId[1];

        DeactivateDeviceId deactivateDeviceId = new DeactivateDeviceId();

        deactivateDeviceId.setId("89014103277405946190");
        deactivateDeviceId.setKind("ghgjg");

        DeActivateDeviceIdArray[0] = deactivateDeviceId;

        ddevices.setDeviceIds(DeActivateDeviceIdArray);
        deDevices[0] = ddevices;
        dataArea.setDevices(deDevices);
        // dataArea.setFlagScrap(Boolean.FALSE);
        req.setDataArea(dataArea);

        header.setSourceName("KORE");
        req.setHeader(header);

        System.out.println("Delivered Source Name----------------"
                + req.getHeader().getSourceName());

        // null pointer check for Flag Scrap
        // if(req.getDataArea().getFlagScrap()==null){
        // throw new AssertionError("Flag Scrap is Mandatory");
        // }

        // null pointer check for device Id
        if (req.getDataArea().getDevices()[0].getDeviceIds()[0].getId() == null
                || req.getDataArea().getDevices()[0].getDeviceIds()[0].getId()
                        .isEmpty()) {
            throw new AssertionError("Device Id is Mandatory");
        }

        if (!expectDeactiveDevice.getDataArea().getDevices()[0].getDeviceIds()[0]
                .getId().equals(
                        req.getDataArea().getDevices()[0].getDeviceIds()[0]
                                .getId())) {
            throw new AssertionError("Device Id is Different");
        }

    }

    public void setExpectedActivateDeviceResponse(
            ActivateDeviceResponse activateDeviceResponse) {
        // TODO Auto-generated method stub
        this.expectedActivateDeviceResponse = activateDeviceResponse;
    }

    // test case for Activate Device Response
    public void verifyDeviceActivationResponseData(
            ActivateDeviceResponse response) {
        // TODO Auto-generated method stub
        ActivateDeviceRequest req = new ActivateDeviceRequest();
        ActivateDeviceRequestDataArea dataArea = new ActivateDeviceRequestDataArea();

        System.out.println("Delivered Order Number----------------"
                + response.getDataArea().getOrderNumber());

        // null pointer check for EAP Code if Source is 'KORE'
        if (!expectedActivateDeviceResponse.getDataArea().getOrderNumber()
                .equals(response.getDataArea().getOrderNumber())) {
            throw new AssertionError("Requested Order Number is not correct");
        }

    }

    public void setExpectedActivateCallback(CallBackVerizonRequest callbackReq) {
        // TODO Auto-generated method stub
        this.expectedActivateCallback = callbackReq;
    }

    // for activate callback request
    public void verifyActivateCallback() {
        // TODO Auto-generated method stub
        CallBackVerizonRequest callbackReq = new CallBackVerizonRequest();

        callbackReq.setRequestId("");
        System.out.println("Callback Request Id:" + callbackReq);
        if (callbackReq.getRequestId() == null
                || callbackReq.getRequestId().isEmpty()) {
            throw new AssertionError("Request Id can not be null");
        }
    }

    public void setExpectedCellInformation(Cell cellInfo) {
        // TODO Auto-generated method stub
        // this.expectedCellInfo=cellInfo;
    }

    // test case for cell information
    public void verifyCellInformation() {
        // TODO Auto-generated method stub
        Cell cell = new Cell();
        cell.setEsn("ESN01");
        cell.setMdn("MDN01");
        cell.setSim("SIM01");
        System.out.println("Request in Junit Test:" + cell);
        if (cell.getEsn() == null || cell.getEsn().isEmpty()) {
            throw new AssertionError("Cell ESN No. can not be null");
        }

        if (cell.getMdn() == null || cell.getMdn().isEmpty()) {
            throw new AssertionError("Cell MDN No. can not be null");
        }

        if (cell.getSim() == null || cell.getSim().isEmpty()) {
            throw new AssertionError("Cell SIM No. can not be null");
        }
    }

    public void setExpectedCellUpdateInformation(Cell cell) {
        // TODO Auto-generated method stub
        // this.expectedCellUpdateInfo=cell;
    }

    // Test case for cell Update
    public void verifyCellUpdateInformation() {
        // TODO Auto-generated method stub
        Cell cell = new Cell();
        cell.setEsn("ESN01");
        cell.setMdn("MDN01");
        cell.setSim("SIM01");
        System.out.println("Request in Junit Test:" + cell);
        if (cell.getEsn() == null || cell.getEsn().isEmpty()) {
            throw new AssertionError("Cell ESN No. can not be null");
        }

        if (cell.getMdn() == null || cell.getMdn().isEmpty()) {
            throw new AssertionError("Cell MDN No. can not be null");
        }

        if (cell.getSim() == null || cell.getSim().isEmpty()) {
            throw new AssertionError("Cell SIM No. can not be null");
        }
    }

    public void setExpectedRestoreDevice(RestoreDeviceRequest req) {
        // TODO Auto-generated method stub
        this.expectRestoreDevice = req;
    }

    public void verifyDeviceRestoreData() {
        // TODO Auto-generated method stub
        RestoreDeviceRequest req = new RestoreDeviceRequest();
        RestoreDeviceRequestDataArea dataArea = new RestoreDeviceRequestDataArea();

        MidWayDevices[] deDevices = new MidWayDevices[1];
        MidWayDevices ddevices = new MidWayDevices();

        MidWayDeviceId[] DeActivateDeviceIdArray = new MidWayDeviceId[1];

        MidWayDeviceId restoreDeviceId = new MidWayDeviceId();

        restoreDeviceId.setId("89014103277405946190");
        restoreDeviceId.setKind("ghgjg");

        DeActivateDeviceIdArray[0] = restoreDeviceId;

        ddevices.setDeviceIds(DeActivateDeviceIdArray);
        deDevices[0] = ddevices;
        dataArea.setDevices(deDevices);
        req.setDataArea(dataArea);

        header.setSourceName("KORE");
        req.setHeader(header);

        System.out.println("Delivered Source Name----------------"
                + req.getHeader().getSourceName());

        // null pointer check for device Id
        if (req.getDataArea().getDevices()[0].getDeviceIds()[0].getId() == null
                || req.getDataArea().getDevices()[0].getDeviceIds()[0].getId()
                        .isEmpty()) {
            throw new AssertionError("Device Id is Mandatory");
        }

        if (!expectRestoreDevice.getDataArea().getDevices()[0].getDeviceIds()[0]
                .getId().equals(
                        req.getDataArea().getDevices()[0].getDeviceIds()[0]
                                .getId())) {
            throw new AssertionError("Device Id is Different");
        }

    }

    public void setExpectedSuspendDevice(SuspendDeviceRequest req) {
        this.expectSuspendDevice = req;
    }

    public void verifyDeviceSuspendData() {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        SuspendDeviceRequest req = new SuspendDeviceRequest();
        SuspendDeviceRequestDataArea dataArea = new SuspendDeviceRequestDataArea();

        MidWayDevices[] deDevices = new MidWayDevices[1];
        MidWayDevices ddevices = new MidWayDevices();

        MidWayDeviceId[] suspendDeviceIdArray = new MidWayDeviceId[1];

        MidWayDeviceId suspendDeviceId = new MidWayDeviceId();

        suspendDeviceId.setId("89014103277405946190");
        suspendDeviceId.setKind("ghgjg");

        suspendDeviceIdArray[0] = suspendDeviceId;

        ddevices.setDeviceIds(suspendDeviceIdArray);
        deDevices[0] = ddevices;
        dataArea.setDevices(deDevices);
        req.setDataArea(dataArea);

        header.setSourceName("KORE");
        req.setHeader(header);

        System.out.println("Delivered Source Name----------------"
                + req.getHeader().getSourceName());

        // null pointer check for device Id
        if (req.getDataArea().getDevices()[0].getDeviceIds()[0].getId() == null
                || req.getDataArea().getDevices()[0].getDeviceIds()[0].getId()
                        .isEmpty()) {
            throw new AssertionError("Device Id is Mandatory");
        }

        if (!expectSuspendDevice.getDataArea().getDevices()[0].getDeviceIds()[0]
                .getId().equals(
                        req.getDataArea().getDevices()[0].getDeviceIds()[0]
                                .getId())) {
            throw new AssertionError("Device Id is Different");
        }
    }

}