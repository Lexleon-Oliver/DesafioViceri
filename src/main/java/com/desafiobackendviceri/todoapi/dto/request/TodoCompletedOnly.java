package com.desafiobackendviceri.todoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TodoCompletedOnly {

    @NotEmpty(message = "The isCompleted field is necessary e can't be null")
    private boolean isCompleted;

}
