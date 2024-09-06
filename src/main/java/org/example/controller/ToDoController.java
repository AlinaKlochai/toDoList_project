package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.DTO.ToDoCreateRequestDTO;
import org.example.model.ToDo;
import org.example.service.ToDoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
@AllArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @GetMapping
    public String getAllToDos(Model model) {
        model.addAttribute("todos", toDoService.getAllToDos());
        model.addAttribute("newToDo", new ToDo());  // For the form binding
        return "todo-list";
    }

    @PostMapping
    public String createNewToDo(@ModelAttribute ToDoCreateRequestDTO toDoCreateRequestDTO){
        toDoService.createToDo(toDoCreateRequestDTO);
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable Long id){
        toDoService.deleteToDo(id);
        return "redirect:/todos";
    }

    @PostMapping("/done/{id}")
    public String doneToDo(@PathVariable Long id){
        toDoService.markAsDoneToDo(id);
        return "redirect:/todos";
    }


}
