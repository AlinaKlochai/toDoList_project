package org.example.service;

import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.DTO.ToDoResponseDTO;
import org.example.exception.NotFoundException;
import org.example.model.ToDo;
import org.example.repository.ToDoRepository;
import org.example.util.ToDoConverter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final ToDoConverter toDoConverter;

    public List<ToDoResponseDTO> getAllToDos() {
        return toDoRepository.findAll().stream()
                .map(toDoConverter::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public ToDoResponseDTO createToDo (ToDoCreateRequestDTO toDoCreateRequestDTO) {
        ToDo toDo = toDoConverter.convertToEntity(toDoCreateRequestDTO);
        ToDo savedToDo = toDoRepository.save(toDo);
        return toDoConverter.convertToResponseDto(savedToDo);
    }

    public void deleteToDo (Long id) {
        toDoRepository.deleteById(id);
    }

    public void markAsDoneToDo (Long id) {
        Optional<ToDo> optionalToDo = Optional.of(toDoRepository.findById(id).get());

        if (optionalToDo.isPresent()) {
            ToDo toDo = optionalToDo.get();
            toDo.setCompleted(true);
            toDoRepository.save(toDo);
        } else {
            throw new NotFoundException("ToDo with id " + id + " not found");
        }
    }


}
