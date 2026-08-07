package com.example.CartUp.products.controllers;

import com.example.CartUp.products.dtos.*;
import com.example.CartUp.products.services.ProductVariantService;
import com.example.CartUp.products.services.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final ProductVariantService productVariantService;

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateProductDtoResponse> uploadProduct(
            @Valid @RequestBody CreateProductDtoRequest request
    ) {
        return ResponseEntity.ok(productsService.uploadProduct(request));
    }


    @PostMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductVariantDtoResponse> addProductVarieties(
            @Valid @RequestBody ProductVarietiesDtoRequest request,@PathVariable Long id) {

        return ResponseEntity.ok(productVariantService.addProductVariant(request,id));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(productsService.getProducts());
    }


    @DeleteMapping("/produtce/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        productsService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
