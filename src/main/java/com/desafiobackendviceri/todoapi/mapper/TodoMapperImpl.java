package com.desafiobackendviceri.todoapi.mapper;

import com.desafiobackendviceri.todoapi.dto.request.TodoDto;
import com.desafiobackendviceri.todoapi.entity.Todo;
import com.desafiobackendviceri.todoapi.entity.User;

import java.util.ArrayList;
import java.util.List;

public class TodoMapperImpl implements TodoMapper{
    @Override
    public Todo toModel(TodoDto todoDto) {
        if (todoDto==null) {
            return null;
        }
        return Todo.builder()
                .id(todoDto.getId())
                .description(todoDto.getDescription())
                .priority(todoDto.getPriority())
                .isCompleted(todoDto.isCompleted())
                .user(todoDtoToUser(todoDto))
                .build();
    }

    @Override
    public TodoDto toDto(Todo todo) {
        if (todo==null) {
            return null;
        }
        return TodoDto.builder()
                .id(todo.getId())
                .description(todo.getDescription())
                .priority(todo.getPriority())
                .isCompleted(todo.isCompleted())
                .user(todoUserId(todo))
                .build();
    }

    @Override
    public List<Todo> toModel(List<TodoDto>todoDtoList){
        if (todoDtoList==null){
            return null;
        }
        List<Todo> todoList= new ArrayList<Todo>(todoDtoList.size());
        for (TodoDto todoDto: todoDtoList){
            todoList.add(toModel(todoDto));
        }
        return todoList;
    }

    @Override
    public List<TodoDto> toDto(List<Todo> todoList){
        if (todoList==null){
            return null;
        }
        List<TodoDto> todoDtoList = new ArrayList<TodoDto>(todoList.size());
        for (Todo todo:todoList){
            todoDtoList.add(toDto(todo));
        }
        return todoDtoList;
    }

    protected User todoDtoToUser(TodoDto todoDto){
        if (todoDto==null){
            return null;
        }

        User user = new User();
        user.setId(todoDto.getUser());
        return user;
    }

    protected Long todoUserId(Todo todo){
        if (todo == null){
            return null;
        }
        User user= todo.getUser();
        if (user==null){
            return null;
        }
        Long id = user.getId();
        if (id==null){
            return null;
        }
        return id;
    }

}
