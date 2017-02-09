package com.gv.midway.service.impl

import java.util.{ArrayList => JArrayList, Arrays => JArrays, List => JList}

import com.gv.midway.TestMocks
import com.gv.midway.constant.{IConstant, JobName, JobType}
import com.gv.midway.dao.IJobDao
import com.gv.midway.pojo.deviceInformation.response.DeviceInformation
import com.gv.midway.pojo.job.JobDetail
import com.gv.midway.pojo.notification.DeviceOverageNotification
import com.gv.midway.pojo.usageInformation.response.DevicesUsageByDayAndCarrier
import org.apache.camel.ProducerTemplate
import org.mockito.ArgumentCaptor
import org.mockito.Mockito._
import org.mockito.Matchers._

import collection.JavaConversions._

class TestJobServiceImpl extends TestMocks {

  test("fetchDevices - 1") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        val devices = JArrays.asList(new DeviceInformation)
        when(dao.fetchDevices(exchange)).thenReturn(devices)
        val returnedDevices = service.fetchDevices(exchange)
        verify(exchange, times(1)).setProperty(IConstant.JOB_TOTAL_COUNT, 1)
        assert(returnedDevices === devices)
      }
    }
  }

  test("fetchDevices - null") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        when(dao.fetchDevices(exchange)).thenReturn(null)
        val returnedDevices = service.fetchDevices(exchange)
        verify(exchange, times(1)).setProperty(IConstant.JOB_TOTAL_COUNT, 0)
        assert(returnedDevices === null)
      }
    }
  }

  test("insertJobDetails") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.insertJobDetails(exchange)
        verify(dao, times(1)).insertJobDetails(exchange)
      }
    }
  }

  test("updateJobDetails") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.updateJobDetails(exchange)
        verify(dao, times(1)).updateJobDetails(exchange)
      }
    }
  }

  test("setJobDetails") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        val carrierName = "carrierName"
        val jobName = JobName.ATTJASPER_DEVICE_USAGE
        val duration = 14
        val jobType = JobType.TRANSACTION_FAILURE
        val period = "period"

        val captor = ArgumentCaptor.forClass(classOf[JobDetail])
        service.setJobDetails(exchange, carrierName, jobName, duration, jobType, period)

        verify(message, times(1)).setBody(captor.capture())
        val jobDetail = captor.getValue
        assert(jobDetail.getType === jobType)
        assert(jobDetail.getCarrierName === carrierName)
        assert(jobDetail.getName === jobName)
        assert(jobDetail.getPeriod === period)
        assert(jobDetail.getDate != null)
      }
    }
  }

  List(
    (JobType.NEW, 0)
    , (JobType.RERUN, 1)
    , (JobType.TRANSACTION_FAILURE, 0)
  ).foreach { case (jobType, count) =>

    test(s"deleteDeviceUsageRecords - $jobType") {
      withMockExchangeAndMessage { (exchange, message) =>
        withServiceAndMockDAO { (service, dao) =>
          val jobDetail = new JobDetail
          jobDetail.setType(jobType)
          when(exchange.getProperty(IConstant.JOB_DETAIL)).thenReturn(jobDetail, Nil: _*)
          service.deleteDeviceUsageRecords(exchange)
          verify(dao, times(count)).deleteDeviceUsageRecords(exchange)
        }
      }
    }

    test(s"deleteDeviceConnectionHistoryRecords - $jobType") {
      withMockExchangeAndMessage { (exchange, message) =>
        withServiceAndMockDAO { (service, dao) =>
          val jobDetail = new JobDetail
          jobDetail.setType(jobType)
          when(exchange.getProperty(IConstant.JOB_DETAIL)).thenReturn(jobDetail, Nil: _*)
          service.deleteDeviceConnectionHistoryRecords(exchange)
          verify(dao, times(count)).deleteDeviceConnectionHistoryRecords(exchange)
        }
      }
    }
  }

  test("setJobStartandEndTime") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        val jobDetail = new JobDetail
        val startDay = 21
        val startDate = s"2016-11-$startDay"
        jobDetail.setDate(startDate)

        when(message.getBody).thenReturn(jobDetail, Nil: _*)

        service.setJobStartandEndTime(exchange)

        val startCaptor = ArgumentCaptor.forClass(classOf[String])
        val endCaptor = ArgumentCaptor.forClass(classOf[String])
        verify(exchange, times(1)).setProperty(same(IConstant.JOB_START_TIME), startCaptor.capture())
        verify(exchange, times(1)).setProperty(same(IConstant.JOB_END_TIME), endCaptor.capture())
        val startTime = startCaptor.getValue
        val endTime = endCaptor.getValue

        assert(startTime === s"${startDate}T00:00:01Z")
        assert(endTime === s"${startDate.replaceFirst(startDay.toString, (startDay + 1).toString)}T00:00:00Z")
      }
    }
  }

  //TODO Unable to figure out how to mock out a java List that has no generic type specified. (e.g List)
//  test("fetchTransactionFailureDevices") {
//    withMockExchangeAndMessage { (exchange, message) =>
//      withServiceAndMockDAO { (service, dao) =>
//        val mockList = mock[java.util.List[Nothing]]
//        when(dao.fetchTransactionFailureDevices(exchange)).thenReturn(mockList, Nil: _*)
//        val list = service.fetchTransactionFailureDevices(exchange)
//        verify(exchange, times(1)).setProperty(IConstant.JOB_TOTAL_COUNT, 1)
//        assert(list === mockList)
//      }
//    }
//  }

  test("deleteTransactionFailureDeviceUsageRecords") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.deleteTransactionFailureDeviceUsageRecords(exchange)
        verify(dao, times(1)).deleteTransactionFailureDeviceUsageRecords(exchange)
      }
    }
  }

  test("deleteTransactionFailureDeviceConnectionHistoryRecords") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.deleteTransactionFailureDeviceConnectionHistoryRecords(exchange)
        verify(dao, times(1)).deleteTransactionFailureDeviceConnectionHistoryRecords(exchange)
      }
    }
  }

  test("processDeviceNotification") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.processDeviceNotification(exchange)
        verify(dao, times(1)).processDeviceNotification(exchange)
      }
    }
  }

  test("addNotificationList") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.addNotificationList(exchange)
        verify(exchange, times(1)).setProperty(same("NotificationLsit"), any(classOf[JArrayList[DeviceOverageNotification]]))
      }
    }
  }

  test("checkNotificationList") {
    pending
    //TODO This function makes no sense
  }

  test("scheduleJob") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAOAndTemplate { (service, _, template) =>
        val jobDetail = new JobDetail
        when(message.getBody).thenReturn(jobDetail, Nil: _*)
        service.scheduleJob(exchange)
        verify(template, times(1)).requestBody("direct:startJob", jobDetail)
      }
    }
  }

  test("updateDeviceUsageView") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.updateDeviceUsageView(exchange)
        verify(dao, times(1)).updateDeviceUsageView(exchange)
      }
    }
  }

  test("fetchDeviceUsageView") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        val mockList = mock[JList[DevicesUsageByDayAndCarrier]]
        when(dao.fetchDeviceUsageView(exchange)).thenReturn(mockList)
        val list = service.fetchDeviceUsageView(exchange)
        verify(dao, times(1)).fetchDeviceUsageView(exchange)
        assert(list === mockList)
      }
    }
  }

//  test("fetchPreviousDeviceUsageData") {
//    withMockExchangeAndMessage { (exchange, message) =>
//      withServiceAndMockDAO { (service, dao) =>
//
//        def usage = {
//          val du = new DeviceUsage
//          du.setNetSuiteId(1)
//          du.setMonthToDateUsage(2)
//          du
//        }
//
//        val list: JList[_] = {
//          val arrayList = new JArrayList[DeviceUsage]
//          List.fill(3)(usage).foreach(arrayList.add)
//          arrayList
//        }
//        TODO The problem is that dao.fetchPreviousDeviceUsageDataUsed returns just a list, no type specified
//        when(dao.fetchPreviousDeviceUsageDataUsed(exchange)).thenReturn(list, Nil: _*)
//
//        val captor = ArgumentCaptor.forClass(classOf[JMap[Integer, Long]])
//        service.fetchPreviousDeviceUsageData(exchange)
//
//        verify(exchange, times(1)).setProperty(same("saveLastUpdatedDataUsed"), captor.capture())
//        val map: Map[Integer, Long] = captor.getValue.asScala.toMap
//        map.foreach{ case (k, v) =>
//            assert(k.toInt === v)
//        }
//      }
//    }
//  }

  test("checkKoreJobScheduling") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        val duration = 14

        service.checkKoreJobScheduling(exchange, duration)
        verify(exchange, times(1)).setProperty(IConstant.ISKOREJOB_SCHEDULING, true)
      }
    }
  }

  test("updateNetSuiteCallBackResponse") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.updateNetSuiteCallBackResponse(exchange)
        verify(dao, times(1)).updateNetSuiteCallBackResponse(exchange)
      }
    }
  }

  test("updateNetSuiteCallBackError") {
    withMockExchangeAndMessage { (exchange, message) =>
      withServiceAndMockDAO { (service, dao) =>
        service.updateNetSuiteCallBackError(exchange)
        verify(dao, times(1)).updateNetSuiteCallBackError(exchange)
      }
    }
  }

  List(
    ("DEVICE_USAGE", 0)
    , ("DEVICE_USAGE", 1)
    , ("DeviceUsageJob", 0)
    , ("DeviceUsageJob", 1)
  ).foreach { case (jobName, count) =>
      testCheckTimeOutDevices(jobName, count)

      testCheckTimeOutDevicesTransactionFailure(jobName, count)
  }

  private def testCheckTimeOutDevices(jobName: String, count: Int): Unit = {
    test(s"checkTimeOutDevices - $jobName - $count") {
      withMockExchangeAndMessage { (exchange, message) =>
        withServiceAndMockDAO { (service, dao) =>

          val list: JList[DeviceInformation] = List.fill(count){new DeviceInformation}
          when(exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST)).thenReturn(list, Nil: _*)
          when(exchange.getProperty(IConstant.JOB_NAME)).thenReturn(jobName, Nil: _*)

          service.checkTimeOutDevices(exchange)

          if (count > 0) {
            if (jobName.endsWith("DEVICE_USAGE")) {
              verify(dao, times(1)).insertTimeOutUsageRecords(exchange)
              verify(dao, times(0)).insertTimeOutConnectionRecords(exchange)
            } else {
              verify(dao, times(0)).insertTimeOutUsageRecords(exchange)
              verify(dao, times(1)).insertTimeOutConnectionRecords(exchange)
            }
          } else {
            verifyZeroInteractions(dao)
          }
        }
      }
    }
  }

  private def testCheckTimeOutDevicesTransactionFailure(jobName: String, count: Int): Unit = {
    test(s"checkTimeOutDevicesTransactionFailure - $jobName - $count") {
      withMockExchangeAndMessage { (exchange, message) =>
        withServiceAndMockDAO { (service, dao) =>

          val list: JList[Object] = List.fill(count){new Object}
          when(exchange.getProperty(IConstant.TIMEOUT_DEVICE_LIST)).thenReturn(list, Nil: _*)
          when(exchange.getProperty(IConstant.JOB_NAME)).thenReturn(jobName, Nil: _*)

          service.checkTimeOutDevicesTransactionFailure(exchange)

          if (count > 0) {
            if (jobName.endsWith("DeviceUsageJob")) {
              verify(dao, times(1)).insertTimeOutUsageRecordsTransactionFailure(exchange)
              verify(dao, times(0)).insertTimeOutConnectionRecordsTransactionFailure(exchange)
            } else {
              verify(dao, times(0)).insertTimeOutUsageRecordsTransactionFailure(exchange)
              verify(dao, times(1)).insertTimeOutConnectionRecordsTransactionFailure(exchange)
            }
          } else {
            verifyZeroInteractions(dao)
          }
        }
      }
    }
  }

  private def withServiceAndMockDAO(f: (JobServiceImpl, IJobDao) => Unit): Unit = {
    val dao = mock[IJobDao]
    val service = new JobServiceImpl
    service.setiJobDao(dao)

    f(service, dao)
  }

  private def withServiceAndMockDAOAndTemplate(f: (JobServiceImpl, IJobDao, ProducerTemplate) => Unit): Unit = {
    val dao = mock[IJobDao]
    val template = mock[ProducerTemplate]
    val service = new JobServiceImpl
    service.setiJobDao(dao)
    service.producer = template

    f(service, dao, template)
  }
}