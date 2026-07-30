package com.example.CartUp.brands.controllers;

import com.example.CartUp.brands.dtos.UploadBrandDtoRequest;
import com.example.CartUp.brands.dtos.UploadBrandDtoResponse;
import com.example.CartUp.brands.services.BrandService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RolesAllowed("ADMIN")
public class BrandController {
    private final BrandService service;

    @PostMapping("/brand")
    public ResponseEntity<UploadBrandDtoResponse> uploadBrand(
            @Valid @RequestBody UploadBrandDtoRequest request
    ) {
        return ResponseEntity.ok(service.uploadBrand(request));
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        service.deleteBrandById(id);
        return ResponseEntity.noContent().build();
    }
}
