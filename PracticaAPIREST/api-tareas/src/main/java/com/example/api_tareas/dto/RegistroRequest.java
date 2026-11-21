package com.example.api_tareas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos para registrar un nuevo usuario")
public class RegistroRequest {

    @Schema(description = "Nombre del usuario", example = "Ana")
    @NotBlank
    private String nombre;

    @Schema(description = "Correo electrónico", example = "ana@gmail.com")
    @NotBlank
    @Email
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "123456")
    @NotBlank
    private String contrasena;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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