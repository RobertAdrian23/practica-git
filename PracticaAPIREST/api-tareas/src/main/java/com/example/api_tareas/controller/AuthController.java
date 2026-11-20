package com.example.api_tareas.controller;

import com.example.api_tareas.dto.RegistroRequest;
import com.example.api_tareas.dto.LoginRequest;
import com.example.api_tareas.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Registro e inicio de sesión de usuarios")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/registro")
    @Operation(
        summary = "Registrar usuario",
        description = "Permite crear una nueva cuenta de usuario"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o correo ya registrado")
    })
    public ResponseEntity<?> registrar(
        @Valid @RequestBody RegistroRequest request
    ) {
        try {
            var usuario = authService.registrar(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of(
                    "mensaje", "Usuario registrado correctamente",
                    "id", usuario.getId(),
                    "nombre", usuario.getNombre(),
                    "correo", usuario.getCorreo()
                )
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                Map.of("error", e.getMessage())
            );
        }
    }

    @PostMapping("/login")
    @Operation(
        summary = "Iniciar sesión",
        description = "Verifica las credenciales y genera un token JWT"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Inicio de sesión correcto y token generado"),
        @ApiResponse(responseCode = "401", description = "Credenciales incorrectas")
    })
    public ResponseEntity<?> login(
        @Valid @RequestBody LoginRequest request
    ) {
        try {
            String token = authService.login(request);

            return ResponseEntity.ok(
                Map.of("token", token)
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of("error", e.getMessage())
            );
        }
    }
}