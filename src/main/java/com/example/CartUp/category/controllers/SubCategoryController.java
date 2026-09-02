package com.example.CartUp.category.controllers;

import com.example.CartUp.category.dto.request.SubCategoryRequest;
import com.example.CartUp.category.dto.response.SubCategoryResponse;
import com.example.CartUp.category.services.SubCategoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RolesAllowed("ADMIN")
@AllArgsConstructor
public class SubCategoryController {

    private final SubCategoryService service;

    @PostMapping("/categories/{categoryId}/subcategories")
    public ResponseEntity<SubCategoryResponse> createSubCategory(
            @Valid @RequestBody SubCategoryRequest request,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(
                service.createSubCategory(request, categoryId));
    }

    @DeleteMapping("/subcategories/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCat(
            @PathVariable Long subCategoryId
    ) {
        service.deleteSubCategory(subCategoryId);
        return ResponseEntity.noContent().build();
    }
}
