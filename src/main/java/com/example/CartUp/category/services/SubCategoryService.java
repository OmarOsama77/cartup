package com.example.CartUp.category.services;

import com.example.CartUp.category.dto.request.CreateSubCategoryRequest;
import com.example.CartUp.category.dto.response.CreateCategoryResponse;
import com.example.CartUp.category.dto.response.CreateSubCategoryResponse;
import com.example.CartUp.category.entities.SubCategory;
import com.example.CartUp.category.repositories.SubCategoryRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class SubCategoryService {
    private final CategoryService categoryService;
    private final SubCategoryRepository repository;

    public CreateSubCategoryResponse createSubCategory(CreateSubCategoryRequest request, Long categoryId){
        if(!categoryService.isCategoryExists(categoryId)){
            throw new ApplicationException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(repository.existsByNameIgnoreCase(request.getSubCatName())){
            throw new ApplicationException(ErrorCode.SUB_CATEGORY_ALREADY_EXISTS);
        }
        SubCategory subCategory = SubCategory
                .builder()
                .name(request.getSubCatName())
                .category(categoryService.findCategoryById(categoryId))
                .build();
        SubCategory saved = repository.save(subCategory);

        return CreateSubCategoryResponse
                .builder()
                .subCatId(saved.getId())
                .subCatName(saved.getName())
                .build();
    }
    public void deleteSubCategory(Long id){
        if(!repository.existsById(id)){
            throw new ApplicationException(ErrorCode.SUB_CATEGORY_NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public SubCategory findSubCatById(Long id){
        return repository.findById(id).orElseThrow(()-> new ApplicationException(ErrorCode.SUB_CATEGORY_NOT_FOUND));
    }
}

