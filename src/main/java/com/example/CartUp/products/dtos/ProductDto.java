package com.example.CartUp.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;

    private String desc;
    private Long brandId;
    private Long subCatId;
    private List<ProductVariantDtoResponse> productVariantDtoList;
}
