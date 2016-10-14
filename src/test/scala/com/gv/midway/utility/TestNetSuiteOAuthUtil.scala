package com.gv.midway.utility

import org.scalatest.FunSuite

class TestNetSuiteOAuthUtil extends FunSuite {

  test("getNetSuiteOAuthHeader") {

    val realm = "someRealm"
    val consumerKey = "someConsumerSecret"
    val tokenId = "someTokenId"
    val signatureMethod = "HMAC-SHA1"

    val headerRegex = """^OAuth realm="(.+)",oauth_consumer_key="(.+)",oauth_token="(.+)",oauth_signature_method="(.+)",oauth_timestamp="(\d+)",oauth_nonce="(.+)",oauth_version="(.+)",oauth_signature="(.+)"$""".r

    NetSuiteOAuthUtil.getNetSuiteOAuthHeader("someURL", consumerKey, tokenId, "someTokenSecret", "someConsumerSecret", realm, "someScript") match {
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