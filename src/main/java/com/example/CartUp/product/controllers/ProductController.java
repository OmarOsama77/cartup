package com.example.CartUp.product.controllers;

import com.example.CartUp.product.dtos.request.ProductRequest;
import com.example.CartUp.shared.dto.PageResponse;
import com.example.CartUp.product.dtos.response.ProductResponse;
import com.example.CartUp.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<PageResponse<ProductResponse>> getProducts(
        Pageable pageable
    ){
        return ResponseEntity.ok(productService.getProducts(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductResponse> uploadProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.uploadProduct(request));
    }



}
