
package com.example.api_tareas.service;

import com.example.api_tareas.dto.TareaRequest;
import com.example.api_tareas.model.Tarea;
import com.example.api_tareas.model.Usuario;
import com.example.api_tareas.repository.TareaRepository;
import com.example.api_tareas.repository.UsuarioRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;

    public TareaService(
        TareaRepository tareaRepository,
        UsuarioRepository usuarioRepository
    ) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Tarea crear(TareaRequest request, String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Tarea tarea = new Tarea();

        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        tarea.setCompletada(request.isCompletada());
        tarea.setUsuario(usuario);

        return tareaRepository.save(tarea);
    }

    public List<Tarea> listar(String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return tareaRepository.findByUsuarioId(usuario.getId());
    }

    public Tarea actualizar(Long id, TareaRequest request, String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Tarea tarea = tareaRepository.findByIdAndUsuarioId(id, usuario.getId())
            .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));

        tarea.setTitulo(request.getTitulo());
        tarea.setDescripcion(request.getDescripcion());
        tarea.setCompletada(request.isCompletada());

        return tareaRepository.save(tarea);
    }

    public void eliminar(Long id, String correo) {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Tarea tarea = tareaRepository.findByIdAndUsuarioId(id, usuario.getId())
            .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada"));

        tareaRepository.delete(tarea);
    }
}
