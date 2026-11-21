
package com.example.api_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_tareas.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);
}
