package com.gv.midway.router

import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import org.scalatest.FunSuite

class TestGetDeviceConnectionRouter extends FunSuite {

  List(
    (IConstant.BSCARRIER_SERVICE_VERIZON, "seda:getDeviceConnectionInformation")
    , (IConstant.BSCARRIER_SERVICE_KORE, null)
    , (IConstant.BSCARRIER_SERVICE_ATTJASPER, null)
    , ("TMobile", null)
  ).foreach { case (carrierName, endpoint) =>

      test(s"$carrierName -> $endpoint") {
        val deviceInfo = new DeviceInformation
        deviceInfo.setBs_carrier(carrierName)
        val answer = new GetDeviceConnectionRouter().getDeviceConnectionHistory(deviceInfo)

        assert(answer === endpoint)
      }
  }
}