package com.example.MakeAnything.domain.model.repository;

import com.example.MakeAnything.domain.model.model.Model;
import com.example.MakeAnything.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    List<Model> findAllByUserId(Long userId);
}
