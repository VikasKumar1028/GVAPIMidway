package com.gv.midway.router

import com.gv.midway.constant.RequestType
import com.gv.midway.pojo.transaction.Transaction
import org.scalatest.FunSuite

class TestATTJasperDeviceServiceRouter extends FunSuite {

  List(
    (RequestType.ACTIVATION, "seda:attJasperSedaActivation")
    , (RequestType.DEACTIVATION, "seda:attJasperSedaDeactivation")
    , (RequestType.SUSPEND, "seda:attJasperSedaSuspend")
    , (RequestType.RESTORE, "seda:attJasperSedaRestore")
    , (RequestType.REACTIVATION, "seda:attJasperSedaReactivation")
    , (RequestType.CHANGECUSTOMFIELDS, "seda:attJasperSedaCustomeFields")
    , (RequestType.CHANGESERVICEPLAN, "seda:attJasperSedaChangeDeviceServicePlans")
  ).foreach { case (request, endpoint) =>
    test(s"$request -> $endpoint") {
      val trans = new Transaction
      trans.setRequestType(request)
      val answer = new ATTJasperDeviceServiceRouter().resolveDeviceServiceChannel(trans)

      assert(answer === endpoint)
    }
  }
}