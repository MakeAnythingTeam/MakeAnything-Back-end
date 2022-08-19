package com.example.MakeAnything.domain.modeltag.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetModelsByTagNameRequest {
    String tagName;

    @Builder
    public GetModelsByTagNameRequest(String tagName) {
        this.tagName = tagName;
    }
}
