package com.example.CartUp.attributes.controllers;

import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueRequest;
import com.example.CartUp.attributes.dtos.attributevaluedtos.UploadAttributeValueResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AttributeValueController {


//    @PostMapping("/attribute/{attributeId}")
//    public ResponseEntity<UploadAttributeValueResponse> uploadAttributeValue(
//            @PathVariable Long attributeId,
//            @Valid @RequestBody UploadAttributeValueRequest request
//    ){
//
//        return ResponseEntity.ok(service.uploadAttributeValue(request,attributeId));
//    }
//
//    @DeleteMapping("/attribute/{attributeId}")
//    public ResponseEntity<Void> deleteAttribute(
//            @PathVariable Long attributeId
//    ){
//        service.deleteAttributeValue(attributeId);
//        return ResponseEntity.noContent().build();
//    }
}
