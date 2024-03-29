package com.example.MakeAnything.domain.comment.service;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.comment.model.Comment;
import com.example.MakeAnything.domain.comment.repository.CommentRepository;
import com.example.MakeAnything.domain.comment.service.dto.*;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final ModelRepository modelRepository;

    private final UserRepository userRepository;

    // 댓글 생성
    @Override
    @Transactional
    public CreateCommentResponse createComment(Long modelId, Long userId, CreateCommentRequest createCommentRequest) {
        Model model = modelRepository.findModelById(modelId);
        User user = userRepository.findUserById(userId);

        Comment newComment = Comment.builder()
                .model(model)
                .user(user)
                .content(createCommentRequest.getContent())
                .build();

        commentRepository.save(newComment);

        return CreateCommentResponse.builder()
                .resultMessage("success")
                .commentId(newComment.getId())
                .build();
    }

    // 댓글 수정
    @Override
    @Transactional
    public UpdateCommentResponse updateComment(Long commentId, Long userId, UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findCommentById(commentId);
        Long userIdByComment = comment.getUser().getId();

        if (userIdByComment == userId) {
            comment.updateComment(updateCommentRequest.getContent());
        } else {
            return UpdateCommentResponse.builder()
                    .resultMessage("fail")
                    .build();
        }
        return UpdateCommentResponse.builder()
                .resultMessage("success")
                .commentId(commentId)
                .build();
    }

    // 댓글 삭제
    @Override
    @Transactional
    public DeleteCommentResponse deleteComment(Long modelId, Long userId, Long commentId) {
        Comment comment = commentRepository.findCommentById(commentId);

        if (userId != comment.getUser().getId()) {
            return DeleteCommentResponse.builder()
                    .resultMessage("BR200")
                    .build();
        }

        comment.deleteComment();

        return DeleteCommentResponse.builder()
                .resultMessage("success")
                .commentId(commentId)
                .build();
    }

    // 댓글 조회
    @Override
    @Transactional
    public GetCommentsResponse getComments(Long modelId) {
        Model modelById = modelRepository.findModelById(modelId);
        List<Comment> comments = commentRepository.findCommentsByModel(modelById);

        return GetCommentsResponse.builder()
                .comments(
                        comments.stream()
                                .filter(comment -> comment.getDeletedAt()==null)
                                .map(comment -> comment.getContent())
                                .collect(Collectors.toList()))
                .resultMessage("success")
                .build();
    }
}
