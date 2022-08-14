package com.example.MakeAnything.domain.modelimage.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ModelImageDto {
    private String originalImageName;
    private String imageFullPath;

    private Long imageSize;
    private Model model;

    public ModelImage toEntity() {
        return ModelImage.builder()
                .originalImageName(this.originalImageName)
                .imageFullPath(this.imageFullPath)
                .imageSize(imageSize)
                .model(this.model)
                .build();
    }

    @Builder
    public ModelImageDto(String originalImageName, String imageFullPath, Long imageSize, Model model) {
        this.originalImageName = originalImageName;
        this.imageFullPath = imageFullPath;
        this.imageSize = imageSize;
        this.model = model;
    }
}
