package com.example.MakeAnything.domain.modelfile.repository;

import com.example.MakeAnything.domain.modelfile.model.ModelFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelFileRepository extends JpaRepository<ModelFile,Long> {
}
