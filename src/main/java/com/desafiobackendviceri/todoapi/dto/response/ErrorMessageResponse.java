package com.desafiobackendviceri.todoapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorMessageResponse {

    private int status;

    private String message;

    private String title;

    private String type;
}
