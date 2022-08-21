package com.example.MakeAnything.domain.comment.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteCommentResponse {
    private String resultMessage;
    private Long commentId;

    @Builder
    public DeleteCommentResponse(String resultMessage, Long commentId) {
        this.resultMessage = resultMessage;
        this.commentId = commentId;
    }
}
