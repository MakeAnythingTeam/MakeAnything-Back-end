package com.example.MakeAnything.domain.modeltag.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import com.example.MakeAnything.domain.modeltag.repository.ModelTagRepository;
import com.example.MakeAnything.domain.modeltag.service.ModelTagService;
import com.example.MakeAnything.domain.modeltag.service.dto.GetModelsByTagNameResponse;
import com.example.MakeAnything.domain.tag.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelTagServiceImpl implements ModelTagService {

    private final ModelTagRepository modelTagRepository;
    private final ModelRepository modelRepository;

    @Override
    @Transactional
    public void createModelTag(Long modelId, List<Tag> tags) {

        Model model = modelRepository.findModelById(modelId);

        for (Tag tag : tags) {
            ModelTag modelTag = ModelTag.builder()
                    .model(model)
                    .tag(tag)
                    .build();

            modelTagRepository.save(modelTag);

        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<GetModelsByTagNameResponse> getModelsByTagName(String tagName) {
        return modelTagRepository.findAll()
                .stream()
                .filter(modelTag -> modelTag.getTag().getTagName() == tagName)
                .filter(modelTag -> modelTag.getModel().getDeletedAt() == null)
                .map(modelTag -> GetModelsByTagNameResponse.of(modelTag.getModel(), modelTag.getModel().getModelImages().get(0).getImageFullPath()))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
