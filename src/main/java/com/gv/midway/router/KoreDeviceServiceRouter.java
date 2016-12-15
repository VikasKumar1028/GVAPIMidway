package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreDeviceServiceRouter {

    private static final Logger LOGGER = Logger.getLogger(KoreDeviceServiceRouter.class);

    public String resolveOrderItemChannel(Transaction transaction) {

        final RequestType requestType = transaction.getRequestType();

        LOGGER.debug("************KORE ROUTER****************************" + transaction.getRequestType());

        switch (requestType) {
            case ACTIVATION:
                return "seda:koreSedaActivation";
            case DEACTIVATION:
                return "seda:koreSedaDeactivation";
            case SUSPEND:
                return "seda:koreSedaSuspend";
            case RESTORE:
                return "seda:koreSedaRestore";
            case REACTIVATION:
                return "seda:koreSedaReactivation";
            case CHANGECUSTOMFIELDS:
                return "seda:koreSedaCustomFields";
            case CHANGESERVICEPLAN:
                return "seda:koreSedaChangeDeviceServicePlans";
            default:
                return null;
        }
    }
}