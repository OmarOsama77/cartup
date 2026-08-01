package com.example.CartUp.products.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Map;

@Data
public class ProductVarietiesDtoRequest {
    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Attributes must not be null")
    @NotEmpty(message = "Attributes must not be empty")
    private Map<String, Object> attributes;
}
