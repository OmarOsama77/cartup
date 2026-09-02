package com.example.CartUp.category.mappers;

import com.example.CartUp.category.dto.response.SubCategoryResponse;
import com.example.CartUp.category.entities.SubCategory;

public class SubCategoryMappers {

    public static SubCategoryResponse toSubCategoryResponse(SubCategory subCategory){
        return SubCategoryResponse
                .builder()
                .subCatId(subCategory.getId())
                .subCatName(subCategory.getName())
                .build();
    }
}
