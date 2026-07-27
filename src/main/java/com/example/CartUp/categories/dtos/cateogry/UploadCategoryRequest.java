package com.example.CartUp.categories.dtos.cateogry;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UploadCategoryRequest {
    @NotBlank(message = "category name is required")
    @Size(min = 3, max = 20)
    private String categoryName;

}
