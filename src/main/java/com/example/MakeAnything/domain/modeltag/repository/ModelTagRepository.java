package com.example.MakeAnything.domain.modeltag.repository;

import com.example.MakeAnything.domain.modeltag.model.ModelTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelTagRepository extends JpaRepository<ModelTag, Long> {
}
