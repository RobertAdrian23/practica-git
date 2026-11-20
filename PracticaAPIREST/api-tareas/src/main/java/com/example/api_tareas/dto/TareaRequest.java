package com.example.api_tareas.dto;

import jakarta.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos necesarios para crear o actualizar una tarea")
public class TareaRequest {

    @Schema(
        description = "Título de la tarea",
        example = "Estudiar Java",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank
    private String titulo;

    @Schema(
        description = "Descripción de la tarea",
        example = "Practicar Spring Boot y JWT"
    )
    private String descripcion;

    @Schema(
        description = "Estado de finalización de la tarea",
        example = "false"
    )
    private boolean completada;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}