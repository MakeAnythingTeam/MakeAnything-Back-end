package com.example.MakeAnything.domain.comment.service;

import com.example.MakeAnything.domain.comment.service.dto.CreateCommentRequest;
import com.example.MakeAnything.domain.comment.service.dto.CreateCommentResponse;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    // 댓글 생성
    CreateCommentResponse createComment(Long modelId, CreateCommentRequest createCommentRequest);
}
