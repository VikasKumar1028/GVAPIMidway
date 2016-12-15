package com.gv.midway.environment;

public class VerizonCredentialsProperties {

    public final String username;
    public final String password;

    public VerizonCredentialsProperties(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String asJson() {
        return "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
    }

    @Override
    public String toString() {
        return "VerizonCredentialsProperties{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}