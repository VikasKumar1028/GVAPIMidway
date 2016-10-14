package com.gv.midway.processor

import java.util.{List => JList}

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceUsage}
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import org.apache.camel.Endpoint
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

class TestTimeOutErrorProcessor extends TestMocks {

  List(
    TestCase("Endpoint[direct://startJob]", newDeviceList, new DeviceInformation, classOf[JList[DeviceInformation]], "")
    , TestCase("Endpoint[direct://startTransactionFailureJob]", newDeviceUsageList, new DeviceUsage, classOf[JList[DeviceUsage]], "blah blah DeviceUsageJob")
    , TestCase("Endpoint[direct://startTransactionFailureJob]", newDeviceConnList, new DeviceConnection, classOf[JList[DeviceConnection]], "blah blah DeviceUsageJob blah")
  ).foreach { case TestCase(name, list, single, clazz, jobName) =>

    test(s"$name - $jobName") {
      withMockExchangeAndMessage{ (exchange, message) =>

        val endpoint = mock[Endpoint]

        when(endpoint.toString).thenReturn(name, Nil: _*)

        when(message.getBody).thenReturn(single, Nil: _*)

        when(exchange.getFromEndpoint).thenReturn(endpoint, Nil: _*)
        when(exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST)).thenReturn(list, Nil: _*)
        when(exchange.getProperty("jobName")).thenReturn(jobName, Nil: _*)

        val captor = ArgumentCaptor.forClass(clazz)
        new TimeOutErrorProcessor().process(exchange)

        verify(exchange, times(1)).setProperty(same(IConstant.TIMEOUT_DEVICE_LIST), captor.capture())

        val returnedList = captor.getValue
        assert(returnedList.size() === 2)
      }
    }
  }

  private def newDeviceList: JList[DeviceInformation] = {
    val list = new java.util.ArrayList[DeviceInformation]()
    list.add(new DeviceInformation)
    list
  }

  private def newDeviceUsageList: JList[DeviceUsage] = {
    val list = new java.util.ArrayList[DeviceUsage]()
    list.add(new DeviceUsage)
    list
  }

  private def newDeviceConnList: JList[DeviceConnection] = {
    val list = new java.util.ArrayList[DeviceConnection]()
    list.add(new DeviceConnection)
    list
  }

  private case class TestCase[A](name: String, list: java.util.List[A], single: A, clazz: Class[JList[A]], jobName: String)
}