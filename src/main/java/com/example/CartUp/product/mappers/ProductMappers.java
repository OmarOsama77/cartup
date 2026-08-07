package com.example.CartUp.product.mappers;

import com.example.CartUp.product.dtos.ProductDto;
import com.example.CartUp.product.dtos.ProductVariantDtoResponse;
import com.example.CartUp.product.entities.Product;
import com.example.CartUp.product.entities.ProductVariant;

public class ProductMappers {

    public static ProductDto fromProductEntity(Product product) {
        return ProductDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .desc(product.getDescription())

                .brandId(product.getBrand().getId())
                .subCatId(product.getSubCategory().getId())
                .productVariantDtoList(product.getProductVariantList().stream().map(it -> fromProductVariant(it)).toList())
                .build();
    }

    public static ProductVariantDtoResponse fromProductVariant(ProductVariant productVariant) {
        return ProductVariantDtoResponse
                .builder()
                .id(productVariant.getId())
                .price(productVariant.getPrice())

                .build();
    }
}
