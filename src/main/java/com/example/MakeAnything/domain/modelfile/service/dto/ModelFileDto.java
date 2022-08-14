package com.example.MakeAnything.domain.modelfile.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ModelFileDto {
    private String originalFileName;
    private String fileFullPath;

    private Long fileSize;

    private Model model;

    public ModelFile toEntity() {
        return ModelFile.builder()
                .originalFileName(this.originalFileName)
                .fileFullPath(this.fileFullPath)
                .fileSize(this.fileSize)
                .model(this.model)
                .build();
    }

    @Builder
    public ModelFileDto(String originalFileName, String fileFullPath, Long fileSize, Model model) {
        this.originalFileName = originalFileName;
        this.fileFullPath = fileFullPath;
        this.fileSize = fileSize;
        this.model = model;
    }
}