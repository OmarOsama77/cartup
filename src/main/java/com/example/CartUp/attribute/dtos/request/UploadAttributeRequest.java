package com.example.CartUp.attribute.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UploadAttributeRequest {
    @NotBlank(message = "Attribute name is required")
    @Size(min = 2, max = 100, message = "Attribute name must be between 2 and 100 characters")

    private String name;
}
