package com.gv.midway.router;

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;
import org.apache.log4j.Logger;

public class ATTJasperDeviceServiceRouter {

    private static final Logger LOGGER = Logger.getLogger(ATTJasperDeviceServiceRouter.class);

    public String resolveDeviceServiceChannel(Transaction transaction) {

        final RequestType requestType = transaction.getRequestType();

        LOGGER.info("************ATTJASPER ROUTER****************************" + transaction.getRequestType());

        switch (requestType) {
            case ACTIVATION:
                return "seda:attJasperSedaActivation";
            case DEACTIVATION:
                return "seda:attJasperSedaDeactivation";
            case SUSPEND:
                return "seda:attJasperSedaSuspend";
            case RESTORE:
                return "seda:attJasperSedaRestore";
            case REACTIVATION:
                return "seda:attJasperSedaReactivation";
            case CHANGECUSTOMFIELDS:
                return "seda:attJasperSedaCustomeFields";
            case CHANGESERVICEPLAN:
                return "seda:attJasperSedaChangeDeviceServicePlans";
            default:
                return null;
        }
    }
}