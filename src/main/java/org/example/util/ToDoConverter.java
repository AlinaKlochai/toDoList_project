package org.example.util;

import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.model.ToDo;
import org.springframework.stereotype.Component;

@Component
public class ToDoConverter {
    public ToDoResponseDTO convertToResponseDto(ToDo toDo) {
        return new ToDoResponseDTO(
                toDo.getId(),
                toDo.getTitle(),
                toDo.isCompleted()
        );
    }

    public ToDo convertToEntity(ToDoCreateRequestDTO toDoCreateRequestDTO) {
        ToDo toDo = new ToDo();
        toDo.setTitle(toDoCreateRequestDTO.getTitle());
        toDo.setCompleted(false); // Новая задача по умолчанию не завершена
        return toDo;
    }
}
