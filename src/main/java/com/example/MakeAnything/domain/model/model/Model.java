package com.example.MakeAnything.domain.model.model;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.common.BaseTimeEntity;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import com.example.MakeAnything.domain.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    private String modelName;

    private Long price;

    private String content;

    private Long downloadCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToOne(mappedBy = "model")
    private ModelFile modelFile;

    @OneToMany(mappedBy = "model")
    private List<ModelImage> modelImages;

    @OneToMany(mappedBy = "model")
    private List<ModelTag> modelTags;

    @Builder
    public Model(User user, Category category, String modelName, Long price, String content) {
        this.user = user;
        this.category = category;
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.downloadCount = Long.valueOf(0);
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
        this.deletedAt = null;
    }

    // 수정 ( 파일과 이미지는 수정 불가 ) 태그는 추가로 구현해야 함.
    public void updateModel(Category category, String modelName, Long price, String content) {
        this.category = category;
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    // 삭제
    public void deleteModel() {
        this.deletedAt = LocalDateTime.now();
    }

    // 다운로드
    public void downloadStatusActive() {
        this.downloadCount += 1;
    }
}