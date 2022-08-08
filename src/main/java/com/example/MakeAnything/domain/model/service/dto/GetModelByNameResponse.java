package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelByNameResponse {
    private Long modelId;
    private String modelName;
    private Long price;
    private Long downloadCount;
    private String userNickName;
    private ModelImage modelImage;

    public static GetModelByNameResponse of(Model model, Long downloadCount) {
        return new GetModelByNameResponse(model.getId(), model.getModelName(), model.getPrice(),
                downloadCount, model.getUser().getNickName(), model.getModelImages().get(0));
    }
}
