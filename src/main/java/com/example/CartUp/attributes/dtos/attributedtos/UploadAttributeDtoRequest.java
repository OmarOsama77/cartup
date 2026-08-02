package com.example.CartUp.attributes.dtos.attributedtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UploadAttributeDtoRequest {
    @NotBlank(message = "Attribute name is required")
    private String name;
}
