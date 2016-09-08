package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;

public class ATTJasperDeviceServiceRouter {
	  private static final Logger LOGGER = Logger.getLogger(ATTJasperDeviceServiceRouter.class);

	    public String resolveOrderItemChannel(Transaction transaction) {

	        RequestType requestType = transaction.getRequestType();

	        LOGGER.info("************ATT JASPER ROUTER****************************"
	                + transaction.getRequestType());

	        switch (requestType) {
	        case ACTIVATION:

	            return "seda:attJasperSedaActivation";

	        case DEACTIVATION:

	            return "seda:attJasperSedaDeactivation";

	        case SUSPEND:

	            return "seda:attJasperSedaSuspend";

	        case RESTORE:

	            return "seda:attJasperSedaRestore";

	        case REACTIVATION:

	            return "seda:attJasperSedaReactivation";

	        case CHANGECUSTOMFIELDS:

	            return "seda:attJasperSedacustomeFields";

	        case CHANGESERVICEPLAN:

	            return "seda:attJasperSedachangeDeviceServicePlans";

	        default:
	            return null;
	        }

	     

	    }
}
