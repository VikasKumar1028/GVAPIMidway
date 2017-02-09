package com.gv.midway.service.impl

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.dao.IDeviceDao
import com.gv.midway.pojo.device.request.{BulkDevices, DevicesDataArea, SingleDevice}
import com.gv.midway.pojo.device.response.{BatchDeviceId, UpdateDeviceResponse}
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest
import com.gv.midway.pojo.deviceInformation.response.{DeviceInformation, DeviceInformationResponse}
import org.apache.camel.Message
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import java.util.{List => JList}

import com.gv.midway.exception.InvalidParameterException
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse
import com.gv.midway.pojo.connectionInformation.request.{ConnectionInformationMidwayRequest, ConnectionInformationRequest}
import com.gv.midway.pojo.session.SessionRequest
import com.gv.midway.pojo.usageInformation.request.{DevicesUsageByDayAndCarrierRequest, UsageInformationMidwayRequest, UsageInformationRequest}
import com.gv.midway.pojo.usageInformation.response.{DevicesUsageByDayAndCarrierResponse, UsageInformationMidwayResponse}
import org.mockito.Matchers._

class TestDeviceServiceImpl extends TestMocks {

  test("updateDeviceDetails") {
    withMockExchangeAndMessage { (exchange, message) =>

      val device = new SingleDevice
      withServiceAndMockDAO(message, device) { (service, dao) =>

        val mockResponse = mock[UpdateDeviceResponse]
        when(dao.updateDeviceDetails(device)).thenReturn(mockResponse, Nil: _*)

        val response = service.updateDeviceDetails(exchange)
        assert(response === mockResponse)
      }
    }
  }

  test("getDeviceInformationDB") {
    withMockExchangeAndMessage { (exchange, message) =>

      val request = new DeviceInformationRequest
      withServiceAndMockDAO(message, request) { (service, dao) =>
        val mockResponse = mock[DeviceInformationResponse]
        when(dao.getDeviceInformationDB(request)).thenReturn(mockResponse, Nil: _*)

        val response = dao.getDeviceInformationDB(request)
        assert(response === mockResponse)
      }
    }
  }

  test("updateDevicesDetailsBulk") {
    withMockExchangeAndMessage { (exchange, message) =>

      val device1 = new DeviceInformation
      val dataArea = new DevicesDataArea
      dataArea.setDevices(Array(device1))
      val devices = new BulkDevices
      devices.setDataArea(dataArea)
      withServiceAndMockDAO(message, devices) { (service, dao) =>

        val captor = ArgumentCaptor.forClass(classOf[JList[DeviceInformation]])
        val successListCaptor = ArgumentCaptor.forClass(classOf[JList[BatchDeviceId]])
        val errorListCaptor = ArgumentCaptor.forClass(classOf[JList[BatchDeviceId]])

        service.updateDevicesDetailsBulk(exchange)

        verify(exchange, times(1)).setProperty(same(IConstant.BULK_SUCCESS_LIST), successListCaptor.capture())
        verify(exchange, times(1)).setProperty(same(IConstant.BULK_ERROR_LIST), errorListCaptor.capture())
        verify(message, times(1)).setBody(captor.capture())

        val successList = successListCaptor.getValue
        assert(successList.size() === 0)
        val errorList = errorListCaptor.getValue
        assert(errorList.size() === 0)
        val capturedDevices = captor.getValue
        assert(capturedDevices.size() === 1)
        assert(capturedDevices.get(0) === device1)
      }
    }
  }

  test("setDeviceInformationDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO(message, null) { (service, dao) =>
        service.setDeviceInformationDB(exchange)
        verify(dao, times(1)).setDeviceInformationDB(exchange)
      }
    }
  }

  test("updateDeviceInformationDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO(message, null) { (service, dao) =>
        service.updateDeviceInformationDB(exchange)
        verify(dao, times(1)).updateDeviceInformationDB(exchange)
      }
    }
  }

  test("bulkOperationDeviceSyncInDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO(message, null) { (service, dao) =>
        service.bulkOperationDeviceSyncInDB(exchange)
        verify(dao, times(1)).bulkOperationDeviceUpload(exchange)
      }
    }
  }

  test("getAllDevices") {
    val dao = mock[IDeviceDao]
    val service = new DeviceServiceImpl(dao)

    val mockDevices = mock[java.util.ArrayList[DeviceInformation]]

    when(dao.getAllDevices).thenReturn(mockDevices, Nil: _*)

    val devices = service.getAllDevices
    assert(devices === mockDevices)
  }

  test("getDeviceUsageInfoDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      val request = new UsageInformationMidwayRequest
      withServiceAndMockDAO(message, request) { (service, dao) =>

        val mockResponse = mock[UsageInformationMidwayResponse]
        when(dao.getDeviceUsageInfoDB(request)).thenReturn(mockResponse, Nil: _*)
        val response = service.getDeviceUsageInfoDB(exchange)
        assert(response === mockResponse)
      }
    }
  }

  test("getDeviceConnectionHistoryInfoDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      val mockRequest = mock[ConnectionInformationMidwayRequest]
      withServiceAndMockDAO(message, mockRequest) { (service, dao) =>
        val mockResponse = mock[ConnectionInformationMidwayResponse]
        when(dao.getDeviceConnectionHistoryInfoDB(mockRequest)).thenReturn(mockResponse, Nil: _*)
        val response = service.getDeviceConnectionHistoryInfoDB(exchange)
        assert(response === mockResponse)
      }
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB") {
    withMockExchangeAndMessage { (exchange, message) =>
      val mockRequest = mock[DevicesUsageByDayAndCarrierRequest]
      withServiceAndMockDAO(message, mockRequest) { (service, dao) =>
        val mockResponse = mock[DevicesUsageByDayAndCarrierResponse]
        when(dao.getDevicesUsageByDayAndCarrierInfoDB(mockRequest)).thenReturn(mockResponse, Nil: _*)
        val response = service.getDevicesUsageByDayAndCarrierInfoDB(exchange)
        assert(response === mockResponse)
      }
    }
  }

  test("getDeviceSessionInfo") {
    withMockExchangeAndMessage { (exchange, message) =>
      val request = new SessionRequest
      when(message.getBody(classOf[SessionRequest])).thenReturn(request, Nil: _*)
      val dao = mock[IDeviceDao]
      val service = new DeviceServiceImpl(dao)
      val mockResponse = mock[ConnectionInformationRequest]
      when(dao.getDeviceSessionInfo(request)).thenReturn(mockResponse, Nil: _*)
      val response = service.getDeviceSessionInfo(exchange)
      assert(response === mockResponse)
    }
  }

  test("getDeviceSessionInfo - InvalidParameterException") {
    withMockExchangeAndMessage { (exchange, message) =>
      val request = new SessionRequest
      when(message.getBody(classOf[SessionRequest])).thenReturn(request, Nil: _*)
      val dao = mock[IDeviceDao]
      val service = new DeviceServiceImpl(dao)
      val exception = "Boom!"
      when(dao.getDeviceSessionInfo(request)).thenThrow(new InvalidParameterException("code", exception))

      intercept[InvalidParameterException] {
        service.getDeviceSessionInfo(exchange)
      }

      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "400")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, exception)
    }
  }

  test("getDeviceSessionUsage") {
    withMockExchangeAndMessage { (exchange, message) =>
      val request = new SessionRequest
      when(message.getBody(classOf[SessionRequest])).thenReturn(request, Nil: _*)
      val dao = mock[IDeviceDao]
      val service = new DeviceServiceImpl(dao)
      val mockResponse = mock[UsageInformationRequest]
      when(dao.getDeviceSessionUsage(request)).thenReturn(mockResponse, Nil: _*)
      val response = service.getDeviceSessionUsage(exchange)
      assert(response === mockResponse)
    }
  }

  test("getDeviceSessionUsage - InvalidParameterException") {
    withMockExchangeAndMessage { (exchange, message) =>
      val request = new SessionRequest
      when(message.getBody(classOf[SessionRequest])).thenReturn(request, Nil: _*)
      val dao = mock[IDeviceDao]
      val service = new DeviceServiceImpl(dao)
      val exception = "Boom!"
      when(dao.getDeviceSessionUsage(request)).thenThrow(new InvalidParameterException("code", exception))

      intercept[InvalidParameterException] {
        service.getDeviceSessionUsage(exchange)
      }

      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, "400")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, "Invalid Parameter")
      verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, exception)
    }
  }

  private def withServiceAndMockDAO(message: Message, body: AnyRef)(f: (DeviceServiceImpl, IDeviceDao) => Unit): Unit = {
    when(message.getBody).thenReturn(body, Nil: _*)

    val dao = mock[IDeviceDao]
    val service = new DeviceServiceImpl(dao)
    f(service, dao)
  }
}