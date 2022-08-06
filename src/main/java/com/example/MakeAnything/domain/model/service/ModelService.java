package com.example.MakeAnything.domain.model.service;

import com.example.MakeAnything.domain.model.service.dto.GetAllModelsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {

    List<GetAllModelsResponse> getAllModels();
}
