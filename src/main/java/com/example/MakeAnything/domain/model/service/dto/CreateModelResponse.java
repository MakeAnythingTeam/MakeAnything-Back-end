package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import com.example.MakeAnything.domain.model.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateModelResponse {
    private String resultMessage;

    @Builder
    public CreateModelResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
