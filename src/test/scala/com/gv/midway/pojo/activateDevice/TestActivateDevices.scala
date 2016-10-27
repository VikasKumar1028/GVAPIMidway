package com.gv.midway.pojo.activateDevice

import com.gv.midway.pojo.activateDevice.request.ActivateDevices
import com.gv.midway.pojo.verizon.{Address, PrimaryPlaceOfUse}
import org.scalatest.FunSuite

class TestActivateDevices extends FunSuite {

  test("all null") {
    assert(apply().generatePrimaryPlaceOfUse() === null)
  }

  test("just serial #") {
    val serial = "Serial"
    val primary = apply(serial = serial).generatePrimaryPlaceOfUse()
    assert(primary != null)
    assert(primary.getCustomerName != null)
    assert(primary.getCustomerName.getFirstName === serial)
    assert(primary.getCustomerName.getLastName === null)
    assert(primary.getCustomerName.getMiddleName === null)
    assert(primary.getCustomerName.getTitle === null)
    assert(primary.getAddress == null)
  }

  test("just middle name") {
    val middleName = "middleName"
    val primary = apply(middleName = middleName).generatePrimaryPlaceOfUse()
    assert(primary != null)
    assert(primary.getCustomerName != null)
    assert(primary.getCustomerName.getMiddleName === middleName)
    assert(primary.getCustomerName.getFirstName === null)
    assert(primary.getCustomerName.getLastName === null)
    assert(primary.getCustomerName.getTitle === null)
    assert(primary.getAddress == null)
  }

  test("just title") {
    val title = "middleName"
    val primary = apply(title = title).generatePrimaryPlaceOfUse()
    assert(primary != null)
    assert(primary.getCustomerName != null)
    assert(primary.getCustomerName.getTitle === title)
    assert(primary.getCustomerName.getFirstName === null)
    assert(primary.getCustomerName.getLastName === null)
    assert(primary.getCustomerName.getMiddleName === null)
    assert(primary.getAddress == null)
  }

  test("just mac") {
    val mac = "mac"
    val primary = apply(mac = mac).generatePrimaryPlaceOfUse()
    assert(primary != null)
    assert(primary.getCustomerName != null)
    assert(primary.getCustomerName.getLastName === mac)
    assert(primary.getCustomerName.getFirstName === null)
    assert(primary.getCustomerName.getMiddleName === null)
    assert(primary.getCustomerName.getTitle === null)
    assert(primary.getAddress == null)
  }

  test("just address") {
    val address = new Address()
    val primary = apply(address = address).generatePrimaryPlaceOfUse()
    assert(primary != null)
    assert(primary.getCustomerName == null)
    assert(primary.getAddress != null)
  }

  private def apply(serial: String = null, title: String = null, middleName: String = null, mac: String = null, address: Address = null): ActivateDevices = {
    val ad = new ActivateDevices()
    ad.setSerialNumber(serial)
    ad.setTitle(title)
    ad.setMiddleName(middleName)
    ad.setMacAddress(mac)
    ad.setAddress(address)
    ad
  }
}