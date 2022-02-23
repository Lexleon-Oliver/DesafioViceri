package com.desafiobackendviceri.todoapi.mapper;

import com.desafiobackendviceri.todoapi.dto.request.UserDto;
import com.desafiobackendviceri.todoapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDto userDto);
    UserDto toDto(User user);
}
