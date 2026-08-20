package com.example.CartUp.product.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class UploadProductVariantRequest {

    @Positive(message = "Price must be greater than zero")
    private double price;

    @NotEmpty(message = "At least one attribute must be selected")
    private List<Long> attributes;
}