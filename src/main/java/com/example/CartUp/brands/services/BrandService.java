package com.example.CartUp.brands.services;

import com.example.CartUp.brands.dtos.UploadBrandDtoRequest;
import com.example.CartUp.brands.dtos.UploadBrandDtoResponse;
import org.springframework.stereotype.Service;


public interface BrandService {

    UploadBrandDtoResponse uploadBrand(UploadBrandDtoRequest request);
    void deleteBrandById(Long id);
}
