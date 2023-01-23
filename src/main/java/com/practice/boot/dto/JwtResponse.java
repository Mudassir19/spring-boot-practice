package com.practice.boot.dto;

public class JwtResponse {

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse() {

    }

    private String token;
}
