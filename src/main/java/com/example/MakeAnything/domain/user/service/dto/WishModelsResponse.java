package com.example.MakeAnything.domain.user.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.user.model.User;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WishModelsResponse {

    private Long modelId;

    private String modelName;

    private Long userId;

    private String userName;

    private Long downloadCount;

    private Long price;

    private LocalDateTime createdAt;

    public static WishModelsResponse of(Model model, User user, Long downloadCount) {

        return new WishModelsResponse(model.getId(), model.getModelName(), user.getId(), user.getUserName(),
                downloadCount, model.getPrice(), model.getCreatedAt());
    }
}
