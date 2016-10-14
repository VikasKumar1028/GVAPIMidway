package com.gv.midway

import com.gv.midway.constant.IConstant
import org.apache.camel.{Exchange, Message}
import org.mockito.Mockito._
import org.scalatest.FunSuiteLike
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

trait TestMocks extends FunSuiteLike with MockitoSugar {

  protected val OPT_VERIZON = Some(IConstant.BSCARRIER_SERVICE_VERIZON)
  protected val OPT_KORE = Some(IConstant.BSCARRIER_SERVICE_KORE)
  protected val OPT_ATT = Some(IConstant.BSCARRIER_SERVICE_ATTJASPER)

  protected def withMockExchange(f: Exchange => Unit): Unit = withMockExchange(None)(f)

  protected def withMockExchange(carrier: Option[String] = None)(f: Exchange => Unit): Unit = {
    val exchange = mock[Exchange]

    carrier.foreach{c =>
      //TODO We need to make this consistently done in a single way
      when(exchange.getProperty("carrierName")).thenReturn(c, Nil: _*)
      when(exchange.getProperty("CarrierName")).thenReturn(c, Nil: _*)
    }

    f(exchange)
  }

  protected def withMockExchangeAndMessage(f: (Exchange, Message) => Unit): Unit = withMockExchangeAndMessage(None)(f)

  protected def withMockExchangeAndMessage(carrier: Option[String] = None)(f: (Exchange, Message) => Unit): Unit = {
    withMockExchange(carrier){ exchange =>
      val message = mock[Message]

      when(exchange.getIn).thenReturn(message, Nil: _*)

      f(exchange, message)
    }
  }

  protected def withMockExchangeMessageAndEnvironment(f: (Exchange, Message, Environment) => Unit): Unit = withMockExchangeMessageAndEnvironment(None)(f)

  protected def withMockExchangeMessageAndEnvironment(carrier: Option[String] = None)(f: (Exchange, Message, Environment) => Unit): Unit = {
    withMockExchangeAndMessage(carrier){ (exchange, message) =>
      val environment = mock[Environment]

      f(exchange, message, environment)
    }
  }
}