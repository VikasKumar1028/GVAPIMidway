package com.gv.midway.service.impl

import com.gv.midway.TestMocks
import com.gv.midway.dao.ISchedulerDao
import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceUsage}
import org.mockito.Mockito._

class TestSchedulerServiceImpl extends TestMocks {

  test("saveDeviceConnectionHistory") {
    withMockExchangeAndMessage { (exchange, message) =>

      val connection = new DeviceConnection
      when(message.getBody).thenReturn(connection, Nil: _*)

      val dao = mock[ISchedulerDao]
      val service = new SchedulerServiceImpl
      service.schedulerDao = dao

      service.saveDeviceConnectionHistory(exchange)

      verify(dao, times(1)).saveDeviceConnectionHistory(connection)
    }
  }

  test("saveDeviceUsageHistory") {
    withMockExchangeAndMessage { (exchange, message) =>

      val usage = new DeviceUsage
      when(message.getBody).thenReturn(usage, Nil: _*)

      val dao = mock[ISchedulerDao]
      val service = new SchedulerServiceImpl
      service.schedulerDao = dao

      service.saveDeviceUsageHistory(exchange)

      verify(dao, times(1)).saveDeviceUsageHistory(usage)
    }
  }
}