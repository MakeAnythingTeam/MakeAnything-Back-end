package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.model.service.dto.GetAllModelsResponse;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{

    private final ModelRepository modelRepository;

    @Override
    public List<GetAllModelsResponse> getAllModels(){
        return null;
    }
}
