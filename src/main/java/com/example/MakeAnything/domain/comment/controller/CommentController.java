package com.example.MakeAnything.domain.comment.controller;

import com.example.MakeAnything.config.resolver.UserId;
import com.example.MakeAnything.domain.comment.service.CommentService;
import com.example.MakeAnything.domain.comment.service.dto.*;
import com.example.MakeAnything.domain.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;


    @ResponseBody
    @PostMapping("/{modelId}")
    public ApiResponse<CreateCommentResponse> createComment(@PathVariable("modelId") Long modelId,
                                                            @RequestBody CreateCommentRequest createCommentRequest,
                                                            @UserId Long userId) {
        return ApiResponse.success(commentService.createComment(modelId, userId, createCommentRequest));
    }

    @ResponseBody
    @PatchMapping("/{modelId}/{commentId}")
    public ApiResponse<UpdateCommentResponse> updateComment(@PathVariable("modelId") Long modelId,
                                                            @PathVariable("commentId") Long commentId,
                                                            @RequestBody UpdateCommentRequest updateCommentRequest,
                                                            @UserId Long userId) {
        return ApiResponse.success(commentService.updateComment(commentId, userId, updateCommentRequest));
    }

    @DeleteMapping("/{modelId}/{commentId}")
    public ApiResponse<DeleteCommentResponse> deleteComment(@PathVariable("modelId") Long modelId,
                                                            @PathVariable("commentId") Long commentId,
                                                            @UserId Long userId) {
        DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(modelId, userId, commentId);
        if (deleteCommentResponse.getResultMessage() == "success") {
            return ApiResponse.success(deleteCommentResponse);
        } else {
            return null;
        }
    }

    @GetMapping("/{modelId}")
    public ApiResponse<GetCommentsResponse> getComment(@PathVariable("modelId") Long modelId) {
        return ApiResponse.success(commentService.getComments(modelId));
    }
}