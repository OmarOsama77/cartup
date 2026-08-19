package com.example.CartUp.product.dtos.request;

import lombok.Data;

@Data
public class UploadProductRequest{
    private String name;
    private Long brandId;
    private Long subCatId;
    private String description;
}

