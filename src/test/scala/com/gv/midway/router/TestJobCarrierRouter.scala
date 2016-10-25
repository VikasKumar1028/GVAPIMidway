package com.gv.midway.router

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import org.scalatest.FunSuite

class TestJobCarrierRouter extends FunSuite {

  List(
    (IConstant.BSCARRIER_SERVICE_VERIZON, "seda:processVerizonJob")
    , (IConstant.BSCARRIER_SERVICE_KORE, "seda:processKoreJob")
    , (IConstant.BSCARRIER_SERVICE_ATTJASPER, "")
  ).foreach { case (carrierName, endpoint) =>
    test(s"$carrierName -> $endpoint") {
      val deviceInfo = new DeviceInformation
      deviceInfo.setBs_carrier(carrierName)
      val answer = new JobCarrierRouter().routeCarrierJob(deviceInfo)

      assert(answer === endpoint)
    }
  }
}