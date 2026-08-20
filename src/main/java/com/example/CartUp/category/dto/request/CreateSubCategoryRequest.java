package com.example.CartUp.category.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateSubCategoryRequest {
    @NotBlank(message = "Sub category name is required")
    @Size(min = 2, max = 100, message = "Sub category name must be between 2 and 100 characters")
    private String subCatName;

}
