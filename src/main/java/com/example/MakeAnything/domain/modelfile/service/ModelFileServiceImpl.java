package com.example.MakeAnything.domain.modelfile.service;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.model.repository.ModelRepository;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelfile.repository.ModelFileRepository;
import com.example.MakeAnything.domain.modelfile.service.dto.ModelFileDto;
import com.example.MakeAnything.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ModelFileServiceImpl implements ModelFileService{

    private final ModelFileRepository modelFileRepository;
    private final ModelRepository modelRepository;

    private final S3Uploader s3Uploader;

    public Long save(ModelFileDto modelFileDto) {
        return modelFileRepository.save((modelFileDto.toEntity())).getId();
    }

    @Transactional
    @Override
    public void createModelFile(Long modelId, MultipartFile multipartFile) {
        Model model = modelRepository.findModelById(modelId);

        try {
            String S3Url = s3Uploader.upload(multipartFile, "files");

            ModelFile modelFile = ModelFile.builder()
                    .originalFileName(multipartFile.getOriginalFilename())
                    .fileFullPath(S3Url)
                    .fileSize(multipartFile.getSize())
                    .model(model)
                    .build();

            modelFileRepository.save(modelFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String current_date = simpleDateFormat.format(new Date());
//        String absolutePath = new File("").getAbsolutePath() + "\\";
//        String path = "files/" + current_date;
//        File file = new File(path);
//
//        if (!file.exists()) {
//            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
//            file.mkdirs();
//        }
//
//        if (!modelFile.isEmpty()) {
//            // zip 파일들만 받아서 처리할 예정
//            String contentType = modelFile.getContentType();
//            String originalFileExtension = "";
//            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
//            if (ObjectUtils.isEmpty(contentType)) {
//
//            } else {
//                if (contentType.contains("file/zip")) {
//                    originalFileExtension = ".zip";
//                }
//                // 다른 파일 명이면 아무 일 하지 않는다
//                else {
//                }
//            }
//
//            String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
//
//            ModelFile modelFile1 = ModelFile.builder()
//                    .originalFileName(modelFile.getName())
//                    .fileFullPath(path + "/" + new_file_name)
//                    .fileSize(modelFile.getSize())
//                    .model(model)
//                    .build();
//
//            modelFileRepository.save(modelFile1);
//        }
    }
}
