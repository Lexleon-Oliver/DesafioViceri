package com.desafiobackendviceri.todoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenOperation extends RuntimeException {
    public ForbiddenOperation(String message) {
        super(message);
    }
}
