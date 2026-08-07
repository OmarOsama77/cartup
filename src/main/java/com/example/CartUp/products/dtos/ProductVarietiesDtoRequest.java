package com.example.CartUp.products.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ProductVarietiesDtoRequest {
    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotEmpty(message = "At least one attribute value must be provided")
    private List<Long> attValueIds;

}
