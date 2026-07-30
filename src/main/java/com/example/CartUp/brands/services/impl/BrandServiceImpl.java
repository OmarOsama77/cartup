package com.example.CartUp.brands.services.impl;

import com.example.CartUp.brands.dtos.UploadBrandDtoRequest;
import com.example.CartUp.brands.dtos.UploadBrandDtoResponse;
import com.example.CartUp.brands.entities.Brand;
import com.example.CartUp.brands.exceptions.BrandAlreadyExistsException;
import com.example.CartUp.brands.exceptions.BrandNotFoundException;
import com.example.CartUp.brands.repositories.BrandRepository;
import com.example.CartUp.brands.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    @Override
    public UploadBrandDtoResponse uploadBrand(UploadBrandDtoRequest request) {
        if(brandRepository.existsByNameIgnoreCase(request.getName())){
            throw new BrandAlreadyExistsException(request.getName());
        }
        Brand brand = Brand.builder().name(request.getName()).build();
       Brand b =  brandRepository.save(brand);

        return UploadBrandDtoResponse.builder().id(b.getId()).name(b.getName()).build();
    }

    @Override
    public void deleteBrandById(Long id) {
        if(!brandRepository.existsById(id)){
            throw new BrandNotFoundException(id);
        }
        brandRepository.deleteById(id);
    }
}
