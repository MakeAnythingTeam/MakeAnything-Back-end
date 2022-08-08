package com.example.MakeAnything.domain.model.service.dto;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import com.example.MakeAnything.domain.tag.model.Tag;
import com.example.MakeAnything.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateModelRequest {
    private String modelName;
    private Long price;
    private String content;
    private MultipartFile modelFile;
    private List<MultipartFile> Images;

    private String categoryName;
    private Long userId;

    private List<String> tags;

    @Builder
    public CreateModelRequest(String modelName, Long price, String content, MultipartFile modelFile, List<MultipartFile> Images, String categoryName, Long userId,List<String> tags) {
        this.modelName = modelName;
        this.price = price;
        this.content = content;
        this.modelFile = modelFile;
        this.Images = Images;
        this.categoryName = categoryName;
        this.userId = userId;
        this.tags = tags;
    }

    public List<ModelImage> toModelImage() {

        // 이미지 파일을 multipartFile 에서 ModelImage 로 변경하여 list 에 각각 삽입한다.
        List<ModelImage> modelImages = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
        String absolutePath = new File("").getAbsolutePath() + "\\";
        String path = "images/" + current_date;
        File file = new File(path);

        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        for (MultipartFile multipartFile : Images) {

            if (!multipartFile.isEmpty()) {
                // jpeg, png, gif 파일들만 받아서 처리할 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        break;
                    }
                }

                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;


                ModelImage modelImage = ModelImage.builder()
                        .originalImageName(multipartFile.getName())
                        .imageFullPath(path + "/" + new_file_name)
                        .imageSize(multipartFile.getSize())
                        .build();

                modelImages.add(modelImage);
            }


        }

        return modelImages;
    }

    public Model toEntity(User user, Category category) {
        return Model.builder()
                .modelName(modelName)
                .price(price)
                .content(content)
                .modelFile((ModelFile) modelFile)
                .modelImages(toModelImage())
                .category(category)
                .user(user)
                .build();
    }
}