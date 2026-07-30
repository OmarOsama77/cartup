package com.example.CartUp.products.dtos;

import lombok.Data;

@Data

public class CreateProductDtoRequest {
    private String name;
    private String desc;
    private Long brandId;
    private Long subCatId;

}
