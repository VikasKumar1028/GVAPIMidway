package com.gv.midway.environment;

public class NetSuiteOAuthHeaderProperties {
    public final String oauthConsumerKey;
    public final String oauthTokenId;
    public final String oauthTokenSecret;
    public final String oauthConsumerSecret;
    public final String realm;
    public final String endPoint;

    public NetSuiteOAuthHeaderProperties(String oauthConsumerKey
                                , String oauthTokenId
                                , String oauthTokenSecret
                                , String oauthConsumerSecret
                                , String realm
                                , String endPoint) {
        this.oauthConsumerKey = oauthConsumerKey;
        this.oauthTokenId = oauthTokenId;
        this.oauthTokenSecret = oauthTokenSecret;
        this.oauthConsumerSecret = oauthConsumerSecret;
        this.realm = realm;
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "NetSuiteOAuthHeaderProperties{" +
                "oauthConsumerKey='" + oauthConsumerKey + '\'' +
                ", oauthTokenId='" + oauthTokenId + '\'' +
                ", oauthTokenSecret='" + oauthTokenSecret + '\'' +
                ", oauthConsumerSecret='" + oauthConsumerSecret + '\'' +
                ", realm='" + realm + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }
}