package com.example.CartUp.categories.services.impl;

import com.example.CartUp.categories.dtos.cateogry.UploadCategoryRequest;
import com.example.CartUp.categories.dtos.cateogry.UploadCategoryResponse;
import com.example.CartUp.categories.entities.Category;
import com.example.CartUp.categories.exceptions.CategoryAlreadyExistsException;
import com.example.CartUp.categories.exceptions.CategoryNotFoundException;
import com.example.CartUp.categories.repositories.CategoryRepository;
import com.example.CartUp.categories.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public UploadCategoryResponse uploadCategory(UploadCategoryRequest request) {


        if (categoryRepository.existsByNameIgnoreCase(request.getCategoryName())) {
            throw new CategoryAlreadyExistsException(request.getCategoryName());
        }
        Category cat = Category.builder().name(request.getCategoryName()).build();
        categoryRepository.save(cat);
        return UploadCategoryResponse.builder().message("Category uploaded successfully").build();
    }


    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new CategoryNotFoundException(id);
        }
    }




}
