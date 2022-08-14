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

    private String modelName;
    private Long price;
    private String content;
    private Long downloadCount;
    private List<ModelImage> modelImages;
    private Long fileSize;
//    private List<String> tags;

    public static GetModelResponse of(Model model) {
        return new GetModelResponse(model.getModelName(), model.getPrice(),model.getContent(),
                model.getDownloadCount(), model.getModelImages(),model.getModelFile().getFileSize());
    }
}