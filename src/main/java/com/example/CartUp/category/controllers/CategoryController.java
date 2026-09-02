package com.example.CartUp.category.controllers;

import com.example.CartUp.category.dto.request.CategoryRequest;
import com.example.CartUp.category.dto.response.CategoryResponse;
import com.example.CartUp.category.services.CategoryService;
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

    @PostMapping("categories")
    public ResponseEntity<CategoryResponse> uploadCategory(
            @Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.ok(service.createCategory(request));
    }

    @DeleteMapping("categories/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long categoryId
    ){
         service.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }
}
