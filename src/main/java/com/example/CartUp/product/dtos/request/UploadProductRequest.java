package com.example.CartUp.product.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UploadProductRequest {

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 150, message = "Product name must be between 2 and 150 characters")
    private String name;

    @NotNull(message = "Brand id is required")
    @Positive(message = "Brand id must be a positive number")
    private Long brandId;

    @NotNull(message = "Sub category id is required")
    @Positive(message = "Sub category id must be a positive number")
    private Long subCatId;

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    private String description;
}