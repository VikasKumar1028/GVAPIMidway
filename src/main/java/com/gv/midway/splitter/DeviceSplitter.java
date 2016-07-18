package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class DeviceSplitter {
   private static final Logger LOGGER = Logger.getLogger(DeviceSplitter.class);

    public List<Transaction> split(List deviceList) {

        LOGGER.info("*****************SPLITTER*********************");
        return deviceList;
    }

}