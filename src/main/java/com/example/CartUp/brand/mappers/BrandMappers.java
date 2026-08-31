package com.example.CartUp.brand.mappers;

import com.example.CartUp.brand.dto.response.CreateBrandResponse;
import com.example.CartUp.brand.entities.Brand;

public class BrandMappers {

    public static CreateBrandResponse toCreateBrandResponse(Brand brand){
        return CreateBrandResponse
                .builder()
                .brandId(brand.getId())
                .brandName(brand.getName())
                .build();
    }
}
