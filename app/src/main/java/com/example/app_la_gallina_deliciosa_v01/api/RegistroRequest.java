package com.example.app_la_gallina_deliciosa_v01.api;

public class RegistroRequest {
    private String nombre;
    private String correo;
    private String contrasena;

    // Constructor sin argumentos
    public RegistroRequest() {
    }

    // Constructor con argumentos
    public RegistroRequest(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    // Getter para el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para el correo
    public String getCorreo() {
        return correo;
    }

    // Setter para el correo
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Getter para la contraseña
    public String getContrasena() {
        return contrasena;
    }

    // Setter para la contraseña
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
