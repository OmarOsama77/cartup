package com.example.CartUp.product.controllers;

import com.example.CartUp.product.dtos.request.ProductVariantRequest;
import com.example.CartUp.product.dtos.response.ProductVariantResponse;
import com.example.CartUp.product.services.ProductVariantService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/products/{productId}")
public class ProductVariantController {
    private ProductVariantService service;

    @PostMapping
    public ResponseEntity<ProductVariantResponse> uploadProductVariant(
        @Valid @RequestBody ProductVariantRequest request,
        @PathVariable Long productId
    ){
        return ResponseEntity.ok(service.uploadProductVariant(request,productId));
    }

    @DeleteMapping("/{productVariantId}")
    public ResponseEntity<Void> deleteProductVariant(@PathVariable Long productId,@PathVariable Long productVariantId){
        service.deleteProductVariant(productId,productVariantId);
        return ResponseEntity.noContent().build();
    }

}
