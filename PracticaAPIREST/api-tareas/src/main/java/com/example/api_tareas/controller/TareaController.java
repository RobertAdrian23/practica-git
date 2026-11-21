package com.example.api_tareas.controller;
import com.example.api_tareas.mapper.TareaMapper;
import com.example.api_tareas.dto.TareaRequest;
import com.example.api_tareas.dto.TareaResponse;
import com.example.api_tareas.entity.Tarea;
import com.example.api_tareas.service.TareaService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ArraySchema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@Tag(name = "Tareas", description = "Operaciones para administrar las tareas")
@SecurityRequirement(name = "bearerAuth")
public class TareaController {

    private final TareaService tareaService;
    private final TareaMapper tareaMapper;

    public TareaController(TareaService tareaService, TareaMapper tareaMapper) {
    this.tareaService = tareaService;
    this.tareaMapper = tareaMapper;
}



    @PostMapping
    @Operation(summary = "Crear tarea", description = "Registra una nueva tarea para el usuario autenticado")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Tarea creada correctamente",
            content = @Content(schema = @Schema(implementation = TareaResponse.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<TareaResponse> crear(
        @Valid @RequestBody TareaRequest request,
        Authentication authentication
    ) {
        Tarea tarea = tareaService.crear(request, authentication.getName());

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(tareaMapper.convertirRespuesta(tarea));
    }

    @GetMapping
    @Operation(summary = "Listar tareas", description = "Muestra todas las tareas del usuario autenticado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tareas consultadas correctamente",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = TareaResponse.class)))),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<List<TareaResponse>> listar(
        Authentication authentication
    ) {
        List<TareaResponse> tareas = tareaService.listar(authentication.getName())
            .stream()
            .map(tareaMapper::convertirRespuesta)
            .toList();

        return ResponseEntity.ok(tareas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tarea", description = "Modifica una tarea perteneciente al usuario autenticado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Tarea actualizada correctamente",
            content = @Content(schema = @Schema(implementation = TareaResponse.class))),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<TareaResponse> actualizar(
        @PathVariable Long id,
        @Valid @RequestBody TareaRequest request,
        Authentication authentication
    ) {
        Tarea tarea = tareaService.actualizar(
            id,
            request,
            authentication.getName()
        );

        return ResponseEntity.ok(tareaMapper.convertirRespuesta(tarea));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarea", description = "Elimina una tarea perteneciente al usuario autenticado")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Tarea eliminada correctamente"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<Void> eliminar(
        @PathVariable Long id,
        Authentication authentication
    ) {
        tareaService.eliminar(id, authentication.getName());

        return ResponseEntity.noContent().build();
    }
}