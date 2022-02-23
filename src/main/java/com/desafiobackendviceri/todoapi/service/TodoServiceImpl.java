package com.desafiobackendviceri.todoapi.service;

import com.desafiobackendviceri.todoapi.dto.request.TodoCompletedOnly;
import com.desafiobackendviceri.todoapi.dto.request.TodoDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;
import com.desafiobackendviceri.todoapi.entity.Todo;
import com.desafiobackendviceri.todoapi.exception.ForbiddenOperation;
import com.desafiobackendviceri.todoapi.exception.TaskNotFoundException;
import com.desafiobackendviceri.todoapi.mapper.TodoMapper;
import com.desafiobackendviceri.todoapi.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TodoServiceImpl implements TodoService{

    private final TodoRepository repository;
    private final TodoMapper todoMapper= TodoMapper.INSTANCE;
    private final UserService userService;

    @Override
    public MessageResponseDto createTask(TodoDto todoDto) {
        todoDto.setUser(getLoggedUserId());
        Todo todoToSave = todoMapper.toModel(todoDto);
        Todo savedTodo = repository.save(todoToSave);
        MessageResponseDto message = new MessageResponseDto();
        return message.createdMessageResponse(savedTodo.getId(), "Created task with ID ");
    }

    @Override
    public MessageResponseDto deleteTask(Long id) {
        Todo task= verifyIfExists(id);
        Long idUser = getLoggedUserId();
        if (task.getUser().getId()==idUser){
            repository.deleteById(id);
            MessageResponseDto message = new MessageResponseDto();
            return message.createdMessageResponse(id, "Deleted task with ID ");
        }else{
            throw new ForbiddenOperation("you do not have permission to delete task from another user");
        }

    }

    @Override
    public MessageResponseDto updateTask(Long id, TodoDto todoDto) {
        Todo task= verifyIfExists(id);
        Long idUser = getLoggedUserId();
        if (task.getUser().getId()==idUser){
            todoDto.setUser(getLoggedUserId());
            Todo todoToUpdate = todoMapper.toModel(todoDto);
            Todo updatedTodo = repository.save(todoToUpdate);
            MessageResponseDto message = new MessageResponseDto();
            return message.createdMessageResponse(updatedTodo.getId(), "Updated task with ID ");
        }else {
            throw new ForbiddenOperation("you do not have permission to update task from another user");
        }
    }

    @Override
    public MessageResponseDto concludeTask(Long id, TodoCompletedOnly completed) {
        Todo task= verifyIfExists(id);
        Long idUser = getLoggedUserId();
        if (task.getUser().getId()==idUser){
            task.setCompleted(true);
            Todo updatedTodo = repository.save(task);
            MessageResponseDto message = new MessageResponseDto();
            return message.createdMessageResponse(updatedTodo.getId(), "Completed task with ID ");
        }else {
            throw new ForbiddenOperation("you do not have permission to conclude task from another user");
        }
    }

    @Override
    public List<TodoDto> listAllTasks() {
        List<Todo> todoList= repository.findAll();
        List<Todo> todoNotCompleted = new ArrayList<>();
        Long idUser = getLoggedUserId();
        for (Todo todo:todoList) {
            if (todo.isCompleted() == false && todo.getUser().getId()==idUser){
                todoNotCompleted.add(todo);
            }
        }
        return todoNotCompleted.stream()
                .map(todoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> ListTaskByPriority(String priority) {
        List<TodoDto> todoList = listAllTasks();
        List<TodoDto> todoByPriority = new ArrayList<>();
        for (TodoDto todoDto:todoList) {
            if (todoDto.getPriority().equals(priority)){
                todoByPriority.add(todoDto);
            }
        }
        return todoByPriority;
    }

    private Todo verifyIfExists(Long id) {
        return repository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    private Long getLoggedUserId() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return userService.findIdUser(authentication.getPrincipal());
    }
}
