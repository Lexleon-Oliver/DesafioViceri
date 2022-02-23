package com.desafiobackendviceri.todoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TodoDto {

    private Long id;

    @NotEmpty(message = "The description field is necessary e can't be null")
    @Size(max = 255,message ="the description field must have max size = 255 characters")
    private String description;

    @NotEmpty(message = "The priority field is necessary e can't be null")
    @Size(max = 5,min = 4,message ="the priority field must have between max 4 and 5 characters")
    private String priority;

    private boolean isCompleted;

    private Long user;
}
