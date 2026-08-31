package com.example.CartUp.attribute.controllers;

import com.example.CartUp.attribute.dtos.request.AttributeValueRequest;
import com.example.CartUp.attribute.dtos.response.AttributeValueResponse;
import com.example.CartUp.attribute.services.AttributeValueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AttributeValueController {

    private final AttributeValueService service;

    @PostMapping("/attributeValue/{attributeId}")
    public ResponseEntity<AttributeValueResponse> uploadAttributeValue(
            @RequestBody @Valid AttributeValueRequest request,
            @PathVariable Long attributeId
    ) {
        return ResponseEntity.ok(service.uploadAttributeValue(request, attributeId));
    }

    @DeleteMapping("/attributeValue/{attributeValueId}")
    public ResponseEntity<Void> deleteAttributeValue(
            @PathVariable Long attributeValueId
    ) {
        service.deleteAttributeValue(attributeValueId);
         return ResponseEntity.noContent().build();
    }
}
