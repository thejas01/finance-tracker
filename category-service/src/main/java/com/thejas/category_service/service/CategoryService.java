package com.thejas.category_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.thejas.category_service.repository.CategoryRepository;

import java.util.List;

import com.thejas.category_service.entity.Category;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoriesByType(String type) {
        return categoryRepository.findByType(type);
    }
}
