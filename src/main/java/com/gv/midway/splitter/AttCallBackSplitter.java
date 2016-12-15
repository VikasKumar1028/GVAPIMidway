package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class AttCallBackSplitter {

   private static final Logger LOGGER = Logger.getLogger(AttCallBackSplitter.class);

    public List<Transaction> split(List<Transaction> deviceList) {

        LOGGER.debug("*****************SPLITTER*********************" + deviceList.size());
        return deviceList;
    }
}
