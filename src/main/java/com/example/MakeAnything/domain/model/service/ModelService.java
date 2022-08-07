package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.service.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {

    List<GetAllModelsResponse> getAllModels();

    // 모델 생성
    CreateModelResponse createModel(CreateModelRequest createModelRequest);

    // 모델 수정
    UpdateModelResponse updateModel(Long modelId, UpdateModelRequest updateModelRequest);

    // 모델 삭제
    DeleteModelResponse deleteModel(Long modelId);
}
