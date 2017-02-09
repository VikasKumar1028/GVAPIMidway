package com.gv.midway.service.impl

import com.gv.midway.TestMocks
import com.gv.midway.constant.IConstant
import com.gv.midway.dao.ITransactionalDao
import com.gv.midway.pojo.transaction.Transaction
import org.apache.camel.Exchange
import org.mockito.Mockito._

class TestTransactionalServiceImpl extends TestMocks {

  type ServiceCall = TransactionalServiceImpl => Exchange => Unit
  type DAOCall = ITransactionalDao => Exchange => Unit

  testIt("populateActivateDBPayload", _.populateActivateDBPayload, _.populateActivateDBPayload)

  testIt("populateDeactivateDBPayload", _.populateDeactivateDBPayload, _.populateDeactivateDBPayload)

  testIt("populateSuspendDBPayload", _.populateSuspendDBPayload, _.populateSuspendDBPayload)

  testIt("populateVerizonTransactionalResponse", _.populateVerizonTransactionalResponse, _.populateVerizonTransactionalResponse)

  testIt("populateVerizonTransactionalErrorResponse", _.populateVerizonTransactionalErrorResponse, _.populateVerizonTransactionalErrorResponse)

  testIt("populateKoreTransactionalErrorResponse", _.populateKoreTransactionalErrorResponse, _.populateKoreTransactionalErrorResponse)

  testIt("populateKoreTransactionalResponse", _.populateKoreTransactionalResponse, _.populateKoreTransactionalResponse)

  testIt("populatePendingKoreCheckStatus", _.populatePendingKoreCheckStatus, _.populatePendingKoreCheckStatus)

  test("populateConnectionErrorResponse") {
    withMockExchangeAndMessage { (exchange, message) =>
      val dao = mock[ITransactionalDao]
      val service = new TransactionalServiceImpl(dao)
      val errorType = "errorType"

      service.populateConnectionErrorResponse(exchange, errorType)
      verify(dao, times(1)).populateConnectionErrorResponse(exchange, errorType)
    }
  }

  testIt("populateCallbackDBPayload", _.populateCallbackDBPayload, _.populateCallbackDBPayload)

  testIt("findMidwayTransactionId", _.findMidwayTransactionId, _.findMidwayTransactionId)

  testIt("populateReactivateDBPayload", _.populateReactivateDBPayload, _.populateReactivateDBPayload)

  testIt("populateRestoreDBPayload", _.populateRestoreDBPayload, _.populateRestoreDBPayload)

  testIt("populateCustomeFieldsDBPayload", _.populateCustomeFieldsDBPayload, _.populateCustomeFieldsDBPayload)

  testIt("populateChangeDeviceServicePlansDBPayload", _.populateChangeDeviceServicePlansDBPayload, _.populateChangeDeviceServicePlansDBPayload)

  testIt("populateKoreCheckStatusResponse", _.populateKoreCheckStatusResponse, _.populateKoreCheckStatusResponse)

  testIt("populateKoreCheckStatusConnectionResponse", _.populateKoreCheckStatusConnectionResponse, _.populateKoreCheckStatusConnectionResponse)

  testIt("populateKoreCheckStatusErrorResponse", _.populateKoreCheckStatusErrorResponse, _.populateKoreCheckStatusErrorResponse)

  testIt("updateNetSuiteCallBackResponse", _.updateNetSuiteCallBackResponse, _.updateNetSuiteCallBackResponse)

  testIt("updateNetSuiteCallBackError", _.updateNetSuiteCallBackError, _.updateNetSuiteCallBackError)

  testIt("updateNetSuiteCallBackRequest", _.updateNetSuiteCallBackRequest, _.updateNetSuiteCallBackRequest)

  testIt("populateKoreCustomChangeResponse", _.populateKoreCustomChangeResponse, _.populateKoreCustomChangeResponse)

  testIt("populateATTJasperTransactionalResponse", _.populateATTJasperTransactionalResponse, _.populateATTJasperTransactionalResponse)

  testIt("populateATTJasperTransactionalErrorResponse", _.populateATTJasperTransactionalErrorResponse, _.populateATTJasperTransactionalErrorResponse)

  testIt("populateATTCustomeFieldsDBPayload", _.populateATTCustomeFieldsDBPayload, _.populateATTCustomeFieldsDBPayload)

  testIt("updateKoreActivationCustomeFieldsDBPayloadError", _.updateKoreActivationCustomeFieldsDBPayloadError, _.updateKoreActivationCustomeFieldsDBPayloadError)

  testIt("updateKoreActivationCustomeFieldsDBPayload", _.updateKoreActivationCustomeFieldsDBPayload, _.updateKoreActivationCustomeFieldsDBPayload)

  test("setActivateCustomFieldListInExchange") {
    withMockExchangeAndMessage { (exchange, message) =>
      val list: java.util.List[Transaction] = java.util.Arrays.asList(mock[Transaction])
      when(exchange.getProperty(IConstant.ATT_ACTIVATION_WITH_CUSTOMFIELDS_LIST)).thenReturn(list, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME)).thenReturn(IConstant.BSCARRIER_SERVICE_KORE, Nil: _*)
      val dao = mock[ITransactionalDao]
      val service = new TransactionalServiceImpl(dao)

      service.setActivateCustomFieldListInExchange(exchange)
      verify(exchange, times(1)).getProperty(IConstant.ATT_ACTIVATION_WITH_CUSTOMFIELDS_LIST)
    }
  }

  test("setActivateServicePlanListInExchange") {
    withMockExchangeAndMessage { (exchange, message) =>
      val list: java.util.List[Transaction] = java.util.Arrays.asList(mock[Transaction])
      when(exchange.getProperty(IConstant.ATT_ACTIVATION_WITH_SERVICEPLAN_LIST)).thenReturn(list, Nil: _*)
      when(exchange.getProperty(IConstant.MIDWAY_DERIVED_CARRIER_NAME)).thenReturn(IConstant.BSCARRIER_SERVICE_KORE, Nil: _*)
      val dao = mock[ITransactionalDao]
      val service = new TransactionalServiceImpl(dao)

      service.setActivateServicePlanListInExchange(exchange)
      verify(exchange, times(1)).getProperty(IConstant.ATT_ACTIVATION_WITH_SERVICEPLAN_LIST)
    }
  }

  testIt("fetchAttPendingCallback", _.fetchAttPendingCallback, _.fetchAttPendingCallback)

  testIt("updateCallBackStatusOfSecondaryField", _.updateCallBackStatusOfSecondaryField, _.updateCallBackStatusOfSecondaryField)

  testIt("updateAttNetSuiteCallBackError", _.updateAttNetSuiteCallBackError, _.updateAttNetSuiteCallBackError)

  testIt("updateAttNetSuiteCallBackRequest", _.updateAttNetSuiteCallBackRequest, _.updateAttNetSuiteCallBackRequest)

  testIt("updateAttNetSuiteCallBackResponse", _.updateAttNetSuiteCallBackResponse, _.updateAttNetSuiteCallBackResponse)

  private def testIt(name: String, s: ServiceCall, d: DAOCall): Unit = {
    test(name) {
      withMockExchangeAndMessage { (exchange, message) =>
        val dao = mock[ITransactionalDao]
        val service = new TransactionalServiceImpl(dao)

        s(service)(exchange)
        d(verify(dao, times(1)))(exchange)
      }
    }
  }
}