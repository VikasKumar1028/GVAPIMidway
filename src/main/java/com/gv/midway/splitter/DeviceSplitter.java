package com.gv.midway.splitter;

import java.util.List;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class DeviceSplitter {
	private Logger log = Logger.getLogger(DeviceSplitter.class);

	public List<Transaction> split(List deviceList) {

		log.info("*****************SPLITTER*********************");
		return deviceList;
	}

}