package com.example.MakeAnything.domain.modelimage.model;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModelImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalImageName;

    private String imageFullPath;

    private Long imageSize;
    @ManyToOne
    @JoinColumn(name = "modelId")
    private Model model;

    @Builder
    public ModelImage(String originalImageName, String imageFullPath, Long imageSize, Model model) {
        this.originalImageName = originalImageName;
        this.imageFullPath = imageFullPath;
        this.imageSize = imageSize;
        this.model = model;
    }
}