package com.example.api_tareas.dto;

public record RegistroResponse(
    String mensaje,
    Long id,
    String nombre,
    String correo
) {
}