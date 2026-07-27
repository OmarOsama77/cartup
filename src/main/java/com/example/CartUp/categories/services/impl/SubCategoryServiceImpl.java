package com.example.CartUp.categories.services.impl;

import com.example.CartUp.categories.dtos.sub_category.UploadSubCategoryRequest;
import com.example.CartUp.categories.entities.Category;
import com.example.CartUp.categories.entities.SubCategory;
import com.example.CartUp.categories.exceptions.CategoryNotFoundException;
import com.example.CartUp.categories.exceptions.SubCategoryAlreadyExistsException;
import com.example.CartUp.categories.exceptions.SubCategoryNotFoundException;
import com.example.CartUp.categories.repositories.CategoryRepository;
import com.example.CartUp.categories.repositories.SubCategoryRepository;
import com.example.CartUp.categories.services.SubCategoryService;
import com.example.CartUp.shared.dto.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;

    @Override
    public MessageResponse uploadSubCategory(UploadSubCategoryRequest request, Long categoryId) {
        //First check if there is a cat with catId

        if (!categoryRepository.existsById(categoryId)) {
            throw new CategoryNotFoundException(categoryId);
        }
        if (subCategoryRepository.existsByNameIgnoreCase(request.getSubCategoryName())) {
            throw new SubCategoryAlreadyExistsException(request.getSubCategoryName());
        }
        //now we can save into db :)

        Category category = categoryRepository.findById(categoryId).orElseThrow();

        SubCategory subCategory = SubCategory.builder().name(request.getSubCategoryName()).category(category).build();

        subCategoryRepository.save(subCategory);
        return MessageResponse.builder().message("Sub category added successfully").build();
    }

    @Override
    public void deleteSubCategory(Long subCategoryId) {
        //check if subCatId is there
        if(!subCategoryRepository.existsById(subCategoryId)){
            throw new SubCategoryNotFoundException(subCategoryId);
        }
        //else we can delete from db
        subCategoryRepository.deleteById(subCategoryId);
    }
}
