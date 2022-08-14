package com.example.MakeAnything.domain.model.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetModelByTagRequest {
    String tagName;

    @Builder
    public GetModelByTagRequest(String tagName) {
        this.tagName = tagName;
    }
}
