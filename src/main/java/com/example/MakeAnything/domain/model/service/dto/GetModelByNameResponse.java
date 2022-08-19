package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelByNameResponse implements Comparable<GetModelByNameResponse>{
    private Long modelId;
    private String modelName;
    private Long price;
    private Long downloadCount;
    private String userNickName;
    private String modelImageUrl;

    public static GetModelByNameResponse of(Model model, String modelImageUrl) {
        return new GetModelByNameResponse(model.getId(), model.getModelName(), model.getPrice(), model.getDownloadCount()
                , model.getUser().getNickName(), modelImageUrl);
    }

    @Override
    public int compareTo(@NotNull GetModelByNameResponse o) {
        return this.getModelId().compareTo(o.getModelId());
    }
}