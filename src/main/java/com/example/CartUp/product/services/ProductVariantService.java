package com.example.CartUp.product.services;

import com.example.CartUp.product.dtos.request.UploadProductVariantRequest;
import com.example.CartUp.product.dtos.response.ProductVariantDto;
import com.example.CartUp.product.entities.ProductVariant;
import com.example.CartUp.product.repositories.ProductVariantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductVariantService {
    private ProductVariantRepository productVariantRepository;

//    public ProductVariantDto uploadProductVariant(UploadProductVariantRequest request) {
//        //first we need to make sure this product variant doesn't exist
//        ProductVariant productVariant = ProductVariant
//
//    }
}
