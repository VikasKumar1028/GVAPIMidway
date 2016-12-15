package com.gv.midway.processor.activateDevice

import com.fasterxml.jackson.databind.ObjectMapper
import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.KeyValuePair
import com.gv.midway.pojo.activateDevice.request.{ActivateDeviceId, ActivateDeviceRequest, ActivateDeviceRequestDataArea, ActivateDevices}
import com.gv.midway.pojo.activateDevice.verizon.request.ActivateDeviceRequestVerizon
import com.gv.midway.pojo.verizon.Address
import org.apache.camel.Exchange
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestVerizonActivateDevicePreProcessor extends TestMocks {

  private val sessionToken = "sessionToken"
  private val authToken = "authToken"

  test("process") {
    withMockExchangeAndMessage { (exchange, message) =>

      val customField1 = new KeyValuePair("k1", "v1")
      val customField2 = new KeyValuePair("k2", "v2")

      val adid1 = new ActivateDeviceId("id1", "kind1")
      val adid2 = new ActivateDeviceId("id2", "kind2")
      val adid3 = new ActivateDeviceId("id3", "kind3")

      val address = new Address
      address.setCity("Kaysville")
      address.setAddressLine1("add1")
      address.setAddressLine2("add2")
      address.setCountry("country")
      address.setState("state")
      address.setZip("zip")
      val devices = new ActivateDevices
      devices.setCustomFields(Array(customField1, customField2))
      devices.setMacAddress("macAddress")
      devices.setSerialNumber("serialNumber")
      devices.setMiddleName("middleName")
      devices.setTitle("title")
      devices.setAddress(address)
      devices.setServicePlan("servicePlan")
      devices.setDeviceIds(Array(adid1, adid2, adid3))
      val dataArea = new ActivateDeviceRequestDataArea
      dataArea.setDevices(devices)
      dataArea.setAccountName("accountName")
      dataArea.setCarrierIpPoolName("carrierIpPoolName")
      dataArea.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      dataArea.setCostCenterCode("costCenterCode")
      dataArea.setGroupName("groupName")
      dataArea.setLeadId("leadId")
      dataArea.setMdnZipCode("mdnZipcode")
      dataArea.setPublicIpRestriction("publicIpRestriction")
      dataArea.setSkuNumber("skuNumber")
      val request = new ActivateDeviceRequest
      request.setDataArea(dataArea)

      when(exchange.getProperty(IConstant.VZ_SESSION_TOKEN)).thenReturn(sessionToken, Nil: _*)
      when(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)).thenReturn(authToken, Nil: _*)
      when(message.getBody).thenReturn(request, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[String])

      new VerizonActivateDevicePreProcessor().process(exchange)

      verify(message, times(1)).setHeader(Exchange.HTTP_PATH, "/devices/actions/activate")
      verify(message, times(1)).setBody(captor.capture())

      val json = captor.getValue

      val objectMapper: ObjectMapper = new ObjectMapper
      val verizonRequest = objectMapper.readValue(json, classOf[ActivateDeviceRequestVerizon])
      assert(verizonRequest.getGroupName === dataArea.getGroupName)
      assert(verizonRequest.getAccountName === dataArea.getAccountName)
      assert(verizonRequest.getSkuNumber === dataArea.getSkuNumber)
      assert(verizonRequest.getCostCenterCode === dataArea.getCostCenterCode)
      assert(verizonRequest.getCarrierIpPoolName === dataArea.getCarrierIpPoolName)
      assert(verizonRequest.getLeadId === dataArea.getLeadId)
      assert(verizonRequest.getCarrierName === dataArea.getCarrierName)
      assert(verizonRequest.getPublicIpRestriction === dataArea.getPublicIpRestriction)
      assert(verizonRequest.getMdnZipCode === dataArea.getMdnZipCode)
      assert(verizonRequest.getServicePlan === devices.getServicePlan)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getCity === address.getCity)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getAddressLine1 === address.getAddressLine1)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getAddressLine2 === address.getAddressLine2)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getCountry === address.getCountry)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getState === address.getState)
      assert(verizonRequest.getPrimaryPlaceOfUse.getAddress.getZip === address.getZip)
      assert(verizonRequest.getPrimaryPlaceOfUse.getCustomerName.getFirstName === devices.getSerialNumber)
      assert(verizonRequest.getPrimaryPlaceOfUse.getCustomerName.getLastName === devices.getMacAddress)
      assert(verizonRequest.getPrimaryPlaceOfUse.getCustomerName.getMiddleName === devices.getMiddleName)
      assert(verizonRequest.getPrimaryPlaceOfUse.getCustomerName.getTitle === devices.getTitle)
      assert(verizonRequest.getCustomFields.length === 2)
      assert(verizonRequest.getDevices.length === 1)
      assert(verizonRequest.getDevices.head.getDeviceIds.length === 3)

    }
  }
}