package com.example.MakeAnything.domain.comment.model;

import com.example.MakeAnything.domain.common.BaseTimeEntity;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modelId")
    private Model model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deletedAt;

    @Builder
    public Comment(Model model, User user, String content) {
        this.model = model;
        this.user = user;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
        this.deletedAt = null;
    }

    // 수정
    public void updateComment(String content) {
        this.content = content;
        this.updateAt = LocalDateTime.now();
    }

    // 삭제
    public void deleteComment() {
        this.deletedAt = LocalDateTime.now();
    }
}
