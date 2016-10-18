package com.gv.midway.processor.jobScheduler

import com.gv.midway.TestMocks
import com.gv.midway.constant.IResponse
import com.gv.midway.pojo.job.JobinitializedResponse
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._

class TestJobInitializedPostProcessor extends TestMocks {

  test("happy flow") {
    withMockExchangeAndMessage { (exchange, message) =>

      val captor = ArgumentCaptor.forClass(classOf[JobinitializedResponse])
      new JobInitializedPostProcessor().process(exchange)

      verify(message, times(1)).setBody(captor.capture())

      val response = captor.getValue
      assert(response.getMessage === IResponse.SUCCESS_DESCRIPTION_JOB_INITIALIZED)
    }
  }
}