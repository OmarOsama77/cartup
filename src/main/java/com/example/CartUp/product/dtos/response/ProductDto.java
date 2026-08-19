package com.example.CartUp.product.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long brandId;
    private Long subCatId;
    private List<ProductVariantDto> productVariants;
}
