package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.service.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ModelService {

    // 모델 전체 조회
    List<GetAllModelsResponse> getAllModels(Pageable pageable);

    // 모델 카테고리 조회
    List<GetModelByCategoryResponse> getModelsByCategory(Pageable pageable, String category);

    // 모델 상세 조회
    GetModelResponse getModel(Long modelId);

    // 모델 생성
    CreateModelResponse createModel(Long userId, CreateModelRequest createModelRequest, MultipartFile modelFile, List<MultipartFile> modelImages);

    // 모델 수정
    UpdateModelResponse updateModel(Long modelId, UpdateModelRequest updateModelRequest);

    // 모델 삭제
    DeleteModelResponse deleteModel(Long modelId, Long userId);

    // 이름으로 모델 검색
    List<GetModelByNameResponse> getModelByName(Pageable pageable, GetModelByNameRequest getModelByNameRequest);

    // 상위 모델 조회
    List<GetTopModelResponse> getTopModel(Pageable pageable);

    // 모델 다운로드
    DownloadModelResponse downloadModel(Long userId, Long modelId);
}
