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
public class GetTopModelResponse implements Comparable<GetTopModelResponse>{
    private Long modelId;
    private String modelName;
    private Long price;
    private Long downloadCount;
    private String userNickName;
    private String modelImageUrl;

    public static GetTopModelResponse of(Model model, String modelImageUrl) {
        return new GetTopModelResponse(model.getId(), model.getModelName(), model.getPrice(), model.getDownloadCount()
                , model.getUser().getNickName(), modelImageUrl);
    }

    @Override
    public int compareTo(@NotNull GetTopModelResponse o) {
        return this.getDownloadCount().compareTo(o.getDownloadCount());
    }
}
