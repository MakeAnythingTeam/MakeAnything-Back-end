package com.example.MakeAnything.domain.tag.service;

import com.example.MakeAnything.domain.tag.model.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {

    List<Tag> createTags(List<String> tagList);

}
