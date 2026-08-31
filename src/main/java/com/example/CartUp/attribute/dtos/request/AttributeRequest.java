package com.example.CartUp.attribute.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AttributeRequest {
    @NotBlank(message = "attribute name is required")
    @Size(min = 3,max = 10, message = "attribute name must be between 2 and 10 chars")
    private String name;

}
