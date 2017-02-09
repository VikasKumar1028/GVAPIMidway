package com.gv.midway.environment

import org.scalatest.FunSuite

class TestVerizonCredentialsProperties extends FunSuite {

  test("asJson") {
    val user = "user"
    val pass = "pass"
    val creds = new VerizonCredentialsProperties(user, pass)
    assert(creds.asJson === s"""{"username":"$user","password":"$pass"}""")
  }
}