package com.example.MakeAnything.domain.modeltag.controller;

import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.modeltag.service.ModelTagService;
import com.example.MakeAnything.domain.modeltag.service.dto.GetModelsByTagNameResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/modelTag")
public class ModelTagController {

    private final ModelTagService modelTagService;

    // 태그 이름으로 모델 검색
    @GetMapping("/{tagName}")
    @ApiOperation(value = "태그로 모델 검색")
    public ApiResponse<List<GetModelsByTagNameResponse>> getModelsByTag(@PathVariable("tagName") String tagName) {
        return ApiResponse.success(modelTagService.getModelsByTagName(tagName));
    }
}
