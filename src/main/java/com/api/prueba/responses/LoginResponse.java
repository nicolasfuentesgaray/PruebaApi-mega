package com.api.prueba.responses;

public class LoginResponse {
    private String token;

    private long expiresIn;
    private int id;
    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public int getId(){return id; }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public LoginResponse SetId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn + '\''+
                ", id="+id+
                '}';
    }
}