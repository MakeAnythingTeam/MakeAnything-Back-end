package com.example.MakeAnything.domain.modeltag.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modeltag.service.dto.GetModelsByTagNameResponse;
import com.example.MakeAnything.domain.tag.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelTagService{
    void createModelTag(Long modelId, List<Tag> tags);

    List<GetModelsByTagNameResponse> getModelsByTagName(String tagName);
}
