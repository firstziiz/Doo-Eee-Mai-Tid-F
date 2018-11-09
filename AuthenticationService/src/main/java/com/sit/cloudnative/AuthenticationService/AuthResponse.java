package com.sit.cloudnative.AuthenticationService;

import java.io.Serializable;
import java.util.Date;

public class AuthResponse implements Serializable {
    private String token;
    private Date expiryDate;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
