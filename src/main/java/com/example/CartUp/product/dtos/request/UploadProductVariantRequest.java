package com.example.CartUp.product.dtos.request;

import lombok.Data;

import java.util.List;

@Data
public class UploadProductVariantRequest {
    private double price;
    private List<Long> attributes;
}
