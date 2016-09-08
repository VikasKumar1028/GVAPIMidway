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

import com.gv.midway.constant.RequestType;
import com.gv.midway.pojo.transaction.Transaction;
import org.apache.log4j.Logger;

public class ATTJasperDeviceServiceRouter {

   private static final Logger LOGGER = Logger.getLogger(ATTJasperDeviceServiceRouter.class);

    public String resolveDeviceServiceChannel(Transaction transaction) {

        RequestType requestType = transaction.getRequestType();

        LOGGER.info("************ATTJASPER ROUTER****************************"
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

            return "seda:attJasperSedaCustomeFields";

        case CHANGESERVICEPLAN:

            return "seda:attJasperSedaChangeDeviceServicePlans";

        default:
            return null;
        }

    }
}
