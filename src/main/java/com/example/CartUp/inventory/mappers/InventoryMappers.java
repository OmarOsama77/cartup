package com.example.CartUp.inventory.mappers;

import com.example.CartUp.inventory.dtos.response.InventoryResponse;
import com.example.CartUp.inventory.entities.Inventory;

public class InventoryMappers {
    public static InventoryResponse toInventoryResponse(Inventory inventory){
        return InventoryResponse
                .builder()
                .inventoryId(inventory.getInventoryId())
                .availableQuantity(inventory.getAvailableQuantity())
                .reservedQuantity(inventory.getReservedQuantity())
                .productVariantId(inventory.getProductVariant().getId())
                .build();
    }
}
