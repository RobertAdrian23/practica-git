package com.example.api_tareas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credenciales para iniciar sesión")
public class LoginRequest {

    @Schema(description = "Correo electrónico registrado", example = "ana@gmail.com")
    @NotBlank
    @Email
    private String correo;

    @Schema(description = "Contraseña del usuario", example = "123456")
    @NotBlank
    private String contrasena;

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