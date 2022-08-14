package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteModelResponse {
    private String resultMessage;

    @Builder
    public DeleteModelResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
