package com.example.CartUp.product.controllers;

import com.example.CartUp.product.dtos.request.UploadProductVariantRequest;
import com.example.CartUp.product.dtos.response.ProductVariantDto;
import com.example.CartUp.product.services.ProductVariantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/products/{productId}/variants")
public class ProductVariantController {
    private ProductVariantService service;

    @PostMapping
    public ResponseEntity<ProductVariantDto> uploadProductVariant(
        @Valid @RequestBody UploadProductVariantRequest request,
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
