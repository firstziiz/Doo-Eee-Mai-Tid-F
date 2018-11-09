package com.sit.cloudnative.AuthenticationService;

import java.io.Serializable;
import java.util.Date;

public class AuthResponse implements Serializable {
    private String token;
    private long expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(long expiryDate) {
        this.expiryDate = expiryDate;
    }
}
