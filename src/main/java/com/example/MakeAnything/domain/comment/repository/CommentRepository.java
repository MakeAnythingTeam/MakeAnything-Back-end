package com.example.MakeAnything.domain.comment.repository;

import com.example.MakeAnything.domain.comment.model.Comment;
import com.example.MakeAnything.domain.model.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentById(Long commentId);

    List<Comment> findCommentsByModel(Model model);
}
