package com.example.MakeAnything.domain.user.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellModelsResponse {

    private Long modelId;

    private String modelName;

    private String modelFirstImage;

    private Long userId;

    private String userName;

    private Long downloadCount;

    private Long price;

    private LocalDateTime createdAt;

    public static SellModelsResponse of(Model model) {
        String modelFirstImage;

        if (model.getModelImages().isEmpty()) {
            modelFirstImage = null;
        } else {
            modelFirstImage = model.getModelImages().get(0).getImageFullPath();
        }

        return new SellModelsResponse(model.getId(), model.getModelName(), modelFirstImage, model.getUser().getId(),
                model.getUser().getUserName(), model.getDownloadCount(), model.getPrice(), model.getCreatedAt());
    }
}
