package com.example.CartUp.inventory.controllers;

import com.example.CartUp.inventory.dtos.request.UpdateAvailableQuantityRequest;
import com.example.CartUp.inventory.dtos.request.UpdateReservedQuantityRequest;
import com.example.CartUp.inventory.dtos.response.InventoryResponse;
import com.example.CartUp.inventory.services.InventoryService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RolesAllowed("ADMIN")
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService service;


    @PatchMapping("/{inventoryId}/available")
    public ResponseEntity<InventoryResponse> updateAvailableQuantity(
            @PathVariable Long inventoryId,
            @Valid @RequestBody UpdateAvailableQuantityRequest request
    ) {
        return ResponseEntity.ok(service.updateAvailableQuantity(request,inventoryId));
    }

    @PatchMapping("/{inventoryId}/reserve")
    public ResponseEntity<InventoryResponse> reserveProduct(
            @PathVariable Long inventoryId,
            @Valid @RequestBody UpdateReservedQuantityRequest request
    ) {
        return ResponseEntity.ok(service.reserveProduct(request,inventoryId));
    }


}
