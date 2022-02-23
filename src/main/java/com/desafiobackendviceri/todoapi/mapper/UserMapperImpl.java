package com.desafiobackendviceri.todoapi.mapper;

import com.desafiobackendviceri.todoapi.dto.request.UserDto;
import com.desafiobackendviceri.todoapi.entity.User;
import org.mapstruct.factory.Mappers;

public class UserMapperImpl implements UserMapper{

    private final TodoMapper todoMapper= Mappers.getMapper(TodoMapper.class);

    @Override
    public User toModel(UserDto userDto) {
        if (userDto == null){
            return null;
        }
        return new User(userDto.getId(), userDto.getName(),
                userDto.getEmail(), userDto.getPassword(),
                todoMapper.toModel(userDto.getTodoDtoList()),
                userDto.getRoles());
    }

    @Override
    public UserDto toDto(User user) {
        if (user==null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .roles(user.getRoles())
                .todoDtoList(todoMapper.toDto(user.getTodoList()))
                .build();
    }
}
