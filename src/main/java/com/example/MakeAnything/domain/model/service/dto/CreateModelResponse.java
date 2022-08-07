package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import com.example.MakeAnything.domain.model.model.Model;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateModelResponse {
    private boolean success;
    private String data;
    private ErrorDTO error;

    @Builder
    public CreateModelResponse(boolean success, String data, ErrorDTO error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }
}
