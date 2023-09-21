package com.example.app_la_gallina_deliciosa_v01.models.Auth;

public class LoginRequest {
    private String correo;
    private String contrasena;

    // Constructor sin argumentos
    public LoginRequest() {
    }

    // Constructor con argumentos
    public LoginRequest(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
