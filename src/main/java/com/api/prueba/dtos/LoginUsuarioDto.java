package com.api.prueba.dtos;

public class LoginUsuarioDto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public LoginUsuarioDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUsuarioDto setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "LoginUsuarioDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
