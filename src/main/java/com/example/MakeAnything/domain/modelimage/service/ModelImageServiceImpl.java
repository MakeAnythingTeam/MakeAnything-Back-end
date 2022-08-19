package com.example.MakeAnything.domain.modelimage.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.modelimage.repository.ModelImageRepository;
import com.example.MakeAnything.domain.modelimage.service.dto.ModelImageDto;
import com.example.MakeAnything.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelImageServiceImpl implements ModelImageService{
    private final ModelImageRepository modelImageRepository;

    private final ModelRepository modelRepository;

    private final S3Uploader s3Uploader;

    public Long save(ModelImageDto modelImageDto) {
        return modelImageRepository.save(modelImageDto.toEntity()).getId();
    }

    @Transactional
    @Override
    public void createModelImages(Long modelId, List<MultipartFile> multipartFiles) {
        Model model = modelRepository.findModelById(modelId);

        multipartFiles.forEach((f) -> {
            try {
                String S3Url = s3Uploader.upload(f, "images",".jpg");

                ModelImage modelImage = ModelImage.builder()
                        .originalImageName(f.getOriginalFilename())
                        .imageFullPath(S3Url)
                        .imageSize(f.getSize())
                        .model(model)
                        .build();

                modelImageRepository.save(modelImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        // 이미지 파일을 multipartFile 에서 ModelImage 로 변경하여 list 에 각각 삽입한다.
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String current_date = simpleDateFormat.format(new Date());
//        String absolutePath = new File("").getAbsolutePath() + "\\";
//        String path = "images/" + current_date;
//        File file = new File(path);
//
//        if (!file.exists()) {
//            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
//            file.mkdirs();
//        }
//
//        for (MultipartFile multipartFile : multipartFiles) {
//
//            if (!multipartFile.isEmpty()) {
//                // jpeg, png, gif 파일들만 받아서 처리할 예정
//                String contentType = multipartFile.getContentType();
//                String originalFileExtension;
//                // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
//                if (ObjectUtils.isEmpty(contentType)) {
//                    break;
//                } else {
//                    if (contentType.contains("image/jpeg")) {
//                        originalFileExtension = ".jpg";
//                    } else if (contentType.contains("image/png")) {
//                        originalFileExtension = ".png";
//                    } else if (contentType.contains("image/gif")) {
//                        originalFileExtension = ".gif";
//                    }
//                    // 다른 파일 명이면 아무 일 하지 않는다
//                    else {
//                        break;
//                    }
//                }
//
//                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
//
//
//                ModelImage modelImage = ModelImage.builder()
//                        .originalImageName(multipartFile.getName())
//                        .imageFullPath(path + "/" + new_file_name)
//                        .imageSize(multipartFile.getSize())
//                        .build();
//
//                modelImageRepository.save(modelImage);
//            }
//        }

    }
}
