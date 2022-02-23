package com.desafiobackendviceri.todoapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDto {
    private String message;

    public MessageResponseDto createdMessageResponse(Long id,String message){
        return MessageResponseDto.builder()
                .message(message+id)
                .build();
    }
}
