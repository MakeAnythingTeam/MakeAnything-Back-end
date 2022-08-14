package com.example.MakeAnything.domain.comment.controller;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.comment.service.CommentService;
import com.example.MakeAnything.domain.comment.service.dto.*;
import com.example.MakeAnything.domain.common.ApiResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    private final JwtService jwtService;

    @ResponseBody
    @PostMapping("/{modelId}")
    @ApiOperation(value = "댓글 등록")
    public ApiResponse<CreateCommentResponse> createComment(@PathVariable("modelId") Long modelId, @RequestBody CreateCommentRequest createCommentRequest) {
        Long userId = jwtService.getUserId();
        return ApiResponse.success(commentService.createComment(modelId, userId, createCommentRequest));
    }

    @ResponseBody
    @PatchMapping("/{modelId}/{commentId}")
    @ApiOperation(value = "댓글 수정")
    public ApiResponse<UpdateCommentResponse> updateComment(@PathVariable("modelId") Long modelId, @PathVariable("commentId") Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest) {
        Long userId = jwtService.getUserId();
        return ApiResponse.success(commentService.updateComment(commentId, userId, updateCommentRequest));
    }

    @DeleteMapping("/{modelId}/{commentId}")
    @ApiOperation(value = "댓글 삭제")
    public ApiResponse<DeleteCommentResponse> deleteComment(@PathVariable("modelId") Long modelId, @PathVariable("commentId") Long commentId) {
        Long userId = jwtService.getUserId();
        DeleteCommentResponse deleteCommentResponse = commentService.deleteComment(modelId, userId, commentId);
        if (deleteCommentResponse.getResultMessage() == "success") {
            return ApiResponse.success(deleteCommentResponse);
        } else {
            return null;
        }
    }

    @GetMapping("/{modelId}")
    @ApiOperation(value = "댓글 조회")
    public ApiResponse<GetCommentsResponse> getComment(@PathVariable("modelId") Long modelId) {
        return ApiResponse.success(commentService.getComments(modelId));
    }
}