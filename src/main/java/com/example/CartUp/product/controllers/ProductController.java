package com.example.CartUp.product.controllers;

import com.example.CartUp.product.dtos.request.UploadProductRequest;
import com.example.CartUp.product.dtos.response.ProductDto;
import com.example.CartUp.product.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> uploadProduct(@Valid @RequestBody UploadProductRequest request) {
        return ResponseEntity.ok(productService.uploadProduct(request));
    }


}
