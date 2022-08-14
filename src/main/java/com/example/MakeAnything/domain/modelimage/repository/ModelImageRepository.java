package com.example.MakeAnything.domain.modelimage.repository;

import com.example.MakeAnything.domain.modelimage.model.ModelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelImageRepository extends JpaRepository<ModelImage, Long> {
}
