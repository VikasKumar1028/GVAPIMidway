package com.gv.midway.processor

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, IResponse}
import com.gv.midway.exception.MissingParameterException
import com.gv.midway.pojo.changeDeviceServicePlans.request.{ChangeDeviceServicePlansRequest, ChangeDeviceServicePlansRequestDataArea}
import org.mockito.Mockito._

class TestChangeDeviceServicePlanValidatorProcessor extends TestMocks {
  test("TestChangeDeviceServicePlanValidatorProcessor.process success") {

    val dataArea = new ChangeDeviceServicePlansRequestDataArea
    dataArea.setCurrentServicePlan("current service plan")
    dataArea.setServicePlan("service plan")
    val request = new ChangeDeviceServicePlansRequest
    request.setDataArea(dataArea)

    withMockExchangeAndMessage{ (exchange, message) =>
      when(message.getBody(classOf[ChangeDeviceServicePlansRequest])).thenReturn(request, Nil: _*)

      new ChangeDeviceServicePlanValidatorProcessor().process(exchange)
    }
  }

  testFail("missing both", None, None)

  testFail("missing plan", None, Some("some plan!"))

  testFail("missing current", Some("some current plan"), None)

  private def testFail(name: String, currentPlan: Option[String], plan: Option[String]): Unit = {
    test(name) {

      val dataArea: ChangeDeviceServicePlansRequestDataArea = (currentPlan, plan) match {
        case (None, None) => null
        case _ =>
          val da = new ChangeDeviceServicePlansRequestDataArea
          currentPlan.foreach(da.setCurrentServicePlan)
          plan.foreach(da.setServicePlan)
          da
      }

      val request = new ChangeDeviceServicePlansRequest
      request.setDataArea(dataArea)

      withMockExchangeAndMessage{ (exchange, message) =>
        when(message.getBody(classOf[ChangeDeviceServicePlansRequest])).thenReturn(request, Nil: _*)

        assertThrows[MissingParameterException] {
          new ChangeDeviceServicePlanValidatorProcessor().process(exchange)
        }
        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_CODE, IResponse.INVALID_PAYLOAD)
        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_STATUS, IResponse.ERROR_DESCRIPTION_CHANGE_SERVICE_PLAN)
        verify(exchange, times(1)).setProperty(IConstant.RESPONSE_DESCRIPTION, IResponse.ERROR_MESSAGE)
      }
    }
  }
}