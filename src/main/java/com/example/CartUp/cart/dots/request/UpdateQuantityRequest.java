package com.example.CartUp.cart.dots.request;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UpdateQuantityRequest {
    private Long productVariantId;
    @Positive(message = "Quantity must be positive")
    private int quantity;
}
