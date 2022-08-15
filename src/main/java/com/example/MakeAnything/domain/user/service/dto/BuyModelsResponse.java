package com.example.MakeAnything.domain.user.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BuyModelsResponse {

    private Long modelId;

    private String modelName;

    private String modelFirstImage;

    private Long userId;

    private String userName;

    private Long downloadCount;

    private Long price;

    private LocalDateTime paidAt;

    public static BuyModelsResponse of(Model model) {
        String modelFirstImage;

        if (model.getModelImages().isEmpty()) {
            modelFirstImage = null;
        } else {
            modelFirstImage = model.getModelImages().get(0).getImageFullPath();
        }

        return new BuyModelsResponse(model.getId(), model.getModelName(), modelFirstImage, model.getUser().getId(),
                model.getUser().getUserName(), model.getDownloadCount(), model.getPrice(), LocalDateTime.now());
    }
}
