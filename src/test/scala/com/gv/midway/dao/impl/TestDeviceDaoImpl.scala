package com.gv.midway.dao.impl

import com.gv.midway.pojo.device.request.{DeviceDataArea, SingleDevice}
import com.gv.midway.pojo.deviceInformation.response.{DeviceInformation, DeviceInformationResponse, DeviceInformationResponseDataArea}
import org.springframework.data.mongodb.core.MongoTemplate
import java.lang.{Integer => JInt}
import java.util.{ArrayList, List => JList}

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.pojo.connectionInformation.DeviceEvents
import com.gv.midway.pojo.connectionInformation.request.{ConnectionInformationMidwayRequest, ConnectionInformationRequestMidwayDataArea}
import com.gv.midway.pojo.device.response.BatchDeviceId
import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceEvent, DeviceUsage}
import com.gv.midway.pojo.deviceInformation.request.{DeviceInformationRequest, DeviceInformationRequestDataArea}
import com.gv.midway.pojo.usageInformation.request.{DevicesUsageByDayAndCarrierRequest, DevicesUsageByDayAndCarrierRequestDataArea, UsageInformationMidwayRequest, UsageInformationRequestMidwayDataArea}
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier
import com.gv.midway.pojo.{Header, Response}
import org.apache.camel.Exchange
import org.mockito.ArgumentCaptor
import org.mockito.Matchers._
import org.mockito.Mockito._
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.query.Query

import collection.JavaConversions._

class TestDeviceDaoImpl extends TestMocks {

  private val netSuiteId: JInt = 9000
  private val startDate = "2016-10-01"
  private val endDate = "2016-10-19"

  test("updateDeviceDetails - happy flow insert") {

    daoWithMockMongoTemplate{ (dao, template) =>

      val netSuiteId: JInt = 9000

      val singleDevice = buildSingleDevice(netSuiteId)

      when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(null, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceInformation])

      val response = dao.updateDeviceDetails(singleDevice)

      assert(response.getHeader === singleDevice.getHeader)

      verify(template, times(1)).insert(captor.capture())
      val capturedDeviceInformation = captor.getValue
      assert(capturedDeviceInformation === singleDevice.getDataArea.getDevice)
      assert(capturedDeviceInformation.getLastUpdated != null)

      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB, IResponse.SUCCESS_MESSAGE)
    }
  }

  test("updateDeviceDetails - happy flow save") {

    daoWithMockMongoTemplate{ (dao, template) =>

      val netSuiteId: JInt = 9000

      val singleDevice = buildSingleDevice(netSuiteId)

      val mockDeviceInformation = new DeviceInformation
      mockDeviceInformation.setMidwayMasterDeviceId("master id")

      when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(mockDeviceInformation, Nil: _*)

      val captor = ArgumentCaptor.forClass(classOf[DeviceInformation])

      val response = dao.updateDeviceDetails(singleDevice)

      assert(response.getHeader === singleDevice.getHeader)

      verify(template, times(1)).save(captor.capture())
      val capturedDeviceInformation = captor.getValue
      assert(capturedDeviceInformation === singleDevice.getDataArea.getDevice)
      assert(capturedDeviceInformation.getLastUpdated != null)
      assert(capturedDeviceInformation.getMidwayMasterDeviceId === mockDeviceInformation.getMidwayMasterDeviceId)

      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_UPDATE_MIDWAYDB, IResponse.SUCCESS_MESSAGE)
    }
  }

  test("updateDeviceDetails - null netSuiteId") {
    daoWithMockMongoTemplate{ (dao, template) =>
      val singleDevice = buildSingleDevice(null)

      val response = dao.updateDeviceDetails(singleDevice)

      assertResponse(response.getResponse, IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("updateDeviceDetails - exception thrown") {

    daoWithMockMongoTemplate{ (dao, _) =>

      val header = mock[Header]

      val device = mock[SingleDevice]
      when(device.getHeader).thenReturn(header, Nil: _*)
      when(device.getDataArea).thenThrow(new RuntimeException("Boom!"))

      val response = dao.updateDeviceDetails(device)

      assert(response.getHeader === header)
      assertResponse(response.getResponse, IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDeviceInformationDB - happy flow") {

    daoWithMockMongoTemplate { (dao, template) =>

      val request = buildDeviceInformationRequest(9000)

      val deviceInfo = mock[DeviceInformation]

      when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(deviceInfo, Nil: _*)

      val response = dao.getDeviceInformationDB(request)

      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea.getDevices === deviceInfo)
    }
  }

  test("getDeviceInformationDB - no device found") {

    daoWithMockMongoTemplate { (dao, template) =>

      val request = buildDeviceInformationRequest(9000)

      val deviceInfo = mock[DeviceInformation]

      when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(null, Nil: _*)

      val response = dao.getDeviceInformationDB(request)

      assertResponse(response.getResponse, IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea.getDevices === null)
    }
  }

  test("getDeviceInformationDB - null setSuiteId") {
    daoWithMockMongoTemplate { (dao, _) =>
      val request = buildDeviceInformationRequest(null)

      val response = dao.getDeviceInformationDB(request)

      assertResponse(response.getResponse, IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB, IResponse.ERROR_MESSAGE)
      assert(response.getDataArea === null)
    }
  }

  test("getDeviceInformationDB - exception thrown") {

    daoWithMockMongoTemplate { (dao, template) =>

      val request = buildDeviceInformationRequest(9000)

      when(template.findOne(any(), any())).thenThrow(new RuntimeException("Boom!"))

      val response = dao.getDeviceInformationDB(request)
      assertResponse(response.getResponse, IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
      assert(response.getHeader === request.getHeader)
    }
  }

  test("setDeviceInformationDB") {
    daoWithMockMongoTemplate{ (dao, template) =>
      val exchange = mock[Exchange]
      val deviceInfo = mock[DeviceInformation]
      when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)
      when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(deviceInfo)

      val captor = ArgumentCaptor.forClass(classOf[DeviceInformation])
      dao.setDeviceInformationDB(exchange)
      verify(exchange, times(1)).setProperty(same(IConstant.MIDWAY_DEVICEINFO_DB), captor.capture())

      assert(captor.getValue === deviceInfo)
    }
  }

  test("updateDeviceInformationDB - save") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>

        when(exchange.getProperty(IConstant.MIDWAY_DEVICEINFO_DB)).thenReturn("", Nil: _*)

        val deviceInfo = mock[DeviceInformation]
        val dataArea = new DeviceInformationResponseDataArea
        dataArea.setDevices(deviceInfo)
        val response = new DeviceInformationResponse()
        response.setDataArea(dataArea)

        when(message.getBody).thenReturn(response, Nil: _*)

        dao.updateDeviceInformationDB(exchange)

        verify(deviceInfo, times(1)).setMidwayMasterDeviceId(anyString())
        verify(template, times(1)).save(deviceInfo)
        verify(message, times(1)).setBody(response)
      }
    }
  }

  test("updateDeviceInformationDB - insert") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>

        when(exchange.getProperty(IConstant.MIDWAY_NETSUITE_ID)).thenReturn(netSuiteId, Nil: _*)

        val deviceInfo = mock[DeviceInformation]
        val dataArea = new DeviceInformationResponseDataArea
        dataArea.setDevices(deviceInfo)
        val response = new DeviceInformationResponse()
        response.setDataArea(dataArea)

        when(message.getBody).thenReturn(response, Nil: _*)

        dao.updateDeviceInformationDB(exchange)

        verify(deviceInfo, times(1)).setNetSuiteId(netSuiteId)
        verify(template, times(1)).insert(deviceInfo)
        verify(message, times(1)).setBody(response)
      }
    }
  }

  test("bulkOperationDeviceUpload - null netSuiteId") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>

        val errors: JList[BatchDeviceId] = new ArrayList[BatchDeviceId]
        val deviceInfo = new DeviceInformation
        when(message.getBody).thenReturn(deviceInfo, Nil: _*)
        when(exchange.getProperty(IConstant.BULK_ERROR_LIST)).thenReturn(errors, Nil: _*)

        dao.bulkOperationDeviceUpload(exchange)

        assert(errors.length === 1)
        assert(errors.head.getErrorMessage === IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB)
      }
    }
  }

  test("bulkOperationDeviceUpload - insert") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>

        val list: JList[BatchDeviceId] = new ArrayList[BatchDeviceId]
        val deviceInfo = new DeviceInformation
        deviceInfo.setNetSuiteId(netSuiteId)
        when(message.getBody).thenReturn(deviceInfo, Nil: _*)
        when(exchange.getProperty(IConstant.BULK_SUCCESS_LIST)).thenReturn(list, Nil: _*)
        when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(null, Nil: _*)

        dao.bulkOperationDeviceUpload(exchange)

        verify(template, times(1)).insert(deviceInfo)

        assert(list.length === 1)
        assert(list.head.getNetSuiteId === netSuiteId.toString)
      }
    }
  }

  test("bulkOperationDeviceUpload - save") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>

        val list: JList[BatchDeviceId] = new ArrayList[BatchDeviceId]
        val deviceInfo = new DeviceInformation
        deviceInfo.setNetSuiteId(netSuiteId)
        when(message.getBody).thenReturn(deviceInfo, Nil: _*)
        when(exchange.getProperty(IConstant.BULK_SUCCESS_LIST)).thenReturn(list, Nil: _*)
        val dbDeviceInfo = new DeviceInformation
        dbDeviceInfo.setMidwayMasterDeviceId("1234")
        when(template.findOne(any(classOf[Query]), same(classOf[DeviceInformation]))).thenReturn(dbDeviceInfo, Nil: _*)

        dao.bulkOperationDeviceUpload(exchange)

        verify(template, times(1)).save(deviceInfo)

        assert(list.length === 1)
        assert(list.head.getNetSuiteId === netSuiteId.toString)

        assert(deviceInfo.getMidwayMasterDeviceId === dbDeviceInfo.getMidwayMasterDeviceId)
      }
    }
  }

  test("bulkOperationDeviceUpload - exception") {
    daoWithMockMongoTemplate { (dao, template) =>
      withMockExchangeAndMessage() { (exchange, message) =>
        val deviceInfo = new DeviceInformation
        deviceInfo.setNetSuiteId(netSuiteId)
        when(message.getBody).thenReturn(deviceInfo,  Nil: _*)

        when(template.findOne(any(), any())).thenThrow(new RuntimeException("Boom!"))
        val errors: JList[BatchDeviceId] = new ArrayList[BatchDeviceId]
        when(exchange.getProperty(IConstant.BULK_ERROR_LIST)).thenReturn(errors, Nil: _*)

        dao.bulkOperationDeviceUpload(exchange)

        assert(errors.length === 1)
        assert(errors.head.getNetSuiteId === netSuiteId.toString)
        assert(errors.head.getErrorMessage === IResponse.ERROR_DESCRIPTION_UPDATE_MIDWAYDB)
      }
    }
  }

  test("getAllDevices") {
    daoWithMockMongoTemplate { (dao, template) =>
      val list = new ArrayList[DeviceInformation]
      list.add(new DeviceInformation)

      when(template.findAll(classOf[DeviceInformation])).thenReturn(list)

      val response = dao.getAllDevices

      assert(response === list)
    }
  }

  List(
    ("null netSuiteId", usageRequest(null), invalidResponse(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB))
    , ("null startDate", usageRequest(startDate = null), invalidResponse(IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB))
    , ("null endDate", usageRequest(endDate = null), invalidResponse(IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB))
    , ("invalid startDate", usageRequest(startDate = "hello"), invalidResponse(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB))
    , ("invalid endDate", usageRequest(endDate = "hello"), invalidResponse(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB))
    , ("invalid date order", usageRequest(startDate = endDate, endDate = startDate), invalidResponse(IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB))
  ).foreach { case (name, request, expectedResponse) =>

    test(s"getDeviceUsageInfoDB - $name") {
      daoWithMockMongoTemplate { (dao, template) =>
        val response = dao.getDeviceUsageInfoDB(request)

        assert(response.getHeader === request.getHeader)
        assert(response.getDataArea === null)
        assertResponse(response.getResponse, expectedResponse.getResponseCode, expectedResponse.getResponseDescription, expectedResponse.getResponseStatus)
      }
    }
  }

  test("getDeviceUsageInfoDB - empty") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = usageRequest()

      when(template.find(any(classOf[Query]), same(classOf[DeviceUsage]))).thenReturn(new ArrayList[DeviceUsage], Nil: _*)

      val response = dao.getDeviceUsageInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea != null)
      assertResponse(response.getResponse, IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDeviceUsageInfoDB - exception thrown") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = usageRequest()

      when(template.find(any(classOf[Query]), same(classOf[DeviceUsage]))).thenThrow(new RuntimeException("Boom!"))

      val response = dao.getDeviceUsageInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea != null)
      assertResponse(response.getResponse, IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDeviceUsageInfoDB - 2 in db") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = usageRequest()

      val usage1 = new DeviceUsage
      usage1.setCarrierName(IConstant.BSCARRIER_SERVICE_VERIZON)
      usage1.setDate(endDate)
      usage1.setDataUsed(100l)

      val usage2 = new DeviceUsage
      usage2.setJobId("jobid")
      usage2.setDate(startDate)
      usage2.setDataUsed(9010203l)

      val list = new ArrayList[DeviceUsage]
      list.add(usage1)
      list.add(usage2)

      when(template.find(any(classOf[Query]), same(classOf[DeviceUsage]))).thenReturn(list, Nil: _*)

      val response = dao.getDeviceUsageInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE)
      assert(response.getDataArea != null)
      val returnedList = response.getDataArea.getDeviceUsages
      assert(returnedList.size() === 2)

      assert(returnedList.head.getDate === startDate)
      assert(returnedList.head.getDataUsed === usage2.getDataUsed)

      assert(returnedList.tail.head.getDate === endDate)
      assert(returnedList.tail.head.getDataUsed === usage1.getDataUsed)
    }
  }

  List(
    ("null netSuiteId", connRequest(null), invalidResponse(IResponse.ERROR_DESCRIPTION_UPDATE_NETSUITE_MIDWAYDB))
    , ("null startDate", connRequest(startDate = null), invalidResponse(IResponse.ERROR_DESCRIPTION_START_DATE_FORMAT_MIDWAYDB))
    , ("null endDate", connRequest(endDate = null), invalidResponse(IResponse.ERROR_DESCRIPTION_END_DATE_FORMAT_MIDWAYDB))
    , ("invalid startDate", connRequest(startDate = "hello"), invalidResponse(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB))
    , ("invalid endDate", connRequest(endDate = "hello"), invalidResponse(IResponse.ERROR_DESCRIPTION_STARTDATE_VALIDATE_MIDWAYDB))
    , ("invalid date order", connRequest(startDate = endDate, endDate = startDate), invalidResponse(IResponse.ERROR_DESCRIPTION_START_END_VALIDATION_MIDWAYDB))
  ).foreach { case (name, request, expectedResponse) =>
      test(s"getDeviceConnectionHistoryInfoDB - $name") {
        daoWithMockMongoTemplate { (dao, template) =>
          val response = dao.getDeviceConnectionHistoryInfoDB(request)

          assert(response.getHeader === request.getHeader)
          assert(response.getDataArea === null)
          assertResponse(response.getResponse, expectedResponse.getResponseCode, expectedResponse.getResponseDescription, expectedResponse.getResponseStatus)
        }
      }
  }

  test("getDeviceConnectionHistoryInfoDB - empty") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = connRequest()

      when(template.find(any(classOf[Query]), same(classOf[DeviceConnection]))).thenReturn(new ArrayList[DeviceConnection], Nil: _*)

      val response = dao.getDeviceConnectionHistoryInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea === null)
      assertResponse(response.getResponse, IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDeviceConnectionHistoryInfoDB - exception thrown") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = connRequest()

      when(template.find(any(classOf[Query]), same(classOf[DeviceConnection]))).thenThrow(new RuntimeException("Boom!"))

      val response = dao.getDeviceConnectionHistoryInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea != null)
      assertResponse(response.getResponse, IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDeviceConnectionHistoryInfoDB - 2 in db") {
    daoWithMockMongoTemplate { (dao, template) =>
      val request = connRequest()

      val event1 = new DeviceEvent
      event1.setOccurredAt(endDate)
      event1.setBytesUsed("14")
      event1.setEventType("type1")
      val conn1 = new DeviceConnection
      conn1.setEvent(Array(event1))

      val event2 = new DeviceEvent
      event2.setOccurredAt(startDate)
      event2.setBytesUsed("140")
      event2.setEventType("type2")
      val conn2 = new DeviceConnection
      conn2.setEvent(Array(event2))

      val list = new ArrayList[DeviceConnection]
      list.add(conn1)
      list.add(conn2)
      list.add(new DeviceConnection)

      when(template.find(any(classOf[Query]), same(classOf[DeviceConnection]))).thenReturn(list, Nil: _*)

      val response = dao.getDeviceConnectionHistoryInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assert(response.getDataArea != null)
      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE)

      val returnedList: List[DeviceEvents] = response.getDataArea.getEvents.toList

      assert(returnedList.length === 2)

      assert(returnedList.head.getOccurredAt === event2.getOccurredAt)
      assert(returnedList.head.getEventType === event2.getEventType)
      assert(returnedList.head.getBytesUsed === event2.getBytesUsed)

      assert(returnedList.tail.head.getOccurredAt === event1.getOccurredAt)
      assert(returnedList.tail.head.getEventType === event1.getEventType)
      assert(returnedList.tail.head.getBytesUsed === event1.getBytesUsed)
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB - invalid date") {
    daoWithMockMongoTemplate { (dao, template) =>

      val request = usageByCarrierRequest("hello")

      val response = dao.getDevicesUsageByDayAndCarrierInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assertResponse(response.getResponse, IResponse.INVALID_PAYLOAD, IResponse.ERROR_DESCRIPTION_DATE_VALIDATE_JOB_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB - exception thrown") {
    daoWithMockMongoTemplate { (dao, template) =>

      val request = usageByCarrierRequest()

      when(template.aggregate(any(), same(classOf[DeviceUsage]), same(classOf[DevicesUsageByDayAndCarrier]))).thenThrow(new RuntimeException("Boom!"))

      val response = dao.getDevicesUsageByDayAndCarrierInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assertResponse(response.getResponse, IResponse.DB_ERROR_CODE, IResponse.ERROR_DESCRIPTION_EXCEPTION_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB - no data") {
    daoWithMockMongoTemplate { (dao, template) =>

      val request = usageByCarrierRequest()

      val results = mock[AggregationResults[DevicesUsageByDayAndCarrier]]
      when(results.getMappedResults).thenReturn(new ArrayList[DevicesUsageByDayAndCarrier])

      when(template.aggregate(any(), same(classOf[DeviceUsage]), same(classOf[DevicesUsageByDayAndCarrier]))).thenReturn(results, Nil: _*)

      val response = dao.getDevicesUsageByDayAndCarrierInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assertResponse(response.getResponse, IResponse.NO_DATA_FOUND_CODE, IResponse.ERROR_DESCRIPTION_NODATA_DEVCIEINFO_MIDWAYDB, IResponse.ERROR_MESSAGE)
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB - 2 records") {
    daoWithMockMongoTemplate { (dao, template) =>

      val request = usageByCarrierRequest()

      val usage1 = new DevicesUsageByDayAndCarrier
      val usage2 = new DevicesUsageByDayAndCarrier
      val usageList = new ArrayList[DevicesUsageByDayAndCarrier]
      usageList.add(usage1)
      usageList.add(usage2)

      val results = mock[AggregationResults[DevicesUsageByDayAndCarrier]]
      when(results.getMappedResults).thenReturn(usageList)

      when(template.aggregate(any(), same(classOf[DeviceUsage]), same(classOf[DevicesUsageByDayAndCarrier]))).thenReturn(results, Nil: _*)

      val response = dao.getDevicesUsageByDayAndCarrierInfoDB(request)

      assert(response.getHeader === request.getHeader)
      assertResponse(response.getResponse, IResponse.SUCCESS_CODE, IResponse.SUCCESS_DESCRIPTION_DEVCIEINFO_MIDWAYDB, IResponse.SUCCESS_MESSAGE)
      assert(response.getDataArea.getDevices.length === 2)
      assert(response.getDataArea.getDate === startDate)
    }
  }

  private def usageByCarrierRequest(date: String = startDate, carrier: String = IConstant.BSCARRIER_SERVICE_VERIZON): DevicesUsageByDayAndCarrierRequest = {
    val header = mock[Header]
    when(header.getBsCarrier).thenReturn(carrier, Nil: _*)

    val dataArea = new DevicesUsageByDayAndCarrierRequestDataArea
    dataArea.setDate(date)

    val request = new DevicesUsageByDayAndCarrierRequest
    request.setHeader(header)
    request.setDateArea(dataArea)

    request
  }
  
  private def connRequest(nsId: JInt = netSuiteId, startDate: String = startDate, endDate: String = endDate): ConnectionInformationMidwayRequest = {
    val header = mock[Header]

    val dataArea = new ConnectionInformationRequestMidwayDataArea
    dataArea.setNetSuiteId(nsId)
    dataArea.setStartDate(startDate)
    dataArea.setEndDate(endDate)

    val request = new ConnectionInformationMidwayRequest
    request.setHeader(header)
    request.setDataArea(dataArea)
    request
  }

  private def usageRequest(nsId: JInt = netSuiteId, startDate: String = startDate, endDate: String = endDate): UsageInformationMidwayRequest = {
    val header = mock[Header]

    val dataArea = new UsageInformationRequestMidwayDataArea
    dataArea.setNetSuiteId(nsId)
    dataArea.setStartDate(startDate)
    dataArea.setEndDate(endDate)

    val request = new UsageInformationMidwayRequest()
    request.setHeader(header)
    request.setUsageInformationRequestMidwayDataArea(dataArea)

    request
  }

  private def invalidResponse(desc: String, code: JInt = IResponse.INVALID_PAYLOAD, status: String = IResponse.ERROR_MESSAGE): Response =
    new Response(code, desc, status)

  private def buildDeviceInformationRequest(netSuiteId: JInt): DeviceInformationRequest = {
    val header = mock[Header]

    val dataArea = new DeviceInformationRequestDataArea
    dataArea.setNetSuiteId(netSuiteId)

    val request = new DeviceInformationRequest
    request.setDataArea(dataArea)
    request.setHeader(header)

    request
  }

  private def buildSingleDevice(netSuiteId: JInt): SingleDevice = {
    val deviceInfo = new DeviceInformation
    deviceInfo.setNetSuiteId(netSuiteId)

    val dataArea = new DeviceDataArea
    dataArea.setDevices(deviceInfo)

    val header = mock[Header]

    val singleDevice = new SingleDevice
    singleDevice.setDataArea(dataArea)
    singleDevice.setHeader(header)

    singleDevice
  }

  private def assertResponse(response: Response, code: JInt, desc: String, status: String): Unit = {
    assert(response.getResponseCode === code)
    assert(response.getResponseDescription === desc)
    assert(response.getResponseStatus === status)
  }

  private def daoWithMockMongoTemplate(f: (DeviceDaoImpl, MongoTemplate) => Unit): Unit = {

    val dao = new DeviceDaoImpl
    val template = mock[MongoTemplate]
    dao.mongoTemplate = template

    f(dao, template)
  }
}