package com.gv.midway.router

import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import org.scalatest.FunSuite

class TestBulkOperationServiceRouter extends FunSuite {

  test("valid device information") {
    val answer = new BulkOperationServiceRouter().bulkOperationDeviceSyncInDB(new DeviceInformation)
    assert(answer === "seda:bulkOperationDeviceSyncInDB")
  }
}