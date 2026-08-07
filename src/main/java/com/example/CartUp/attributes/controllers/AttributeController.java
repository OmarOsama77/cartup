//package com.example.CartUp.attributes.controllers;
//
//import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoRequest;
//import com.example.CartUp.attributes.dtos.attributedtos.UploadAttributeDtoResponse;
//import jakarta.annotation.security.RolesAllowed;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@AllArgsConstructor
//@RolesAllowed("ADMIN")
//public class AttributeController {
//    private AttributesNameService service;
//
//    @PostMapping("/attribute")
//    public ResponseEntity<UploadAttributeDtoResponse> uploadAttributes(
//            @Valid @RequestBody UploadAttributeDtoRequest request
//    ) {
//        return ResponseEntity.ok(service.uploadAttribute(request));
//    }
//    @DeleteMapping("/attribute/{id}")
//    public ResponseEntity<Void> deleteAttribute(
//            @PathVariable Long id
//    ){
//        service.deleteAttribute(id);
//        return ResponseEntity.noContent().build();
//    }
//}
