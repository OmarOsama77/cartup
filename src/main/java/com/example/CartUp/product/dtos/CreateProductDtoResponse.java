package com.example.CartUp.product.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Long brandId;
    private Long subCategoryId;

}

