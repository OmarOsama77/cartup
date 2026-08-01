package com.example.CartUp.products.controllers;

import com.example.CartUp.products.dtos.CreateProductDtoRequest;
import com.example.CartUp.products.dtos.CreateProductDtoResponse;
import com.example.CartUp.products.dtos.GetProductDtoResponse;
import com.example.CartUp.products.dtos.ProductVarietiesDtoRequest;
import com.example.CartUp.products.services.ProductsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ProductsController {
    private final ProductsService service;

    @PostMapping("/products")
    public ResponseEntity<CreateProductDtoResponse> uploadProduct(
            @Valid @RequestBody CreateProductDtoRequest request
    ) {
        return ResponseEntity.ok(service.uploadProduct(request));
    }


    @PostMapping("/products/{id}")
    public ResponseEntity<GetProductDtoResponse> addProductVarieties(
            @Valid @RequestBody ProductVarietiesDtoRequest request,@PathVariable Long id) {

        return ResponseEntity.ok(service.addProductVarieties(request,id));
    }


    @DeleteMapping("/produtce/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id
    ) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
