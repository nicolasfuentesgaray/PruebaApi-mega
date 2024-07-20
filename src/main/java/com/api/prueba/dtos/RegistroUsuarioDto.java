package com.api.prueba.dtos;

public class RegistroUsuarioDto {
    private String email;
    private String password;
    private String nombre;

    public String getEmail() {
        return email;
    }

    public RegistroUsuarioDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegistroUsuarioDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public RegistroUsuarioDto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    @Override
    public String toString() {
        return "RegistroUsuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
