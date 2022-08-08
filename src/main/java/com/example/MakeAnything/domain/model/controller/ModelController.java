package com.example.MakeAnything.domain.model.controller;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.model.service.ModelService;
import com.example.MakeAnything.domain.model.service.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    private final JwtService jwtService;

    // 전체 모델 조회
    @GetMapping("")
    public ApiResponse<List<GetAllModelsResponse>> getAllModels(){
        return ApiResponse.success(modelService.getAllModels());
    }

    // 모델 ( 게시물 ) 생성
    @ResponseBody
    @PostMapping("")
    public ApiResponse<CreateModelResponse> createModel(@RequestBody CreateModelRequest createModelRequest) {

        Long userId = jwtService.getUserId();

        if (userId != createModelRequest.getUserId()) {
            return ApiResponse.error(ErrorCode.INVALID);
        }

        return ApiResponse.success(modelService.createModel(createModelRequest));
    }

    // 모델을 카테고리로 조회
    @GetMapping("/{categoryId}")
    public ApiResponse<List<GetModelByCategoryResponse>> getModelByCategory(@PathVariable ("categoryId") Long categoryId) {
        return ApiResponse.success(modelService.getModelsByCategory(categoryId));
    }

    // 모델을 태그로 조회 ( 검색 )
    @ResponseBody
    @GetMapping("/search/tag")
    public ApiResponse<GetModelByTagResponse> getModelByTag(@RequestBody GetModelByTagRequest getModelByTagRequest) {
        return null;
    }
    
    // 모델 이름 조회 ( 검색 )
    @ResponseBody
    @GetMapping("/search/name")
    public ApiResponse<List<GetModelByNameResponse>> getModelByName(@RequestBody GetModelByNameRequest getModelByNameRequest) {
        return ApiResponse.success(modelService.getModelByName(getModelByNameRequest));
    }

    // 인기 모델 상위 조회
    @GetMapping("/topRated")
    public ApiResponse<List<GetTopModelResponse>> getTopModel() {
        return null;
    }

    // 모델 상세 정보 조회
    @GetMapping("/{modelId}")
    public ApiResponse<GetModelResponse> getModel(@PathVariable ("modelId") Long modelId){
        return ApiResponse.success(modelService.getModel(modelId));
    }

    // 모델 수정
    @ResponseBody
    @PatchMapping("/{modelId}")
    public ApiResponse<UpdateModelResponse> updateModel(@PathVariable ("modelId") Long modelId, @RequestBody UpdateModelRequest updateModelRequest) {
        return ApiResponse.success(modelService.updateModel(modelId, updateModelRequest));
    }

    // 모델 삭제
    @DeleteMapping("/{modelId}")
    public ApiResponse<DeleteModelResponse> deleteModel(@PathVariable ("modelId") Long modelId) {
        return ApiResponse.success(modelService.deleteModel(modelId));
    }
}