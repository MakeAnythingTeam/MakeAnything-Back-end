package com.example.MakeAnything.domain.modeltag.service.dto;

import com.example.MakeAnything.domain.model.model.Model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetModelsByTagNameResponse implements Comparable<GetModelsByTagNameResponse>{
    private Long modelId;
    private String modelName;
    private Long price;
    private Long downloadCount;
    private String userNickName;
    private String modelImageUrl;

    public static GetModelsByTagNameResponse of(Model model, String modelImageUrl) {
        return new GetModelsByTagNameResponse(model.getId(), model.getModelName(), model.getPrice(), model.getDownloadCount()
                , model.getUser().getNickName(), modelImageUrl);
    }

    @Override
    public int compareTo(@NotNull GetModelsByTagNameResponse o) {
        return this.getModelId().compareTo(o.getModelId());
    }
}