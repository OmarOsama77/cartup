package com.example.CartUp.categories.controllers;

import com.example.CartUp.categories.dtos.cateogry.UploadCategoryRequest;
import com.example.CartUp.categories.dtos.cateogry.UploadCategoryResponse;
import com.example.CartUp.categories.services.CategoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RolesAllowed("ADMIN")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/admin/categories")
    public ResponseEntity<UploadCategoryResponse> uploadCategory(
            @Valid @RequestBody UploadCategoryRequest request) {
        return ResponseEntity.ok(service.uploadCategory(request));
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long categoryId
    ){
         service.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
