package com.example.CartUp.brand.services;

import com.example.CartUp.brand.dto.request.CreateBrandRequest;
import com.example.CartUp.brand.dto.response.CreateBrandResponse;
import com.example.CartUp.brand.entities.Brand;
import com.example.CartUp.brand.mappers.BrandMappers;
import com.example.CartUp.brand.repositories.BrandRepository;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public CreateBrandResponse createBrand(CreateBrandRequest request) {
        if(repository.existsByNameIgnoreCase(request.getBrandName())){
            throw new ApplicationException(ErrorCode.BRAND_ALREADY_EXISTS);
        }
        Brand brand = Brand
                .builder()
                .name(request.getBrandName())
                .build();
       repository.save(brand);
       return BrandMappers.toCreateBrandResponse(brand);
    }

    public void deleteBrand(Long id){
        if(!repository.existsById(id)){
            throw new ApplicationException(ErrorCode.BRAND_NOT_FOUND);
        }
        repository.deleteById(id);
    }
    public Brand findBrandById(Long id){
        return repository.findById(id).orElseThrow(()->new ApplicationException(ErrorCode.BRAND_NOT_FOUND));
    }
}
