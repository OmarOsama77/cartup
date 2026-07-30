package com.example.CartUp.products.controllers;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.services.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProductsController {
    private final ProductsService service;

    @PostMapping("/products")
    public ResponseEntity<CreateProductDtoResponse> uploadProduct(
            @Valid @RequestBody CreateProductDtoRequest request
            ){
        return ResponseEntity.ok(service.uploadProduct(request));
    }
}
