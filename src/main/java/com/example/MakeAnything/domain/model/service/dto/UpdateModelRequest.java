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

    // category
    // user
    // tag

    @Builder
    public UpdateModelRequest(String modelName, Long price, String content) {
        this.modelName = modelName;
        this.price = price;
        this.content = content;
    }
}
