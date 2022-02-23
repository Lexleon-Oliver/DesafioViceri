package com.desafiobackendviceri.todoapi.service;

import com.desafiobackendviceri.todoapi.dto.request.TodoCompletedOnly;
import com.desafiobackendviceri.todoapi.dto.request.TodoDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;

import java.util.List;

public interface TodoService {
    MessageResponseDto createTask(TodoDto todoDto);

    MessageResponseDto deleteTask(Long id);

    MessageResponseDto updateTask(Long id, TodoDto todoDto);

    MessageResponseDto concludeTask(Long id, TodoCompletedOnly completed);

    List<TodoDto> listAllTasks();

    List<TodoDto> ListTaskByPriority(String priority);
}
