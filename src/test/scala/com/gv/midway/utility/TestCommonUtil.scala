package com.gv.midway.utility

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.exception.InvalidParameterException
import com.gv.midway.pojo.job.{JobParameter, JobinitializedResponse}
import com.gv.midway.pojo.transaction.Transaction
import com.gv.midway.pojo.verizon.DeviceId
import org.apache.camel.{Exchange, Message}
import org.apache.cxf.binding.soap.SoapHeader
import org.apache.cxf.headers.Header.Direction
import org.joda.time.LocalDate
import org.mockito.Mockito._
import org.w3c.dom.Element

import collection.JavaConversions._

class TestCommonUtil extends TestMocks {

  val VALID_CARRIERS = List(IConstant.BSCARRIER_SERVICE_KORE, IConstant.BSCARRIER_SERVICE_ATTJASPER, IConstant.BSCARRIER_SERVICE_VERIZON)

  val today = new LocalDate()

  List(
    today
    , today.plusDays(1)
    , today.plusDays(10)
    , today.plusDays(100)
    , today.plusMonths(1)
    , today.plusMonths(10)
    , today.plusMonths(100)
    , today.plusYears(1)
    , today.plusYears(10)
    , today.plusYears(100)
    , today.minusDays(1)
    , today.minusDays(10)
    , today.minusDays(100)
    , today.minusMonths(1)
    , today.minusMonths(10)
    , today.minusMonths(100)
    , today.minusYears(1)
    , today.minusYears(10)
    , today.minusYears(100)
  ).map {
    _.toString("yyyy-MM-dd")
  }.foreach { date =>

    test(s"isValidDateFormat($date) === true") {
      assert(CommonUtil.isValidDateFormat(date) === true)
    }
  }

  List(
    "ABC"
    , ""
    , "date"
    , "2016-09-1234"
    , "123-12-12"
    , "1234-123-12"
    , "1234-13-12"
    , "1234-00-12"
//    , "2111111"  //TODO According to the API, this shouldn't be allowed, but it is passing as a valid format
  ).foreach { invalidDate =>

    test(s"isValidDateFormat($invalidDate) === false") {
      assert(CommonUtil.isValidDateFormat(invalidDate) === false)
    }
  }


  test("isValidDateFormat with extra white space") {
    assert(CommonUtil.isValidDateFormat("2016-09-29     ") === true)
  }

  test("isValidDateFormat(null) === false") {
    assert(CommonUtil.isValidDateFormat(null) === false)
  }

  test("getMidwayTransactionID") {
    val transactionId = CommonUtil.getMidwayTransactionID
    assert(transactionId.matches("""\d+"""))
  }

  List(
    "KORE"
    , "kore"
    , "KoRe"
  ).foreach{ kore =>
    test(s"getDerivedCarrierName($kore)") {
      assert(CommonUtil.getDerivedCarrierName(kore) === IConstant.BSCARRIER_SERVICE_KORE)
    }
  }

  List(
    "ATTJASPER"
    , "aTtJaSpEr"
    , "attjasper"
  ).foreach { att =>
    test(s"getDerivedCarrierName($att)") {
      assert(CommonUtil.getDerivedCarrierName(att) === IConstant.BSCARRIER_SERVICE_ATTJASPER)
    }
  }

  List(
    "VERIZON"
    , "verizon"
    , "vErIzOn"
  ).foreach { verizon =>
    test(s"getDerivedCarrierName($verizon)") {
      assert(CommonUtil.getDerivedCarrierName(verizon) === IConstant.BSCARRIER_SERVICE_VERIZON)
    }
  }

  List(
    "null"
    , "TMobile"
    , "Cricket"
  ).foreach { invalid =>
    test(s"getDerivedCarrierName($invalid)") {
      val thrown = intercept[InvalidParameterException] {
        CommonUtil.getDerivedCarrierName(invalid)
      }
      assert(thrown.getMessage === "Unsupported bsCarrier field value.  Must pass [Verizon, Kore, or AttJasper].")
      //assert(CommonUtil.getDerivedCarrierName(invalid) === null)
    }
  }

  CommonUtil.endPointList.foreach{ endPoint =>
    test(s"isProvisioningMethod($endPoint)") {
      assert(CommonUtil.isProvisioningMethod(endPoint))
    }
  }

  test("isProvisioningMethod(invalid)") {
    assert(!CommonUtil.isProvisioningMethod("invalid"))
  }

  test("getRecommendedDeviceIdentifier - unknown kind") {
    val di = deviceId("1", "2")
    val answer = CommonUtil.getRecommendedDeviceIdentifier(Array(di))
    assert(answer === di)
  }

  List(
    "ICCID"
    , "ESN"
    , "MEID"
  ).foreach{ kind =>
    test(s"getRecommendedDeviceIdentifier($kind)") {
      val knownKind = deviceId("1", kind)

      val answer = CommonUtil.getRecommendedDeviceIdentifier(Array(deviceId("1", "2"), knownKind, deviceId("3", "4")))
      assert(answer === knownKind)
    }
  }

  test("getSimNumber") {
    val sim = deviceId("1", "SIM")
    val deviceIds = Array(deviceId("3", "4"), deviceId("8293239823", "skldjf;sdlkfjs"), sim, deviceId("Hello", "World"))
    val answer = CommonUtil.getSimNumber(deviceIds)
    assert(answer === sim)
  }

  //TODO Should this also accept ATT as a carrier?
  List(IConstant.BSCARRIER_SERVICE_KORE, IConstant.BSCARRIER_SERVICE_VERIZON).foreach{ carrier =>
    val date = "2016-09-30"
    test(s"validateJobParameterForDeviceUsage($date, $carrier)") {
      //TODO It really isn't a good design pattern to have s null result mean a valid state. It is preferable to reduce the usages of null.
      val response = CommonUtil.validateJobParameterForDeviceUsage(jobParameter(date, carrier))
      assert(response === null)
    }
  }

  //TODO Should this allow other carriers besides VERIZON?
  List(IConstant.BSCARRIER_SERVICE_VERIZON).foreach{ carrier =>
    val date = "2016-09-30"
    test(s"validateJobParameterForDeviceConnection($date, $carrier)") {
      val response = CommonUtil.validateJobParameterForDeviceConnection(jobParameter(date, carrier))
      assert(response === null)
    }
  }

  List(
    ("invalid date", jobParameter("abc", IConstant.BSCARRIER_SERVICE_VERIZON))
    , ("invalid carrier", jobParameter("2015-09-30", "YoYoMa"))
    , ("null parameters", null)
  ).foreach { case(desc, jp) =>

    test(s"validateJobParameterForDeviceUsage - $desc") {
      print(jp)
      assertNonEmptyResponse(CommonUtil.validateJobParameterForDeviceUsage(jp))
    }

    test(s"validateJobParameterForDeviceConnection - $desc") {
      assertNonEmptyResponse(CommonUtil.validateJobParameterForDeviceConnection(jp))
    }
  }

  List(
    ("1", "2016-09-01", "2016-09-01")
    , ("15", "2016-09-01", "2016-08-15")
    , (null, "2016-09-01", null)
    , ("abx", "2016-09-01", null)
    , ("30", "2016-09-01", "2016-08-30")
    , ("30", "2016-09-30", "2016-09-30")
    , ("30", "2016-09-29", "2016-08-30")
    , ("30", "2016-09-15", "2016-08-30")
    , ("8", "2016-09-15", "2016-09-08")
    , ("8", "2016-09-25", "2016-09-08")
    , ("8", "2016-09-08", "2016-09-08")
    , ("13", "2016-09-27", "2016-09-13")
    , ("11", "2016-09-22", "2016-09-11")
  ).foreach { case (billingDay, jobDate, expected) =>
    test(s"getDeviceBillingStartDate($billingDay, $jobDate)") {
      assert(CommonUtil.getDeviceBillingStartDate(billingDay, jobDate) === expected)
    }
  }

  test("setMessageHeader - non Verizon") {
    withMockExchangeAndMessage { (exchange, message) =>

      CommonUtil.setMessageHeader(exchange)

      verify(message, times(1)).setHeader("VZ-M2M-Token", "")
      verify(message, times(1)).setHeader("Authorization", "Bearer ")
      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    }
  }

  test("setMessageHeader - Verizon") {
    withMockExchangeAndMessage { (exchange, message) =>

      val sessionToken = "sessionToken"
      val authToken = "authToken"

      when(exchange.getProperty(IConstant.VZ_SEESION_TOKEN)).thenReturn(sessionToken, Nil: _*)
      when(exchange.getProperty(IConstant.VZ_AUTHORIZATION_TOKEN)).thenReturn(authToken, Nil: _*)

      CommonUtil.setMessageHeader(exchange)

      verify(message, times(1)).setHeader("VZ-M2M-Token", sessionToken)
      verify(message, times(1)).setHeader("Authorization", s"Bearer $authToken")
      verify(message, times(1)).setHeader(Exchange.CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json")
      verify(message, times(1)).setHeader(Exchange.HTTP_METHOD, "POST")
    }
  }

  test("getSOAPHeaders") {
    val username = "user"
    val password = "123456"
    val headers: List[SoapHeader] = CommonUtil.getSOAPHeaders(username, password).toList

    assert(headers.length === 1)
    val header = headers.head
    assert(header.getName.getLocalPart === "soapHeader")
    assert(header.getDirection === Direction.DIRECTION_OUT)
    header.getObject match {
      case e: Element =>
        val usernameTokenElement = e.getElementsByTagName("wsse:UsernameToken").item(0)
        val usernameElement = usernameTokenElement.getFirstChild
        assert(usernameElement.getNodeName === "wsse:Username")
        assert(usernameElement.getTextContent === username)
        val passwordElement = usernameTokenElement.getChildNodes.item(1)
        assert(passwordElement.getNodeName === "wsse:Password")
        assert(passwordElement.getTextContent === password)
      case _ => fail("Invalid object")
    }
  }

  test("getSOAPResposneFromExchange") {
    pending
  }

  test("getSOAPErrorResposneFromExchange") {
    pending
  }

  List(IConstant.BSCARRIER_SERVICE_KORE, IConstant.BSCARRIER_SERVICE_ATTJASPER).foreach { carrier =>
    withMockDataForSetListInWireTap(carrier) { (message, transactions) =>
      verify(message, times(1)).setBody(transactions)
    }
  }

  withMockDataForSetListInWireTap(IConstant.BSCARRIER_SERVICE_VERIZON) { (message, transactions) =>
    verify(message, times(0)).setBody(transactions)
  }

  List(
    ("CustomField1", 17)
    , ("CustomField2", 18)
    , ("CustomField3", 19)
    , ("CustomField4", 73)
    , ("CustomField5", 74)
    , ("CustomField6", 75)
    , ("CustomField7", 76)
    , ("CustomField8", 77)
    , ("CustomField9", 78)
    , ("CustomField10", 79)
    , ("YoYoMan", -1)
  ).foreach { case (field, int) =>
      test(s"getAttJasperCustomField($field)") {
        assert(CommonUtil.getAttJasperCustomField(field) === int)
      }
  }

  private def deviceId(id: String, kind: String): DeviceId = {
    val di = new DeviceId()
    di.setId(id)
    di.setKind(kind)
    di
  }

  private def jobParameter(date: String, carrierName: String): JobParameter = {
    val jp = new JobParameter()
    jp.setDate(date)
    jp.setCarrierName(carrierName)
    jp
  }

  private def assertNonEmptyResponse(response: JobinitializedResponse) = {
    assert(response != null)
    assert(!response.getMessage.isEmpty)
  }

  private def withMockDataForSetListInWireTap(carrier: String)(f: (Message, java.util.List[Transaction]) => Unit) = {
    val exchange = mock[Exchange]
    val message = mock[Message]
    val transaction = mock[Transaction]
    val transactions: java.util.List[Transaction] = List(transaction)

    when(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME)).thenReturn(carrier, Nil: _*)
    when(exchange.getIn).thenReturn(message)

    test(s"setListInWireTap - $carrier") {

      CommonUtil.setListInWireTap(exchange, transactions)

      f(message, transactions)
    }
  }
}