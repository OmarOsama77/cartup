package com.example.CartUp.attribute.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AttributeValueRequest {
    @NotBlank(message = "value is required")
    @Size(min = 3,max = 10, message = "attribute value must be between 2 and 10 chars")
    private String value;

}
