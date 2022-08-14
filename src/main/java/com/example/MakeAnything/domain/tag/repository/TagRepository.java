package com.example.MakeAnything.domain.tag.repository;

import com.example.MakeAnything.domain.tag.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findTagByTagName(String tagName);
}
