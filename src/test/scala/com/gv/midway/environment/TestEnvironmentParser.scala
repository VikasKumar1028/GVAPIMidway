package com.gv.midway.environment

import org.scalatest.FunSuite
import org.springframework.core.env.Environment
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar

class TestEnvironmentParser extends FunSuite with MockitoSugar {

  testIt("ATTJasperProperties", EnvironmentParser.getATTJasperProperties) { _ =>
    type G = ATTJasperProperties => String

    val v: G = _.version
    val lk: G = _.licenseKey
    val u: G = _.username
    val p: G = _.password

    List(
      ("attJasper.version", v)
      , ("attJasper.licenseKey", lk)
      , ("attJasper.userName", u)
      , ("attJasper.password", p)
    )
  }

  testIt("NetSuiteOAuthHeaderProperties", EnvironmentParser.getNetSuiteOAuthHeaderProperties) { _ =>
    type G = NetSuiteOAuthHeaderProperties => String

    val ck: G = _.oauthConsumerKey
    val ti: G = _.oauthTokenId
    val ts: G = _.oauthTokenSecret
    val cs: G = _.oauthConsumerSecret
    val r: G = _.realm
    val ep: G = _.endPoint

    List(
      ("netSuite.oauthConsumerKey", ck)
      , ("netSuite.oauthTokenId", ti)
      , ("netSuite.oauthTokenSecret", ts)
      , ("netSuite.oauthConsumerSecret", cs)
      , ("netSuite.realm", r)
      , ("netSuite.endPoint", ep)
    )
  }

  def testIt[A](name: String, generator: Environment => A)(propsGenerator: Unit => List[(String, A => String)]): Unit = {

    test(name) {
      val env = mock[Environment]

      val props = propsGenerator()

      props.foreach { case (k, _) =>
        when(env.getProperty(k)).thenReturn(valueGenerator(k), Nil: _*)
      }

      val parsed = generator(env)

      props.foreach { case (k, getter) =>
        assert(getter(parsed) === valueGenerator(k))
      }
    }

  }

  private def valueGenerator(k: String): String = s"$k-$k"
}