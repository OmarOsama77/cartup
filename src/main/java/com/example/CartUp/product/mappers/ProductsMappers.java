package com.example.CartUp.product.mappers;

import com.example.CartUp.attribute.entities.AttributeValue;
import com.example.CartUp.product.dtos.response.ProductDto;
import com.example.CartUp.product.dtos.response.ProductVariantDto;
import com.example.CartUp.product.entities.Product;
import com.example.CartUp.product.entities.ProductVariant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductsMappers {

    public static ProductDto fromProductToProductDto(Product product) {

        List<ProductVariantDto> productVariantDto = product.getProductVariantList().stream().map(ProductsMappers::fromProductVariantToProductVariantDto).collect(Collectors.toList());

        return ProductDto.builder().id(product.getId()).name(product.getName()).brandId(product.getBrand().getId()).subCatId(product.getSubCategory().getId()).description(product.getDescription()).productVariants(productVariantDto).build();
    }

    public static ProductVariantDto fromProductVariantToProductVariantDto(ProductVariant productVariant) {


        List<AttributeValue> attributeValues = productVariant.getAttributeValues();
        Map<String, String> attributes = new HashMap<>();

        for (int i = 0; i < attributeValues.size(); i++) {
            attributes.put(attributeValues.get(i).getAttribute().getName(), attributeValues.get(i).getValue());
        }


        return ProductVariantDto.builder()
                .id(productVariant.getId())
                .price(productVariant.getPrice())
                .attributeValues(attributes)
                .availableQuantity(productVariant.getInventory().getAvailableQuantity())
                .reservedQuantity(productVariant.getInventory().getReservedQuantity())
                .build();
    }


    public static Map<String, String> toAttributeMap(List<AttributeValue> attributesValues) {

        Map<String, String> attributes = new HashMap<>();

        for (int i = 0; i < attributesValues.size(); i++) {
            attributes.put(attributesValues.get(i).getAttribute().getName(), attributesValues.get(i).getValue());
        }

        return attributes;
    }

}
