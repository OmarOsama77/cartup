package com.example.CartUp.product.mappers;

import com.example.CartUp.attribute.entities.AttributeValue;
import com.example.CartUp.product.dtos.response.ProductResponse;
import com.example.CartUp.product.dtos.response.ProductVariantResponse;
import com.example.CartUp.product.entities.Product;
import com.example.CartUp.product.entities.ProductVariant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsMappers {


    private static Map<String,String> toAttributeValues(List<AttributeValue> attributeValueList){
        Map<String,String> att = new HashMap<>();
        for (AttributeValue attributeValue : attributeValueList){
            att.put(attributeValue.getAttribute().getName(),attributeValue.getValue());
        }
        return att;
    }

    public static ProductVariantResponse toProductVariantDto(ProductVariant productVariant) {
        Map<String,String> attributes = toAttributeValues(productVariant.getAttributeValues());
        return ProductVariantResponse
                .builder()
                .id(productVariant.getId())
                .price(productVariant.getPrice())
                .attributeValues(attributes)
                .availableQuantity(productVariant.getInventory().getAvailableQuantity())
                .build();
    }

    public static ProductResponse toProductDto(Product product) {
        List<ProductVariantResponse> productVariantList = product.getProductVariantList()
                .stream().map(ProductsMappers::toProductVariantDto).toList();
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .subCatId(product.getSubCategory().getId())
                .brandId(product.getBrand().getId())
                .productVariants(productVariantList)
                .build();
    }
}
