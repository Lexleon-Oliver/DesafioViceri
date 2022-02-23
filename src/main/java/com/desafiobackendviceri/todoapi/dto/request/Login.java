package com.desafiobackendviceri.todoapi.dto.request;

import lombok.Data;

@Data
public class Login {
    private String email;
    private String password;
}
