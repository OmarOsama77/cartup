package com.example.CartUp.products.controllers;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.dtos.ProductDto;
import com.example.CartUp.products.dtos.ProductVarietiesDtoRequest;
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
    private final ProductsService service;

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateProductDtoResponse> uploadProduct(
            @Valid @RequestBody CreateProductDtoRequest request
    ) {
        return ResponseEntity.ok(service.uploadProduct(request));
    }


    @PostMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductDto> addProductVarieties(
            @Valid @RequestBody ProductVarietiesDtoRequest request,@PathVariable Long id) {

        return ResponseEntity.ok(service.addProductVarieties(request,id));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        return ResponseEntity.ok(service.getProducts());
    }


    @DeleteMapping("/produtce/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
