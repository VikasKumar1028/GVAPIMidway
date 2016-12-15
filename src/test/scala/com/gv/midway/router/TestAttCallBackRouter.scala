package com.gv.midway.router

import com.gv.midway.pojo.transaction.Transaction
import org.scalatest.FunSuite

class TestAttCallBackRouter extends FunSuite {

  test("valid transaction") {

    val answer = new AttCallBackRouter().checkStatusOfPendingDevice(new Transaction)
    assert(answer === "seda:attSedaCallBack")
  }
}