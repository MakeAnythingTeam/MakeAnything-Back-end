package com.example.MakeAnything.domain.comment.repository;

import com.example.MakeAnything.domain.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
