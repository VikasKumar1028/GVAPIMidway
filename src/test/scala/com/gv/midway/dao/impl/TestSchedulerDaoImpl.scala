package com.gv.midway.dao.impl

import com.gv.midway.pojo.deviceHistory.{DeviceConnection, DeviceUsage}
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.data.mongodb.core.MongoTemplate

class TestSchedulerDaoImpl extends FunSuite with MockitoSugar {

  test("saveDeviceConnectionHistory") {
    withDao { (template, dao) =>
      val deviceConnection = mock[DeviceConnection]
      dao.saveDeviceConnectionHistory(deviceConnection)
      verify(template, times(1)).insert(deviceConnection)
    }
  }

  test("saveDeviceUsageHistory") {
    withDao { (template, dao) =>
      val usage = mock[DeviceUsage]
      dao.saveDeviceUsageHistory(usage)
      verify(template, times(1)).insert(usage)
    }
  }

  private def withDao(f: (MongoTemplate, SchedulerDaoImpl) => Unit): Unit = {
    val template = mock[MongoTemplate]

    val dao = new SchedulerDaoImpl()
    dao.mongoTemplate = template

    f(template, dao)
  }
}