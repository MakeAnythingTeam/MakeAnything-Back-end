package com.example.MakeAnything.domain.modelimage.service;

import com.example.MakeAnything.domain.modelimage.service.dto.ModelImageDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ModelImageService {
    Long save(ModelImageDto modelImageDto);

    void createModelImages(Long modelId, List<MultipartFile> modelImages);
}
