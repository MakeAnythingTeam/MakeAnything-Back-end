package com.example.MakeAnything.domain.modeltag.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import com.example.MakeAnything.domain.modeltag.repository.ModelTagRepository;
import com.example.MakeAnything.domain.modeltag.service.ModelTagService;
import com.example.MakeAnything.domain.tag.model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
