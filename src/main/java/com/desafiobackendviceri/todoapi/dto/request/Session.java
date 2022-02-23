package com.desafiobackendviceri.todoapi.dto.request;

import lombok.Data;

@Data
public class Session {
    private String login;
    private String token;
}
