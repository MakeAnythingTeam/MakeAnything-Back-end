package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelByCategoryResponse implements Comparable<GetModelByCategoryResponse>{
    private Long modelId;
    private String modelName;
    private Long price;
    private Long downloadCount;
    private String userNickName;
    private String modelImageUrl;

    public static GetModelByCategoryResponse of(Model model) {
        return new GetModelByCategoryResponse(model.getId(), model.getModelName(), model.getPrice()
                , model.getDownloadCount(), model.getUser().getNickName(), model.getModelImages().get(0).getImageFullPath());
    }

    @Override
    public int compareTo(@NotNull GetModelByCategoryResponse o) {
        return this.getModelId().compareTo(o.getModelId());
    }
}
