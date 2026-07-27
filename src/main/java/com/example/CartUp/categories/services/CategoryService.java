package com.example.CartUp.categories.services;

import com.example.CartUp.categories.dtos.cateogry.UploadCategoryRequest;
import com.example.CartUp.categories.dtos.cateogry.UploadCategoryResponse;

public interface CategoryService {
    UploadCategoryResponse uploadCategory(UploadCategoryRequest request);
    void deleteCategory(Long id);
}
