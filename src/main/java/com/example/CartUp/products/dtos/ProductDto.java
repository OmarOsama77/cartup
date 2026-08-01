package com.example.CartUp.products.dtos;

import com.example.CartUp.products.enums.ProductStatus;
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
    private ProductStatus productStatus;
    private String desc;
    private Long brandId;
    private Long subCatId;
    private List<ProductVariantDto> productVariantDtoList;
}
