package com.example.MakeAnything.domain.comment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentResponse {
    private String resultMessage;
    private Long commentId;

    @Builder
    public UpdateCommentResponse(String resultMessage, Long commentId) {
        this.resultMessage = resultMessage;
        this.commentId = commentId;
    }
}
