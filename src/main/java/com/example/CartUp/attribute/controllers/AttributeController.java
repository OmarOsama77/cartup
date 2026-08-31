package com.example.CartUp.attribute.controllers;

import com.example.CartUp.attribute.dtos.request.AttributeRequest;
import com.example.CartUp.attribute.dtos.response.AttributeResponse;
import com.example.CartUp.attribute.services.AttributeService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RolesAllowed("ADMIN")
public class AttributeController {

    private final AttributeService attributeService;

    @PostMapping("/attribute")
    public ResponseEntity<AttributeResponse> uploadAttribute(
            @Valid @RequestBody AttributeRequest request) {
        return ResponseEntity.ok(attributeService.uploadAttribute(request));
    }


    @PatchMapping("/attribute/{id}")
    public ResponseEntity<AttributeResponse> changeAttributeName(
            @PathVariable Long id,
            @Valid @RequestBody AttributeRequest request
    ) {
        return ResponseEntity.ok(attributeService.changeAttributeName(request,id));
    }

    @DeleteMapping("/attribute/{id}")
    public ResponseEntity<Void> deleteAttribute(
            @PathVariable Long id
    ) {
        attributeService.deleteAttribute(id);
        return ResponseEntity.noContent().build();
    }


}
