package com.example.CartUp.product.dtos.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class UploadProductVariantRequest {

    @Positive(message = "Price must be greater than zero")
    private double price;

    @NotNull(message = "Available quantity is required")
    @PositiveOrZero(message = "Available quantity cannot be negative")
    private Integer availableQuantity;

    @NotEmpty(message = "At least one attribute must be selected")
    private List<Long> attributes;
}