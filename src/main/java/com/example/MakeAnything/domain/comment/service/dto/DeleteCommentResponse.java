package com.example.MakeAnything.domain.comment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCommentResponse {
    private String resultMessage;

    @Builder
    public DeleteCommentResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
