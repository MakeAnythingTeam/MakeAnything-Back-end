package com.example.MakeAnything.domain.model.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateModelRequest {
    private String modelName;
    private Long price;
    private String content;

    private String categoryName;

//    private List<String> tags;
    // tag

    @Builder
    public UpdateModelRequest(String modelName, Long price, String content,String categoryName) {
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.categoryName = categoryName;
//        this.tags = tags;
    }
}
