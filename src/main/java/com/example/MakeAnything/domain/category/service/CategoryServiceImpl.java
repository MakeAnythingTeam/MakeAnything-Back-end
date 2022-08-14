package com.example.MakeAnything.domain.category.service;

import com.example.MakeAnything.domain.category.model.Category;
import com.example.MakeAnything.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void createCategory(String categoryName) {
        Category category = Category.builder()
                .categoryName(categoryName)
                .build();

        categoryRepository.save(category);
    }
}
