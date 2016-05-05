package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.pojo.transaction.Transaction;

public class KoreCheckStatusServiceRouter {

	private Logger log = Logger.getLogger(KoreCheckStatusServiceRouter.class);

	public String checkStatsuOfPendigDevice(Transaction transaction) {
		log.info("************KORE ROUTER****************************"
				+ transaction.toString());

		return "seda:koreSedaCheckStatus";

	}
}
