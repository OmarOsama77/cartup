package com.example.CartUp.attribute.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UploadAttributeValueRequest {
    @NotBlank(message = "Attribute value is required")
    private String value;

}
