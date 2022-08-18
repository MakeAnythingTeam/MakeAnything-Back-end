package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.model.service.dto.*;
import com.example.MakeAnything.domain.modelfile.service.ModelFileService;
import com.example.MakeAnything.domain.modelimage.service.ModelImageService;
import com.example.MakeAnything.domain.modeltag.service.ModelTagService;
import com.example.MakeAnything.domain.tag.model.Tag;
import com.example.MakeAnything.domain.tag.service.TagService;
import com.example.MakeAnything.domain.user.model.User;
import com.example.MakeAnything.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final UserRepository userRepository;

    private final ModelFileService modelFileService;

    private final ModelImageService modelImageService;

    private final ModelTagService modelTagService;

    private final TagService tagService;


    // 모델 조회
    @Override
    @Transactional(readOnly = true)
    public List<GetAllModelsResponse> getAllModels() {
        return modelRepository.findAll().stream()
                .filter(model -> model.getDeletedAt() == null)
                .map(model -> GetAllModelsResponse.of(model))
                .collect(Collectors.toList());
    }

    // 모델 상세조회
    @Override
    @Transactional(readOnly = true)
    public GetModelResponse getModel(Long modelId) {
        Model model = modelRepository.findModelById(modelId);
        GetModelResponse getModelResponse = GetModelResponse.of(model);
        return getModelResponse;
    }

    // 카테고리 이름으로 모델 조회
    @Override
    @Transactional(readOnly = true)
    public List<GetModelByCategoryResponse> getModelsByCategory(Long categoryId) {
        return modelRepository.findModelsByCategory(categoryId).stream()
                .map(model -> GetModelByCategoryResponse.of(model))
                .collect(Collectors.toList());
    }

    // 모델 생성
    @Override
    @Transactional
    public CreateModelResponse createModel(Long userId, CreateModelRequest createModelRequest) {

        User user = userRepository.findUserById(userId);
        Category category = Category.valueOf(createModelRequest.getCategoryName());

        Model model = createModelRequest.toEntity(user, category);
        modelRepository.save(model);

        List<Tag> tags = tagService.createTags(createModelRequest.getTags());
        modelTagService.createModelTag(model.getId(), tags);
        modelFileService.createModelFile(model.getId(),createModelRequest.getModelFile());
        modelImageService.createModelImages(model.getId(), createModelRequest.getImages());

        CreateModelResponse createModelResponse = CreateModelResponse.builder()
                .modelId(model.getId())
                .resultMessage("success")
                .build();

        return createModelResponse;
    }

    // 모델 수정
    @Transactional
    @Override
    public UpdateModelResponse updateModel(Long modelId, UpdateModelRequest updateModelRequest) {
        Optional<Model> optionalModel = modelRepository.findById(modelId);

        if (optionalModel.isPresent()) {
            Model model = optionalModel.get();

            Category category = Category.valueOf(updateModelRequest.getCategoryName());

            model.updateModel(category, updateModelRequest.getModelName(),
                    updateModelRequest.getPrice(),
                    updateModelRequest.getContent());

            return UpdateModelResponse.builder()
                    .resultMessage("success")
                    .build();
        } else {
            return UpdateModelResponse.builder()
                    .resultMessage("fail")
                    .build();
        }
    }

    // 모델 삭제 ( 삭제 날짜를 NULL 에서 현재 시간으로 변경 )
    @Transactional
    @Override
    public DeleteModelResponse deleteModel(Long modelId, Long userId) {

        Optional<Model> optionalModel = modelRepository.findById(modelId);
        Model model = optionalModel.orElseThrow(NullPointerException::new);

        if (model.getUser().getId() != userId) {
            model.deleteModel();

            return DeleteModelResponse.builder()
                    .resultMessage("success")
                    .build();
        } else {
            return DeleteModelResponse.builder()
                    .resultMessage("fail")
                    .build();
        }
    }

    // 이름으로 모델 검색
    @Transactional(readOnly = true)
    @Override
    public List<GetModelByNameResponse> getModelByName(GetModelByNameRequest getModelByNameRequest) {
        return modelRepository.findModelsByModelName(getModelByNameRequest.getModelName()).stream()
                .map(model -> GetModelByNameResponse.of(model))
                .collect(Collectors.toList());
    }

    // 상위 모델 조회
    @Transactional(readOnly = true)
    @Override
    public List<GetTopModelResponse> getTopModel() {
        return modelRepository.findAllByOrderByDownloadCountDesc().stream()
                .map(model -> GetTopModelResponse.of(model))
                .collect(Collectors.toList());
    }
}