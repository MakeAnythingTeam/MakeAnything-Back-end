package com.example.MakeAnything.domain.model.model;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.common.BaseTimeEntity;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import com.example.MakeAnything.domain.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Model extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category category;

    private String modelName;

    private Long price;

    private String content;

    @OneToOne(mappedBy = "model")
    private ModelFile modelFile;

    @OneToMany(mappedBy = "model")
    private List<ModelImage> modelImages;

    @OneToMany(mappedBy = "model")
    private List<ModelTag> modelTags;

    @Builder
    public Model(User user, Category category, String modelName, Long price, String content, ModelFile modelFile, List<ModelImage> modelImages, List<ModelTag> modelTags) {
        this.user = user;
        this.category = category;
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.modelFile = modelFile;
        this.modelImages = modelImages;
        this.modelTags = modelTags;
    }

    // 수정
    public void update(String modelName, Long price, String content) {
        this.modelName = modelName;
        this.price = price;
        this.content = content;
    }
}