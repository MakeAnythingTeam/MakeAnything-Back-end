package com.example.MakeAnything.domain.tag.service;

import com.example.MakeAnything.domain.tag.model.Tag;
import com.example.MakeAnything.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Transactional
    @Override
    public List<Tag> createTags(List<String> tagNameList) {

        List<Tag> tagList = new ArrayList<>();

        for (String tagName : tagNameList) {
            Optional<Tag> optionalTag = Optional.ofNullable(tagRepository.findTagByTagName(tagName));
            Tag tag;

            // 태그가 기존에 없으면
            if (!optionalTag.isPresent()) {

                tag = Tag.builder()
                        .tagName(tagName)
                        .build();
                tagRepository.save(tag);

                // 태그가 기존에 있으면
            } else {
                tag = optionalTag.get();
            }

            tagList.add(tag);
        }

        return tagList;
    }
}