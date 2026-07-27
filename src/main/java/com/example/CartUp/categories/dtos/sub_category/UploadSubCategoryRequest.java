package com.example.CartUp.categories.dtos.sub_category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadSubCategoryRequest {
    @NotBlank(message = "sub category name is required")
    @Size(min = 3, message = "sub category name must be more than 3 chars")
    private String subCategoryName;
}
