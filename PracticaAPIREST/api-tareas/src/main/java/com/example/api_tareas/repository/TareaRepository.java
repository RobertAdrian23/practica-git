
package com.example.api_tareas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_tareas.entity.Tarea;

import java.util.List;
import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByUsuarioId(Long usuarioId);

    Optional<Tarea> findByIdAndUsuarioId(Long id, Long usuarioId);
}
