package com.example.MakeAnything.domain.category.service;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    void createCategory(String categoryName);
}
