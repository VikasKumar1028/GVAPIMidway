package com.gv.midway.router;

import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusServiceRouter {

	public String checkStatsuOfPendigDevice(Transaction transaction) {
		System.out.println("************KORE ROUTER****************************" + transaction.toString());

		return "seda:koreSedaCheckStatus";
		
	}
}
