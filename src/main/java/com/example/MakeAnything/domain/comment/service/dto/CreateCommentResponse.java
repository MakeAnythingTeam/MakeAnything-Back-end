package com.example.MakeAnything.domain.comment.service.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCommentResponse {
    private String resultMessage;

    @Builder
    public CreateCommentResponse(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
