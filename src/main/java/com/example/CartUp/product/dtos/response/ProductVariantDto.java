package com.example.CartUp.product.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDto {
    private Long id;
    private double price;
    private Map<String,String> attributeValues;

}
