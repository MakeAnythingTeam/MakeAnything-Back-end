package com.example.MakeAnything.domain.model.service.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DownloadModelResponse {
    private String modelFileUrl;
    private String resultMessage;

    @Builder
    public DownloadModelResponse(String modelFileUrl, String resultMessage) {
        this.modelFileUrl = modelFileUrl;
        this.resultMessage = resultMessage;
    }
}
