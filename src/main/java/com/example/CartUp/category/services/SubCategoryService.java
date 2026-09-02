package com.example.CartUp.category.services;

import com.example.CartUp.category.dto.request.SubCategoryRequest;
import com.example.CartUp.category.dto.response.SubCategoryResponse;
import com.example.CartUp.category.entities.Category;
import com.example.CartUp.category.entities.SubCategory;
import com.example.CartUp.category.mappers.SubCategoryMappers;
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

    public SubCategoryResponse createSubCategory(SubCategoryRequest request, Long categoryId){
        request.setSubCatName(request.getSubCatName().toLowerCase().trim());

        Category category = categoryService.findCategoryById(categoryId);
        if(repository.existsByNameIgnoreCase(request.getSubCatName())){
            throw new ApplicationException(ErrorCode.SUB_CATEGORY_ALREADY_EXISTS);
        }
        SubCategory subCategory = SubCategory
                .builder()
                .name(request.getSubCatName())
                .category(category)
                .build();
         repository.save(subCategory);

        return SubCategoryMappers.toSubCategoryResponse(subCategory);
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

