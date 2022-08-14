package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateModelResponse {
    private String resultMessage;

    @Builder
    public UpdateModelResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
