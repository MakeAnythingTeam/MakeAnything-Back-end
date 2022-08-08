package com.example.MakeAnything.domain.comment.controller;

import com.example.MakeAnything.domain.comment.service.CommentService;
import com.example.MakeAnything.domain.comment.service.dto.CreateCommentRequest;
import com.example.MakeAnything.domain.comment.service.dto.CreateCommentResponse;
import com.example.MakeAnything.domain.common.ApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @ResponseBody
    @PostMapping("/{modelId}")
    @ApiOperation(value = "댓글 등록")
    public ApiResponse<CreateCommentResponse> createComment(@PathVariable("modelId") Long modelId, @RequestBody CreateCommentRequest createCommentRequest) {
        return ApiResponse.success(commentService.createComment(modelId, createCommentRequest));
    }
}