package com.gv.midway.router

import com.gv.midway.pojo.transaction.Transaction
import org.scalatest.FunSuite

class TestKoreCheckStatusServiceRouter extends FunSuite {

  test("valid transaction") {
    val answer = new KoreCheckStatusServiceRouter().checkStatsuOfPendigDevice(new Transaction)
    assert(answer === "seda:koreSedaCheckStatus")
  }
}