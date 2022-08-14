package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.tag.model.Tag;
import com.example.MakeAnything.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateModelRequest {
    private String modelName;
    private Long price;
    private String content;
    private MultipartFile modelFile;
    private List<MultipartFile> images;

    private String categoryName;
    private Long userId;

    private List<String> tags;

    @Builder
    public CreateModelRequest(String modelName, Long price, String content, MultipartFile modelFile, List<MultipartFile> images, String categoryName, Long userId,List<String> tags) {
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.modelFile = modelFile;
        this.images = images;
        this.categoryName = categoryName;
        this.userId = userId;
        this.tags = tags;
    }

    public Model toEntity(User user, Category category) {
        return Model.builder()
                .user(user)
                .category(category)
                .modelName(modelName)
                .price(price)
                .content(content)
                .build();
    }
}