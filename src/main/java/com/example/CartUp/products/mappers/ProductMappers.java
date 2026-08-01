package com.example.CartUp.products.mappers;

import com.example.CartUp.products.dtos.ProductDto;
import com.example.CartUp.products.dtos.ProductVariantDto;
import com.example.CartUp.products.entities.Product;
import com.example.CartUp.products.entities.ProductVariant;

public class ProductMappers {

    public static ProductDto fromProductEntity(Product product) {
        return ProductDto
                .builder()
                .id(product.getId())
                .name(product.getName())
                .desc(product.getDescription())
                .productStatus(product.getProductStatus())
                .brandId(product.getBrand().getId())
                .subCatId(product.getSubCategory().getId())
                .productVariantDtoList(product.getProductVariantList().stream().map(it -> fromProductVariant(it)).toList())
                .build();
    }

    public static ProductVariantDto fromProductVariant(ProductVariant productVariant) {
        return ProductVariantDto
                .builder()
                .id(productVariant.getId())
                .price(productVariant.getPrice())
                .attributes(productVariant.getAttributes())
                .build();
    }
}
