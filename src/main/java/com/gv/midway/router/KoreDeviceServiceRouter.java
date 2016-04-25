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

import com.gv.midway.pojo.transaction.Transaction;

public class KoreDeviceServiceRouter {

	public String resolveOrderItemChannel(Transaction transaction) {
		System.out.println("************KORE ROUTER****************************" + transaction.getRequestType());

		if (transaction.getRequestType().contains("direct://activateDevice"))
			return "seda:koreSedaActivation";
		else if (transaction.getRequestType().contains("direct://deactivateDevice"))
			return "seda:koreSedaDeactivation";
		else if (transaction.getRequestType().contains("direct://suspendDevice"))
			return "seda:koreSedaSuspend";
		else if (transaction.getRequestType().contains("direct://reactivateDevice"))
			return "seda:koreSedaReactivation";
		 if (transaction.getRequestType().contains("direct://restoreDevice"))
			return "seda:koreSedaRestore";
		 else if (transaction.getRequestType().contains("direct://customeFields"))
				return "seda:koreSedacustomeFields";
		else
			return null;
	}
}