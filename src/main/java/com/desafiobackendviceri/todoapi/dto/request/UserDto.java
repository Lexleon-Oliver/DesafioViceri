package com.desafiobackendviceri.todoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "The name field is necessary e can't be null")
    @Size(max = 100,message ="the name field must have max size = 100 characters")
    private String name;

    @NotEmpty(message = "The email field is necessary e can't be null")
    @Email(message = "Email informed isn't a valid email")
    private String email;

    @NotEmpty(message = "The password field is necessary e can't be null")
    private String password;

    private List<TodoDto> todoDtoList;

    private List<String> roles;
}
