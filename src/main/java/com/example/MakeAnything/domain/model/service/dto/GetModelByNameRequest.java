package com.example.MakeAnything.domain.model.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetModelByNameRequest {
    String modelName;

    @Builder
    public GetModelByNameRequest(String modelName) {
        this.modelName = modelName;
    }
}
