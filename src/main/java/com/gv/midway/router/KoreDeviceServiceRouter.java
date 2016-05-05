/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gv.midway.router;

import org.apache.log4j.Logger;

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;

public class KoreDeviceServiceRouter {

	private Logger log = Logger.getLogger(KoreDeviceServiceRouter.class);

	public String resolveOrderItemChannel(Transaction transaction) {

		RequestType requestType = transaction.getRequestType();

		log.info("************KORE ROUTER****************************"
				+ transaction.getRequestType());

		switch (requestType) {
		case ACTIVATION:

			return "seda:koreSedaActivation";

		case DEACTIVATION:

			return "seda:koreSedaDeactivation";

		case SUSPEND:

			return "seda:koreSedaSuspend";

		case RESTORE:

			return "seda:koreSedaRestore";

		case REACTIVATION:

			return "seda:koreSedaReactivation";

		case CHANGECUSTOMFIELDS:

			return "seda:koreSedacustomeFields";

		case CHNAGESERVICEPLAN:

			return "seda:koreSedachangeDeviceServicePlans";

		default:
			return null;
		}

		/*
		 * if (transaction.getRequestType().contains("direct://activateDevice"))
		 * return "seda:koreSedaActivation"; else if
		 * (transaction.getRequestType().contains("direct://deactivateDevice"))
		 * return "seda:koreSedaDeactivation"; else if
		 * (transaction.getRequestType().contains("direct://suspendDevice"))
		 * return "seda:koreSedaSuspend"; else if
		 * (transaction.getRequestType().contains("direct://reactivateDevice"))
		 * return "seda:koreSedaReactivation"; if
		 * (transaction.getRequestType().contains("direct://restoreDevice"))
		 * return "seda:koreSedaRestore"; else if
		 * (transaction.getRequestType().contains("direct://customeFields"))
		 * return "seda:koreSedacustomeFields"; else if
		 * (transaction.getRequestType
		 * ().contains("direct://changeDeviceServicePlans")) return
		 * "seda:koreSedachangeDeviceServicePlans"; else return null;
		 */

	}
}
