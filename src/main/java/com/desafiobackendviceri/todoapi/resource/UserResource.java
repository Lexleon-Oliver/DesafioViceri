package com.desafiobackendviceri.todoapi.resource;

import com.desafiobackendviceri.todoapi.dto.request.UserDto;
import com.desafiobackendviceri.todoapi.dto.response.MessageResponseDto;
import com.desafiobackendviceri.todoapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserResource {
    private UserService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createUser(@RequestBody @Valid UserDto userDto){
        return usuarioService.createUser(userDto);
    }
}
