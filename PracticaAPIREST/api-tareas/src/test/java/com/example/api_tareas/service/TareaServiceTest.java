
package com.example.api_tareas.service;

import com.example.api_tareas.dto.TareaRequest;
import com.example.api_tareas.entity.Tarea;
import com.example.api_tareas.entity.Usuario;
import com.example.api_tareas.repository.TareaRepository;
import com.example.api_tareas.repository.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {

    @Mock
    private TareaRepository tareaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private TareaService tareaService;

    // 1. Crear una tarea correctamente
    @Test
    void crearDebeGuardarTareaCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        TareaRequest request = new TareaRequest();
        request.setTitulo("Estudiar Java");
        request.setDescripcion("Practicar pruebas unitarias");
        request.setCompletada(false);

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(tareaRepository.save(any(Tarea.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Tarea resultado = tareaService.crear(request, "test@gmail.com");

        assertNotNull(resultado);
        assertEquals("Estudiar Java", resultado.getTitulo());
        assertEquals("Practicar pruebas unitarias", resultado.getDescripcion());
        assertFalse(resultado.isCompletada());
        assertEquals(usuario, resultado.getUsuario());

        verify(tareaRepository).save(any(Tarea.class));
    }

    // 2. Listar tareas correctamente
    @Test
    void listarDebeDevolverTareasDelUsuario() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        List<Tarea> tareas = List.of(new Tarea(), new Tarea());

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(tareaRepository.findByUsuarioId(1L))
                .thenReturn(tareas);

        List<Tarea> resultado = tareaService.listar("test@gmail.com");

        assertEquals(2, resultado.size());

        verify(tareaRepository).findByUsuarioId(1L);
    }

    // 3. Actualizar una tarea correctamente
    @Test
    void actualizarDebeModificarTareaCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Tarea tarea = new Tarea();
        tarea.setTitulo("Título anterior");

        TareaRequest request = new TareaRequest();
        request.setTitulo("Título actualizado");
        request.setDescripcion("Nueva descripción");
        request.setCompletada(true);

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(tareaRepository.findByIdAndUsuarioId(1L, 1L))
                .thenReturn(Optional.of(tarea));

        when(tareaRepository.save(tarea))
                .thenReturn(tarea);

        Tarea resultado = tareaService.actualizar(
                1L, request, "test@gmail.com"
        );

        assertEquals("Título actualizado", resultado.getTitulo());
        assertEquals("Nueva descripción", resultado.getDescripcion());
        assertTrue(resultado.isCompletada());

        verify(tareaRepository).save(tarea);
    }

    // 4. Eliminar una tarea correctamente
    @Test
    void eliminarDebeBorrarTareaCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Tarea tarea = new Tarea();

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(tareaRepository.findByIdAndUsuarioId(1L, 1L))
                .thenReturn(Optional.of(tarea));

        tareaService.eliminar(1L, "test@gmail.com");

        verify(tareaRepository).delete(tarea);
    }

    // 5. Error cuando el usuario no existe
    @Test
    void crearDebeLanzarErrorSiUsuarioNoExiste() {

        TareaRequest request = new TareaRequest();

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                tareaService.crear(request, "test@gmail.com")
        );

        verify(tareaRepository, never()).save(any(Tarea.class));
    }

    // 6. Error cuando la tarea no existe
    @Test
    void actualizarDebeLanzarErrorSiTareaNoExiste() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        TareaRequest request = new TareaRequest();

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.of(usuario));

        when(tareaRepository.findByIdAndUsuarioId(1L, 1L))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                tareaService.actualizar(1L, request, "test@gmail.com")
        );

        verify(tareaRepository, never()).save(any(Tarea.class));
    }

    // 7. Error cuando el usuario no existe al listar
    @Test
    void listarDebeLanzarErrorSiUsuarioNoExiste() {

        when(usuarioRepository.findByCorreo("test@gmail.com"))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () ->
                tareaService.listar("test@gmail.com")
        );

        verify(tareaRepository, never()).findByUsuarioId(anyLong());
    }
}
