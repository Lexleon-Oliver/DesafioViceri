package com.desafiobackendviceri.todoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsafePasswordException extends RuntimeException {
    public UnsafePasswordException(String message) {
        super(message);
    }
}
