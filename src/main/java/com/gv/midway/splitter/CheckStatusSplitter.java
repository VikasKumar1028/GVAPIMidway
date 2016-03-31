package com.gv.midway.splitter;

import java.util.List;

import com.gv.midway.pojo.transaction.Transaction;

public class CheckStatusSplitter {

	 public List<Transaction> split(List deviceList) {
	    	
	    	System.out.println("*****************SPLITTER*********************"+deviceList.size());
	        return deviceList;
	    }
}
