package com.gv.midway.router

import com.gv.midway.constant.RequestType
import com.gv.midway.pojo.transaction.Transaction
import org.scalatest.FunSuite

class TestKoreDeviceServiceRouter extends FunSuite {

  List(
    (RequestType.ACTIVATION, "seda:koreSedaActivation")
    , (RequestType.DEACTIVATION, "seda:koreSedaDeactivation")
    , (RequestType.SUSPEND, "seda:koreSedaSuspend")
    , (RequestType.RESTORE, "seda:koreSedaRestore")
    , (RequestType.REACTIVATION, "seda:koreSedaReactivation")
    , (RequestType.CHANGECUSTOMFIELDS, "seda:koreSedacustomeFields")
    , (RequestType.CHANGESERVICEPLAN, "seda:koreSedachangeDeviceServicePlans")
  ).foreach { case (request, endpoint) =>
    test(s"$request -> $endpoint") {
      val trans = new Transaction
      trans.setRequestType(request)
      val answer = new KoreDeviceServiceRouter().resolveOrderItemChannel(trans)
      assert(answer === endpoint)
    }
  }
}