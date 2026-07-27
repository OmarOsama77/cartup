package com.example.CartUp.categories.controllers;

import com.example.CartUp.categories.dtos.cateogry.UploadCategoryRequest;
import com.example.CartUp.categories.dtos.cateogry.UploadCategoryResponse;
import com.example.CartUp.categories.dtos.sub_category.UploadSubCategoryRequest;
import com.example.CartUp.categories.services.CategoryService;
import com.example.CartUp.categories.services.SubCategoryService;
import com.example.CartUp.shared.dto.MessageResponse;
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

    @PostMapping("/admin/categories/{categoryId}/subcategories")
    public ResponseEntity<MessageResponse> uploadSubCategory(
            @Valid @RequestBody UploadSubCategoryRequest request,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(
                service.uploadSubCategory(request, categoryId));
    }

    @DeleteMapping("/admin/categories/subcategories/{subCategoryId}")
    public ResponseEntity<Void> deleteSubCat(
            @PathVariable Long subCategoryId
    ) {
        service.deleteSubCategory(subCategoryId);
        return ResponseEntity.noContent().build();
    }
}
