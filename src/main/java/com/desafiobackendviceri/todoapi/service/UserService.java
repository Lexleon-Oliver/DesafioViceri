package com.desafiobackendviceri.todoapi.service;

import com.desafiobackendviceri.todoapi.dto.request.UserDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;

public interface UserService {
    MessageResponseDto createUser(UserDto userDto);
    Long findIdUser(Object principal);
}
