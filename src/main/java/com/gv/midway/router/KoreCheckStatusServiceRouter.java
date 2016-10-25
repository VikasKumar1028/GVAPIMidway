package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusServiceRouter {

    private static final Logger LOGGER = Logger.getLogger(KoreCheckStatusServiceRouter.class);

    public String checkStatsuOfPendigDevice(Transaction transaction) {
        LOGGER.info("************KORE ROUTER****************************" + transaction.toString());
        return "seda:koreSedaCheckStatus";
    }
}