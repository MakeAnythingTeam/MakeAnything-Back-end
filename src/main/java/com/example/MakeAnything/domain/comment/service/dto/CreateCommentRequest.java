package com.example.MakeAnything.domain.comment.service.dto;

import com.example.MakeAnything.domain.comment.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    private String content;

    @Builder
    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
