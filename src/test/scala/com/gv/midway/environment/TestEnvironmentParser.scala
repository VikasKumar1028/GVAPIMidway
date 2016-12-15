package com.gv.midway.environment

import com.gv.midway.constant.IConstant
import org.mockito.Mockito._
import org.scalatest.FunSuite
import org.scalatest.mockito.MockitoSugar
import org.springframework.core.env.Environment

class TestEnvironmentParser extends FunSuite with MockitoSugar {

  testIt("VerizonCredentialsProperties", EnvironmentParser.getVerizonCredentialsProperties) {
    type G = VerizonCredentialsProperties => String

    val u: G = _.username
    val p: G = _.password

    List(
      (IConstant.VERIZON_API_USERNAME, u)
      , (IConstant.VERIZON_API_PASSWORD, p)
    )
  }

  testIt("ATTJasperProperties", EnvironmentParser.getATTJasperProperties) {
    type G = ATTJasperProperties => String

    val v: G = _.version
    val lk: G = _.licenseKey
    val u: G = _.username
    val p: G = _.password

    List(
      (IConstant.ATTJASPER_VERSION, v)
      , (IConstant.ATTJASPER_LICENSE_KEY, lk)
      , (IConstant.ATTJASPER_USERNAME, u)
      , (IConstant.ATTJASPER_PASSWORD, p)
    )
  }

  testIt("NetSuiteOAuthHeaderProperties", EnvironmentParser.getNetSuiteOAuthHeaderProperties) {
    type G = NetSuiteOAuthHeaderProperties => String

    val ck: G = _.oauthConsumerKey
    val ti: G = _.oauthTokenId
    val ts: G = _.oauthTokenSecret
    val cs: G = _.oauthConsumerSecret
    val r: G = _.realm
    val ep: G = _.endPoint

    List(
      (IConstant.NETSUITE_OAUTH_CONSUMER_KEY, ck)
      , (IConstant.NETSUITE_OAUTH_TOKEN_ID, ti)
      , (IConstant.NETSUITE_OAUTH_TOKEN_SECRET, ts)
      , (IConstant.NETSUITE_OAUTH_CONSUMER_SECRET, cs)
      , (IConstant.NETSUITE_REALM, r)
      , (IConstant.NETSUITE_END_POINT, ep)
    )
  }

  def testIt[A](name: String, generator: Environment => A)(propsGenerator: => List[(String, A => String)]): Unit = {

    test(name) {
      val env = mock[Environment]

      val props = propsGenerator

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