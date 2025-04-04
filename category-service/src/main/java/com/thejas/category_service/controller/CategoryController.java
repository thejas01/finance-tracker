package com.thejas.category_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.thejas.category_service.entity.Category;
import com.thejas.category_service.repository.CategoryRepository;
import com.thejas.category_service.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    @GetMapping("/exists")
    public boolean categoryExists(@RequestParam String name, @RequestParam String type) {
        return categoryRepository.existsByNameAndType(name, type);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/type/{type}")
    public List<Category> getCategoriesByType(@PathVariable String type) {
        return categoryService.getCategoriesByType(type);
    }
}