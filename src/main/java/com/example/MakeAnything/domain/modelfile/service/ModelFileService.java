package com.example.MakeAnything.domain.modelfile.service;

import com.example.MakeAnything.domain.modelfile.service.dto.ModelFileDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ModelFileService {
    Long save(ModelFileDto modelFileDto);

    void createModelFile(Long modelId, MultipartFile modelFile);
}
