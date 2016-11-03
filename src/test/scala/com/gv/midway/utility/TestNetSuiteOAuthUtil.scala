package com.gv.midway.utility

import com.gv.midway.environment.NetSuiteOAuthHeaderProperties
import org.scalatest.FunSuite

class TestNetSuiteOAuthUtil extends FunSuite {

  val URL = "someURL"
  val tokenSecret = "someTokenSecret"
  val consumerSecret = "someConsumerSecret"
  val realm = "someRealm"
  val consumerKey = "someConsumerKey"
  val tokenId = "someTokenId"
  val signatureMethod = "HMAC-SHA1"
  val script = "14"

  test("getNetSuiteOAuthHeader") {
    asserts(NetSuiteOAuthUtil.getNetSuiteOAuthHeader(URL, consumerKey, tokenId, tokenSecret, consumerSecret, realm, script))
  }

  test("getNetSuiteOAuthHeader(overloaded)") {
    val properties = new NetSuiteOAuthHeaderProperties(consumerKey, tokenId, tokenSecret, consumerSecret, realm, URL)
    asserts(NetSuiteOAuthUtil.getNetSuiteOAuthHeader(properties, script))
  }

  private def asserts(header: String): Unit = {

    val headerRegex = """^OAuth realm="(.+)",oauth_consumer_key="(.+)",oauth_token="(.+)",oauth_signature_method="(.+)",oauth_timestamp="(\d+)",oauth_nonce="(.+)",oauth_version="(.+)",oauth_signature="(.+)"$""".r

    header match {
      case headerRegex(extractedRealm, extractedConsumerKey, extractedTokenId, extractedSignatureMethod, extractedTimestamp, extractedNonce, extractedVersion, extractedSignature) =>
        assert(extractedRealm === realm)
        assert(extractedConsumerKey === consumerKey)
        assert(extractedTokenId === tokenId)
        assert(extractedSignatureMethod === signatureMethod)
        assert(extractedVersion === "1.0")
      case x =>
        fail(s"Output didn't match expected form: [$x]")
    }
  }
}