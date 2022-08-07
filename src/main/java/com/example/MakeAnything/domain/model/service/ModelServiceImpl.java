package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.common.exception.model.ErrorDTO;
import com.example.MakeAnything.domain.common.exception.type.ErrorCode;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.model.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{

    private final ModelRepository modelRepository;

    // 모델 조회
    @Override
    @Transactional(readOnly = true)
    public List<GetAllModelsResponse> getAllModels(){
        return modelRepository.findAll().stream()
                .map(model -> GetAllModelsResponse.of(model,0L))
                .collect(Collectors.toList());
    }

    // 모델 생성
    @Override
    @Transactional
    public CreateModelResponse createModel(CreateModelRequest createModelRequest) {
        Model model = createModelRequest.toEntity();
        modelRepository.save(model);

        return CreateModelResponse.builder()
                .success(true)
                .data("success")
                .error(null)
                .build();
    }

    // 모델 수정
    @Transactional
    @Override
    public UpdateModelResponse updateModel(Long modelId, UpdateModelRequest updateModelRequest) {
        Optional<Model> optionalModel = modelRepository.findById(modelId);

        if(optionalModel.isPresent())
        {
            Model model = optionalModel.get();

            model.update(updateModelRequest.getModelName(),
                    updateModelRequest.getPrice(),
                    updateModelRequest.getContent());

            return UpdateModelResponse.builder()
                    .success(true)
                    .data("success")
                    .error(null)
                    .build();
        }
        else
        {
            return UpdateModelResponse.builder()
                    .success(false)
                    .data("fail")
                    .error(ErrorDTO.of(ErrorCode.valueOf("BR000"),"modelId error"))
                    .build();
        }
    }

    // 모델 삭제
    @Transactional
    @Override
    public DeleteModelResponse deleteModel(Long modelId) {
        Optional<Model> optionalModel = modelRepository.findById(modelId);

        if(optionalModel.isPresent())
        {
            Model model = optionalModel.get();
            modelRepository.delete(model);

            return DeleteModelResponse.builder()
                    .success(true)
                    .data("success")
                    .error(null)
                    .build();
        }
        else
        {
            return DeleteModelResponse.builder()
                    .success(false)
                    .data("fail")
                    .error(ErrorDTO.of(ErrorCode.valueOf("BR000"),"modelId error"))
                    .build();
        }
    }
}
