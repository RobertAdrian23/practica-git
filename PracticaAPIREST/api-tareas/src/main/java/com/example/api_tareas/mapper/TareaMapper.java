
package com.example.api_tareas.mapper;

import com.example.api_tareas.dto.TareaResponse;
import com.example.api_tareas.entity.Tarea;

import org.springframework.stereotype.Component;

@Component
public class TareaMapper {

    public TareaResponse convertirRespuesta(Tarea tarea) {
        return new TareaResponse(
            tarea.getId(),
            tarea.getTitulo(),
            tarea.getDescripcion(),
            tarea.isCompletada()
        );
    }
}
