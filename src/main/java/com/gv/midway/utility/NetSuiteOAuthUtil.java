package com.gv.midway.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

public class NetSuiteOAuthUtil {

    private static final Logger LOGGER = Logger.getLogger(NetSuiteOAuthUtil.class);

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static Base64 base64 = new Base64();

    public static String getNetSuiteOAuthHeader(String url, String consumerKey,
            String tokenId, String tokenSecret, String consumerSecret,
            String relam, String script) throws UnsupportedEncodingException {

        String nonce = randomAlphaNumeric(6);

        String timestamp = "" + System.currentTimeMillis() / 1000;

        String signatureString = getSignature(url, consumerKey, tokenId,
                script, nonce, timestamp);

        LOGGER.info("signature base String is.........." + signatureString);

        String oauth_signature = generateSignature(signatureString,
                consumerSecret, tokenSecret);

        LOGGER.info("oauth signatue.........." + oauth_signature);

        String encodedAuthString = URLEncoder.encode(oauth_signature, "UTF-8");

        LOGGER.info("encoded oauth String is......." + encodedAuthString);

        String basicAuth = "OAuth realm=" + "\"" + relam + "\""
                + ",oauth_consumer_key=" + "\"" + consumerKey + "\""
                + ",oauth_token=" + "\"" + tokenId + "\""
                + ",oauth_signature_method=\"HMAC-SHA1\"," + "oauth_timestamp="
                + "\"" + timestamp + "\"" + ",oauth_nonce=" + "\"" + nonce
                + "\"" + ",oauth_version=\"1.0\",oauth_signature=" + "\""
                + encodedAuthString + "\"";

        LOGGER.info("Oauth 1.0 netSuite Header is......." + basicAuth);

        return basicAuth;
    }

    private static String getSignature(String url, String consumerKey,
            String tokenId, String script, String nonce, String timeStamp)
            throws UnsupportedEncodingException {

        String parameter_string = "deploy=1&oauth_consumer_key=" + consumerKey
                + "&oauth_nonce=" + nonce + "&oauth_signature_method=HMAC-SHA1"
                + "&oauth_timestamp=" + timeStamp + "&oauth_token=" + tokenId
                + "&oauth_version=1.0" + "&script=" + script;

        String signature_base_string = "POST&"
                + URLEncoder.encode(url, "UTF-8") + "&"
                + URLEncoder.encode(parameter_string, "UTF-8");

        return signature_base_string;
    }

    private static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING
                    .length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    private static String generateSignature(String signatueBaseStr,
            String oAuthConsumerSecret, String oAuthTokenSecret) {
        byte[] byteHMAC = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec spec;
            if (null == oAuthTokenSecret) {
                String signingKey = encode(oAuthConsumerSecret) + '&';
                spec = new SecretKeySpec(signingKey.getBytes(), "HMAC-SHA1");
            } else {
                String signingKey = encode(oAuthConsumerSecret) + '&'
                        + encode(oAuthTokenSecret);
                LOGGER.info("signing key............." + signingKey);
                spec = new SecretKeySpec(signingKey.getBytes(), "HMAC-SHA1");
            }
            mac.init(spec);
            byteHMAC = mac.doFinal(signatueBaseStr.getBytes());
        } catch (Exception e) {
            LOGGER.error("ERROR "+e);
        }
        return new String(base64.encode(byteHMAC)).trim();

    }

    private static String encode(String value) {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(value, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("ERROR "+e);
        }
        StringBuilder sb = new StringBuilder(encoded.length());
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb.append("%2A");
            } else if (focus == '+') {
                sb.append("%20");
            } else if (focus == '%' && i + 1 < encoded.length()
                    && encoded.charAt(i + 1) == '7'
                    && encoded.charAt(i + 2) == 'E') {
                sb.append('~');
                i += 2;
            } else {
                sb.append(focus);
            }
        }
        return sb.toString();
    }
}
