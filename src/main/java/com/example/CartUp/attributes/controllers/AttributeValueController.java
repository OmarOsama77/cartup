package com.example.CartUp.attributes.controllers;

import com.example.CartUp.attributes.dtos.request.UploadAttributeValueRequest;
import com.example.CartUp.attributes.dtos.response.UploadAttributeValueResponse;
import com.example.CartUp.attributes.services.AttributeValueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AttributeValueController {

    private final AttributeValueService service;

    @PostMapping("/attributeValue/{attributeId}")
    public ResponseEntity<UploadAttributeValueResponse> uploadAttributeValue(
            @RequestBody @Valid UploadAttributeValueRequest request,
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
