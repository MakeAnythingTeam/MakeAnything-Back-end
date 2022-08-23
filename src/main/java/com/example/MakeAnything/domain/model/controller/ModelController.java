package com.example.MakeAnything.domain.model.controller;

import com.example.MakeAnything.domain.auth.service.JwtService;
import com.example.MakeAnything.domain.common.ApiResponse;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.model.service.ModelService;
import com.example.MakeAnything.domain.model.service.dto.*;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelfile.service.ModelFileService;
import com.example.MakeAnything.domain.modelimage.service.ModelImageService;
import com.example.MakeAnything.domain.modeltag.service.ModelTagService;
import com.example.MakeAnything.domain.tag.model.Tag;
import com.example.MakeAnything.domain.tag.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    private final JwtService jwtService;

    // 전체 모델 조회 ( 완 )
    @GetMapping("")
    @ApiOperation(value = "모델 전체 조회")
    public ApiResponse<List<GetAllModelsResponse>> getAllModels(){
        return ApiResponse.success(modelService.getAllModels());
    }

    // 모델 ( 게시물 ) 생성 ( 완 )
    @ResponseBody
    @PostMapping("")
    @ApiOperation(value = "모델 생성")
    public ApiResponse<CreateModelResponse> createModel(@RequestPart(value = "modelFile") MultipartFile modelFile,
                                                        @RequestPart(value = "modelImages") List<MultipartFile> modelImages,
                                                        @RequestPart(value = "createModelRequest") CreateModelRequest createModelRequest) {

        Long userId = jwtService.getUserId();


        CreateModelResponse createModelResponse = modelService.createModel(userId, createModelRequest, modelFile, modelImages);

        return ApiResponse.success(createModelResponse);
    }

    // 모델을 카테고리로 조회 ( 미완 )
    @GetMapping("/category/{category}")
    @ApiOperation(value = "카테고리 조회")
    public ApiResponse<List<GetModelByCategoryResponse>> getModelByCategory(@PathVariable ("category") String category) {
        return ApiResponse.success(modelService.getModelsByCategory(category));
    }
    
    // 모델 이름 조회 ( 검색 ) ( 완료 )
    @ResponseBody
    @GetMapping("/search/name")
    @ApiOperation(value = "이름으로 모델 검색")
    public ApiResponse<List<GetModelByNameResponse>> getModelByName(@RequestBody GetModelByNameRequest getModelByNameRequest) {
        return ApiResponse.success(modelService.getModelByName(getModelByNameRequest));
    }

    // 인기 모델 상위 조회 ( 완 )
    @GetMapping("/topRated")
    @ApiOperation(value = "다운로드 상위 모델 조회")
    public ApiResponse<List<GetTopModelResponse>> getTopModel() {
        return ApiResponse.success(modelService.getTopModel());
    }

    // 모델 상세 정보 조회 ( 완료 )
    @GetMapping("/model/{modelId}")
    @ApiOperation(value = "모델 상세 정보 조회")
    public ApiResponse<GetModelResponse> getModel(@PathVariable ("modelId") Long modelId){
        return ApiResponse.success(modelService.getModel(modelId));
    }

    // 모델 수정
    @ResponseBody
    @PatchMapping("/update/{modelId}")
    @ApiOperation(value = "모델 수정")
    public ApiResponse<UpdateModelResponse> updateModel(@PathVariable ("modelId") Long modelId, @RequestBody UpdateModelRequest updateModelRequest) {
        Long userId = jwtService.getUserId();
        return ApiResponse.success(modelService.updateModel(modelId, updateModelRequest));
    }

    // 모델 삭제 ( 완 )
    @DeleteMapping("/{modelId}")
    @ApiOperation(value = "모델 삭제")
    public ApiResponse<DeleteModelResponse> deleteModel(@PathVariable ("modelId") Long modelId) {
        Long userId = jwtService.getUserId();
        DeleteModelResponse deleteModelResponse = modelService.deleteModel(modelId, userId);
        if (deleteModelResponse.getResultMessage() == "success") {
            return ApiResponse.success(deleteModelResponse);
        } else {
            return ApiResponse.error(ErrorCode.INVALID_AUTH_TOKEN);
        }
    }

    /**
     * 결제 이후 다운로드
     * */
    @GetMapping("/download/{modelId}")
    @ApiOperation(value = "결제 이후 다운로드")
    public ApiResponse<DownloadModelResponse> downloadModel(@PathVariable("modelId") Long modelId) {
        Long userId = jwtService.getUserId();
        return ApiResponse.success(modelService.downloadModel(userId, modelId));
    }
}