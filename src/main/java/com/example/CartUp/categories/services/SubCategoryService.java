package com.example.CartUp.categories.services;

import com.example.CartUp.categories.dtos.sub_category.UploadSubCategoryRequest;
import com.example.CartUp.categories.entities.SubCategory;
import com.example.CartUp.shared.dto.MessageResponse;

public interface SubCategoryService {
    MessageResponse uploadSubCategory(UploadSubCategoryRequest request,Long categoryId);
    void deleteSubCategory(Long subCategoryId);
    SubCategory findSubCatById(Long id);
}
