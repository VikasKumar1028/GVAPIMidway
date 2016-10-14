package com.gv.midway.controller

import com.gv.midway.constant.{CarrierType, IConstant, JobName, JobType}
import com.gv.midway.pojo.{CarrierProvisioningDeviceResponse, Header}
import com.gv.midway.pojo.activateDevice.request.ActivateDeviceRequest
import com.gv.midway.pojo.changeDeviceServicePlans.request.ChangeDeviceServicePlansRequest
import com.gv.midway.pojo.connectionInformation.ConnectionInformationMidwayResponse
import com.gv.midway.pojo.connectionInformation.deviceSessionBeginEndInfo.response.SessionBeginEndResponse
import com.gv.midway.pojo.connectionInformation.deviceStatus.response.ConnectionStatusResponse
import com.gv.midway.pojo.connectionInformation.request.{ConnectionInformationMidwayRequest, ConnectionInformationRequest}
import com.gv.midway.pojo.customFieldsDevice.request.CustomFieldsDeviceRequest
import com.gv.midway.pojo.deactivateDevice.request.DeactivateDeviceRequest
import com.gv.midway.pojo.device.request.{BulkDevices, SingleDevice}
import com.gv.midway.pojo.device.response.{BatchDeviceResponse, UpdateDeviceResponse}
import com.gv.midway.pojo.deviceInformation.request.DeviceInformationRequest
import com.gv.midway.pojo.deviceInformation.response.DeviceInformationResponse
import com.gv.midway.pojo.job.{JobDetail, JobParameter, JobinitializedResponse}
import com.gv.midway.pojo.reActivateDevice.request.ReactivateDeviceRequest
import com.gv.midway.pojo.restoreDevice.request.RestoreDeviceRequest
import com.gv.midway.pojo.suspendDevice.request.SuspendDeviceRequest
import com.gv.midway.pojo.usageInformation.request.{DevicesUsageByDayAndCarrierRequest, UsageInformationMidwayRequest, UsageInformationRequest}
import com.gv.midway.pojo.usageInformation.response.{DevicesUsageByDayAndCarrierResponse, UsageInformationMidwayResponse, UsageInformationResponse}
import org.apache.camel.ProducerTemplate
import org.mockito.ArgumentCaptor
import org.scalatest.FunSuite
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Matchers._

class TestAdaptationLayerServiceImplLayer extends FunSuite with MockitoSugar {

  private val region = "region"
  private val timestamp = "timestamp"
  private val organization = "organization"
  private val transactionId = "transactionId"
  private val sourceName = "sourceName"
  private val applicationName = "applicationName"
  private val bsCarrier = "bsCarrier"
  private val netSuiteId = 14
  private val deviceId = "deviceId"
  private val kind = "kind"
  private val startDate = "2016-09-01"
  private val endDate = "2016-09-02"
  private val earliest = "2016-07-01"
  private val latest = "2016-10-10"

  test("updateDeviceDetails") {

    val singleDevice = new SingleDevice()
    val response = new UpdateDeviceResponse

    withMockServiceImplAndProducer{ (service, producer) =>
      when(producer.requestBody("direct:updateDeviceDetails", singleDevice)).thenReturn(response, Nil: _*)
      val answer = service.updateDeviceDetails(singleDevice)

      assert(answer === response)
    }
  }

  test("getDeviceInfoDB") {
    val captor = ArgumentCaptor.forClass(classOf[DeviceInformationRequest])

    withMockServiceImplAndProducer { (service, producer) =>

      val response = new DeviceInformationResponse
      when(producer.requestBody(same("direct:getDeviceInformationDB"), any(classOf[DeviceInformationRequest]))).thenReturn(response, Nil: _*)

      val answer = service.getDeviceInfoDB(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, netSuiteId)

      verify(producer, times(1)).requestBody(same("direct:getDeviceInformationDB"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assert(request.getDataArea != null)
      assert(request.getDataArea.getNetSuiteId === netSuiteId)
      assertHeader(request.getHeader)
    }
  }

  test("getDeviceInfoCarrier") {
    val captor = ArgumentCaptor.forClass(classOf[DeviceInformationRequest])

    withMockServiceImplAndProducer { (service, producer) =>

      val response = new DeviceInformationResponse
      when(producer.requestBody(same("direct:deviceInformationCarrier"), any(classOf[DeviceInformationRequest]))).thenReturn(response, Nil: _*)

      val answer = service.getDeviceInfoCarrier(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, netSuiteId, deviceId, kind)
      verify(producer, times(1)).requestBody(same("direct:deviceInformationCarrier"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assert(request.getDataArea != null)
      assert(request.getDataArea.getNetSuiteId === netSuiteId)
      assert(request.getDataArea.getDeviceId.getId === deviceId)
      assert(request.getDataArea.getDeviceId.getKind === kind)
      assertHeader(request.getHeader)
    }
  }

  test("updateDevicesDetailsBulk") {
    withMockServiceImplAndProducer { (service, producer) =>

      val bulkDevices = new BulkDevices
      val response = new BatchDeviceResponse

      when(producer.requestBody("direct:updateDevicesDetailsBulk", bulkDevices)).thenReturn(response, Nil: _*)

      val answer = service.updateDevicesDetailsBulk(bulkDevices)

      assert(answer === response)
    }
  }

  provisionTest("deactivateDevice", "direct:deactivateDevice", new DeactivateDeviceRequest, (s, ddr: DeactivateDeviceRequest) => s.deactivateDevice(ddr))

  provisionTest("activateDevice", "direct:activateDevice", new ActivateDeviceRequest, (s, adr: ActivateDeviceRequest) => s.activateDevice(adr))

  provisionTest("reactivateDevice", "direct:reactivateDevice", new ReactivateDeviceRequest, (s, rdr: ReactivateDeviceRequest) => s.reactivateDevice(rdr))

  provisionTest("suspendDevice", "direct:suspendDevice", new SuspendDeviceRequest, (s, sdr: SuspendDeviceRequest) => s.suspendDevice(sdr))

  provisionTest("customFieldsUpdateRequest", "direct:customeFields", new CustomFieldsDeviceRequest, (s, cfdr: CustomFieldsDeviceRequest) => s.customFieldsUpdateRequest(cfdr))

  provisionTest("restoreDevice", "direct:restoreDevice", new RestoreDeviceRequest, (s, rdr: RestoreDeviceRequest) => s.restoreDevice(rdr))

  provisionTest("changeDeviceServicePlans", "direct:changeDeviceServicePlans", new ChangeDeviceServicePlansRequest, (s, cdspr: ChangeDeviceServicePlansRequest) => s.changeDeviceServicePlans(cdspr))

  List(
    (IConstant.BSCARRIER_SERVICE_VERIZON, JobName.VERIZON_DEVICE_USAGE, CarrierType.VERIZON.toString)
    , (IConstant.BSCARRIER_SERVICE_KORE, JobName.KORE_DEVICE_USAGE, CarrierType.KORE.toString)
  ).foreach { case (carrierName, enum, _type) =>

    test(s"transactionFailureDeviceUsageJob - $carrierName") {
      withMockServiceImplAndProducer { (service, producer) =>
        val jobParameter = testJobParameter(carrierName)

        val jobDetailCaptor = ArgumentCaptor.forClass(classOf[JobDetail])

        val response = new JobinitializedResponse
        when(producer.requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))).thenReturn(response, Nil: _*)

        val answer = service.transactionFailureDeviceUsageJob(jobParameter)

        verify(producer, times(1)).asyncRequestBody(same("direct:startTransactionFailureJob"), any(classOf[JobDetail]))
        verify(producer, times(1)).requestBody(same("direct:jobResponse"), jobDetailCaptor.capture())

        assert(answer === response)
        assert(jobDetailCaptor.getValue != null)
        val jobDetail = jobDetailCaptor.getValue
        assert(jobDetail.getType === JobType.TRANSACTION_FAILURE)
        assert(jobDetail.getDate === jobParameter.getDate)
        assert(jobDetail.getName === enum)
        assert(jobDetail.getCarrierName === _type)
      }
    }

    test(s"reRunDeviceUsageJob - $carrierName") {
      withMockServiceImplAndProducer{ (service, producer) =>
        val jobParameter = testJobParameter(carrierName)

        val jobDetailCaptor = ArgumentCaptor.forClass(classOf[JobDetail])

        val response = new JobinitializedResponse
        when(producer.requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))).thenReturn(response, Nil: _*)

        val answer = service.reRunDeviceUsageJob(jobParameter)

        verify(producer, times(1)).asyncRequestBody(same("direct:startJob"), any(classOf[JobDetail]))
        verify(producer, times(1)).requestBody(same("direct:jobResponse"), jobDetailCaptor.capture())

        assert(answer === response)
        assert(jobDetailCaptor.getValue != null)
        val jobDetail = jobDetailCaptor.getValue
        assert(jobDetail.getType === JobType.RERUN)
        assert(jobDetail.getDate === jobParameter.getDate)
        assert(jobDetail.getName === enum)
        assert(jobDetail.getCarrierName === _type)
      }
    }
  }

  //ATT is not currently supported by these calls
  List(
    IConstant.BSCARRIER_SERVICE_ATTJASPER
  ).foreach { carrierName =>

    test(s"transactionFailureDeviceUsageJob - $carrierName") {
      withMockServiceImplAndProducer{ (service, producer) =>
        val response = service.transactionFailureDeviceUsageJob(testJobParameter(carrierName))
        assert(response.getMessage != null)
        verify(producer, times(0)).requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))
      }
    }

    test(s"reRunDeviceUsageJob - $carrierName") {
      withMockServiceImplAndProducer{ (service, producer) =>
        val response = service.reRunDeviceUsageJob(testJobParameter(carrierName))
        assert(response.getMessage != null)
        verify(producer, times(0)).requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))
      }
    }
  }

  List(
    (IConstant.BSCARRIER_SERVICE_VERIZON, JobName.VERIZON_CONNECTION_HISTORY, CarrierType.VERIZON.toString)
  ).foreach { case (carrierName, jobName, carrierType) =>
    test(s"transactionFailureConnectionHistoryJob - $carrierName") {
      withMockServiceImplAndProducer{ (service, producer) =>
        val jobParameter = testJobParameter(carrierName)

        val jobDetailCaptor = ArgumentCaptor.forClass(classOf[JobDetail])

        val response = new JobinitializedResponse
        when(producer.requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))).thenReturn(response, Nil: _*)

        val answer = service.transactionFailureConnectionHistoryJob(jobParameter)

        verify(producer, times(1)).asyncRequestBody(same("direct:startTransactionFailureJob"), any(classOf[JobDetail]))
        verify(producer, times(1)).requestBody(same("direct:jobResponse"), jobDetailCaptor.capture())

        assert(answer === response)
        assert(jobDetailCaptor.getValue != null)
        val jobDetail = jobDetailCaptor.getValue
        assert(jobDetail.getType === JobType.TRANSACTION_FAILURE)
        assert(jobDetail.getDate === jobParameter.getDate)
        assert(jobDetail.getName === jobName)
        assert(jobDetail.getCarrierName === carrierType)
      }
    }
  }

  //These two aren't supported for these calls
  List(
    IConstant.BSCARRIER_SERVICE_ATTJASPER
    , IConstant.BSCARRIER_SERVICE_KORE
  ).foreach { carrierName =>

    test(s"transactionFailureConnectionHistoryJob - $carrierName") {
      withMockServiceImplAndProducer { (service, producer) =>
        val jobParameter = testJobParameter(carrierName)

        val response = service.transactionFailureConnectionHistoryJob(jobParameter)
        assert(response.getMessage != null)
        verify(producer, times(0)).asyncRequestBody(same("direct:startTransactionFailureJob"), any(classOf[JobDetail]))
        verify(producer, times(0)).requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))
      }
    }

    test(s"reRunConnectionHistoryJob - $carrierName") {
      withMockServiceImplAndProducer{ (service, producer) =>
        val response = service.reRunConnectionHistoryJob(testJobParameter(carrierName))
        assert(response.getMessage != null)
        verify(producer, times(0)).asyncRequestBody(same("direct:jobResponse"), any(classOf[JobDetail]))
        verify(producer, times(0)).requestBody(same("direct:jobResponse"), any(classOf[JobDetail]))
      }
    }
  }

  test("getDeviceUsageInfoDB") {
    withMockServiceImplAndProducer { (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[UsageInformationMidwayRequest])

      val response = mock[UsageInformationMidwayResponse]
      when(producer.requestBody(same("direct:getDeviceUsageInfoDB"), any[UsageInformationMidwayRequest])).thenReturn(response, Nil: _*)

      val answer =
        service.getDeviceUsageInfoDB(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, netSuiteId, startDate, endDate)

      verify(producer, times(1)).requestBody(same("direct:getDeviceUsageInfoDB"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assertHeader(request.getHeader)
      val dataArea = request.getUsageInformationRequestMidwayDataArea
      assert(dataArea != null)
      assert(dataArea.getNetSuiteId === netSuiteId)
      assert(dataArea.getStartDate === startDate)
      assert(dataArea.getEndDate === endDate)
    }
  }

  test("getDeviceConnectionHistoryInfoDB") {
    withMockServiceImplAndProducer { (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[ConnectionInformationMidwayRequest])

      val response = mock[ConnectionInformationMidwayResponse]
      when(producer.requestBody(same("direct:getDeviceConnectionHistoryInfoDB"), any[ConnectionInformationMidwayResponse])).thenReturn(response, Nil: _*)

      val answer =
        service.getDeviceConnectionHistoryInfoDB(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, netSuiteId, startDate, endDate)

      verify(producer, times(1)).requestBody(same("direct:getDeviceConnectionHistoryInfoDB"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assertHeader(request.getHeader)
      val dataArea = request.getDataArea
      assert(dataArea != null)
      assert(dataArea.getNetSuiteId === netSuiteId)
      assert(dataArea.getStartDate === startDate)
      assert(dataArea.getEndDate === endDate)
    }
  }

  test("deviceConnectionStatusRequest") {
    withMockServiceImplAndProducer { (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[ConnectionInformationRequest])

      val response = mock[ConnectionStatusResponse]
      when(producer.requestBody(same("direct:deviceConnectionStatus"), any[ConnectionInformationRequest])).thenReturn(response, Nil: _*)

      val answer =
        service.deviceConnectionStatusRequest(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, deviceId, kind, earliest, latest)

      verify(producer, times(1)).requestBody(same("direct:deviceConnectionStatus"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assertHeader(request.getHeader)
      val dataArea = request.getDataArea
      assert(dataArea != null)
      val deviceIdObj = dataArea.getDeviceId
      assert(deviceIdObj != null)
      assert(deviceIdObj.getId === deviceId)
      assert(deviceIdObj.getKind === kind)
      assert(dataArea.getEarliest === earliest)
      assert(dataArea.getLatest === latest)
    }
  }

  test("retrieveDeviceUsageHistoryCarrier") {
    withMockServiceImplAndProducer{ (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[UsageInformationRequest])

      val response = mock[UsageInformationResponse]
      when(producer.requestBody(same("direct:retrieveDeviceUsageHistoryCarrier"), any[UsageInformationRequest])).thenReturn(response, Nil: _*)

      val answer =
        service.retrieveDeviceUsageHistoryCarrier(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, deviceId, kind, earliest, latest)

      verify(producer, times(1)).requestBody(same("direct:retrieveDeviceUsageHistoryCarrier"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assertHeader(request.getHeader)
      val dataArea = request.getDataArea
      assert(dataArea != null)
      val deviceIdObj = dataArea.getDeviceId
      assert(deviceIdObj != null)
      assert(deviceIdObj.getId === deviceId)
      assert(deviceIdObj.getKind === kind)
      assert(dataArea.getEarliest === earliest)
      assert(dataArea.getLatest === latest)
    }
  }

  test("deviceSessionBeginEndResponse") {
    withMockServiceImplAndProducer { (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[ConnectionInformationRequest])

      val response = mock[SessionBeginEndResponse]
      when(producer.requestBody(same("direct:deviceSessionBeginEndInfo"), any[ConnectionInformationRequest])).thenReturn(response, Nil: _*)

      val answer =
        service.deviceSessionBeginEndResponse(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, deviceId, kind, earliest, latest)

      verify(producer, times(1)).requestBody(same("direct:deviceSessionBeginEndInfo"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assert(request != null)
      assertHeader(request.getHeader)
      val dataArea = request.getDataArea
      assert(dataArea != null)
      val deviceIdObj = dataArea.getDeviceId
      assert(deviceIdObj.getId === deviceId)
      assert(deviceIdObj.getKind === kind)
      assert(dataArea.getEarliest === earliest)
      assert(dataArea.getLatest === latest)
    }
  }

  test("getDevicesUsageByDayAndCarrierInfoDB") {
    withMockServiceImplAndProducer{ (service, producer) =>

      val captor = ArgumentCaptor.forClass(classOf[DevicesUsageByDayAndCarrierRequest])

      val response = mock[DevicesUsageByDayAndCarrierResponse]
      when(producer.requestBody(same("direct:getDevicesUsageByDayAndCarrierInfoDB"), any[DevicesUsageByDayAndCarrierResponse])).thenReturn(response, Nil: _*)

      val answer =
        service.getDevicesUsageByDayAndCarrierInfoDB(region, timestamp, organization, transactionId, sourceName, applicationName, bsCarrier, startDate)

      verify(producer, times(1)).requestBody(same("direct:getDevicesUsageByDayAndCarrierInfoDB"), captor.capture())

      assert(answer === response)
      val request = captor.getValue
      assertHeader(request.getHeader)
      val dataArea = request.getDateArea
      assert(dataArea != null)
      assert(dataArea.getDate === startDate)
    }
  }

  private def provisionTest[A](name: String, endpoint: String, param: A, f: (AdaptationLayerServiceImpl, A) => CarrierProvisioningDeviceResponse): Unit = {
    test(name) {
      withMockServiceImplAndProducer { (service, producer) =>
        val response = new CarrierProvisioningDeviceResponse

        when(producer.requestBody(endpoint, param)).thenReturn(response, Nil: _*)

        val answer = f(service, param)
        assert(answer === response)
      }
    }
  }

  private def withMockServiceImplAndProducer(f: (AdaptationLayerServiceImpl, ProducerTemplate) => Unit): Unit = {
    val producerTemplate = mock[ProducerTemplate]
    val service = new AdaptationLayerServiceImpl()
    service.producer = producerTemplate

    f(service, producerTemplate)
  }

  private def assertHeader(header: Header): Unit = {
    assert(header != null)
    assert(header.getRegion === region)
    assert(header.getTimestamp === timestamp)
    assert(header.getOrganization === organization)
    assert(header.getTransactionId === transactionId)
    assert(header.getSourceName === sourceName)
    assert(header.getApplicationName === applicationName)
    assert(header.getBsCarrier === bsCarrier)
  }

  private def testJobParameter(carrierName: String): JobParameter = {
    val jp = new JobParameter
    jp.setCarrierName(carrierName)
    jp.setDate("2016-10-10")
    jp
  }
}