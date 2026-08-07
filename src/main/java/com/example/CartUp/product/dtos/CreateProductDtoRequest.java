package com.example.CartUp.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CreateProductDtoRequest {
    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Product description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String desc;

    @NotNull(message = "Brand ID is required")
    private Long brandId;

    @NotNull(message = "Sub-category ID is required")
    private Long subCatId;

}
