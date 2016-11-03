package com.gv.midway.environment;

public class ATTJasperProperties {

    public final String version;
    public final String licenseKey;
    public final String username;
    public final String password;

    public ATTJasperProperties(String version, String licenseKey, String username, String password) {
        this.version = version;
        this.licenseKey = licenseKey;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "ATTJasperProperties{" +
                "version='" + version + '\'' +
                ", licenseKey='" + licenseKey + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}