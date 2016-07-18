package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class CheckStatusSplitter {

   private static final Logger LOGGER = Logger.getLogger(CheckStatusSplitter.class);

    public List<Transaction> split(List<Transaction> deviceList) {

        LOGGER.info("*****************SPLITTER*********************"
                + deviceList.size());
        return deviceList;
    }
}
