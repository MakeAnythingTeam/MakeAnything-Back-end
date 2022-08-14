package com.example.MakeAnything.domain.comment.service.dto;

import com.example.MakeAnything.domain.comment.model.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetCommentsResponse {
    private List<Comment> comments;
    private String resultMessage;

    @Builder
    public GetCommentsResponse(List<Comment> comments, String resultMessage) {
        this.comments = comments;
        this.resultMessage = resultMessage;
    }
}
