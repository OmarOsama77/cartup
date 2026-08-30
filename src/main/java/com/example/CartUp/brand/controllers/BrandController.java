package com.example.CartUp.brand.controllers;

import com.example.CartUp.brand.dto.request.CreateBrandRequest;
import com.example.CartUp.brand.dto.response.CreateBrandResponse;
import com.example.CartUp.brand.services.BrandService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/brands")
public class BrandController {
    private final BrandService service;

    @PostMapping
    public ResponseEntity<CreateBrandResponse> uploadBrand(
            @Valid @RequestBody CreateBrandRequest request
    ) {
        return ResponseEntity.ok(service.createBrand(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long id){
        service.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}
