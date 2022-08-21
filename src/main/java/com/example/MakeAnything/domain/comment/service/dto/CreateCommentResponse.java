package com.example.MakeAnything.domain.comment.service.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCommentResponse {
    private String resultMessage;
    private Long commentId;

    @Builder
    public CreateCommentResponse(String resultMessage, Long commentId) {
        this.resultMessage = resultMessage;
        this.commentId = commentId;
    }
}
