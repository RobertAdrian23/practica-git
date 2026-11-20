package com.example.api_tareas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información de una tarea")
public class TareaResponse {

    @Schema(description = "Identificador de la tarea", example = "1")
    private Long id;

    @Schema(description = "Título de la tarea", example = "Estudiar Java")
    private String titulo;

    @Schema(description = "Descripción de la tarea", example = "Practicar Spring Boot y JWT")
    private String descripcion;

    @Schema(description = "Estado de finalización", example = "false")
    private boolean completada;

    public TareaResponse() {
    }

    public TareaResponse(Long id, String titulo, String descripcion, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.completada = completada;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }
}