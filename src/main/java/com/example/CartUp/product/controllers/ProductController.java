package com.example.CartUp.product.controllers;

import com.example.CartUp.product.dtos.request.UploadProductRequest;
import com.example.CartUp.product.dtos.response.ProductDto;
import com.example.CartUp.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/products")
    public ResponseEntity<ProductDto> uploadProduct(@Valid @RequestBody UploadProductRequest request) {
        return ResponseEntity.ok(productService.uploadProduct(request));
    }

}
