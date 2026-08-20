package com.example.CartUp.attributes.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UploadAttributeValueRequest {
    @NotBlank(message = "Attribute value is required")
    private String value;

}
