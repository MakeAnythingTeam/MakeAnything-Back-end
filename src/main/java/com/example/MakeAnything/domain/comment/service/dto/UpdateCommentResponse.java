package com.example.MakeAnything.domain.comment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentResponse {
    private String resultMessage;

    @Builder
    public UpdateCommentResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
