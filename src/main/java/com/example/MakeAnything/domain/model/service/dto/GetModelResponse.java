package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelResponse {
    private Long modelId;
    private String modelName;
    private Long price;
    private String content;
    private Long downloadCount;
    private List<String> modelImages;
    private Long fileSize;
    private List<String> tagNames;

    public static GetModelResponse of(Model model, List<String> modelImages, List<String> tagNames) {
        return new GetModelResponse(model.getId(), model.getModelName(), model.getPrice(), model.getContent(),
                model.getDownloadCount(), modelImages, model.getModelFile().getFileSize(), tagNames);
    }
}