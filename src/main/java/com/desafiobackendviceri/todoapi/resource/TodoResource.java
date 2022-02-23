package com.desafiobackendviceri.todoapi.resource;

import com.desafiobackendviceri.todoapi.dto.request.TodoCompletedOnly;
import com.desafiobackendviceri.todoapi.dto.request.TodoDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;
import com.desafiobackendviceri.todoapi.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TodoResource {

    private TodoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createTask(@RequestBody @Valid TodoDto todoDto){
        return service.createTask(todoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto deleteTask(@PathVariable Long id){
        return service.deleteTask(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto updateTask(@PathVariable Long id, @Valid @RequestBody TodoDto todoDto){
        return service.updateTask(id,todoDto);
    }

    @PutMapping("/completed/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto concludeTask(@PathVariable Long id, @Valid @RequestBody TodoCompletedOnly completed){
        return service.concludeTask(id,completed);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDto> listAllTasks() {
        return service.listAllTasks();
    }

    @GetMapping("/priority/{priority}")
    @ResponseStatus(HttpStatus.OK)
    public List<TodoDto> listTasksByPriority(@PathVariable String priority) {
        return service.ListTaskByPriority(priority);
    }

}
