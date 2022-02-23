package com.desafiobackendviceri.todoapi.mapper;

import com.desafiobackendviceri.todoapi.dto.request.TodoDto;
import com.desafiobackendviceri.todoapi.entity.Todo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TodoMapper {

    TodoMapper INSTANCE = Mappers.getMapper(TodoMapper.class);

    Todo toModel(TodoDto todoDto);
    TodoDto toDto(Todo todo);
    List<Todo> toModel(List<TodoDto> todoDtoList);
    List<TodoDto> toDto(List<Todo> todoList);
}
