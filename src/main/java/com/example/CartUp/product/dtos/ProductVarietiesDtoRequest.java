package com.example.CartUp.product.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class ProductVarietiesDtoRequest {
    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotEmpty(message = "At least one attribute value must be provided")
    private List<Long> attValueIds;

}
