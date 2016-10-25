package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class AttCallBackRouter {

    private static final Logger LOGGER = Logger.getLogger(AttCallBackRouter.class);

    public String checkStatsuOfPendigDevice(Transaction transaction) {
        LOGGER.info("************ATT Router****************************" + transaction.toString());
        return "seda:attSedaCallBack";
    }
}