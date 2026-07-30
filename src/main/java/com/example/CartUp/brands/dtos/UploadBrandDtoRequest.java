package com.example.CartUp.brands.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UploadBrandDtoRequest {

    @NotBlank(message = "Brand name is required")
    @Size(max = 100, message = "Brand name must be under 100 characters")
    private String name;
}
