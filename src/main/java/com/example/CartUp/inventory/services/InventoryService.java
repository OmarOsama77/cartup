package com.example.CartUp.inventory.services;

import com.example.CartUp.inventory.dtos.request.UpdateAvailableQuantityRequest;
import com.example.CartUp.inventory.dtos.request.UpdateReservedQuantityRequest;
import com.example.CartUp.inventory.dtos.response.InventoryResponse;
import com.example.CartUp.inventory.entities.Inventory;
import com.example.CartUp.inventory.mappers.InventoryMappers;
import com.example.CartUp.inventory.repositories.InventoryRepository;
import com.example.CartUp.product.entities.ProductVariant;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

@Service
@AllArgsConstructor
public class InventoryService {
    private final InventoryRepository repository;

    public Inventory createInventory(ProductVariant productVariant, int availableQuantity, int reservedQuantity) {
        if (repository.existsByProductVariantId(productVariant.getId())) {
            throw new ApplicationException(ErrorCode.INVENTORY_ALREADY_EXISTS);
        }
        Inventory inventory = Inventory
                .builder()
                .availableQuantity(availableQuantity)
                .reservedQuantity(reservedQuantity)
                .productVariant(productVariant)
                .build();
        return repository.save(inventory);
    }

    public InventoryResponse updateAvailableQuantity(UpdateAvailableQuantityRequest request, Long inventoryId) {
        Inventory inventory = repository.findById(inventoryId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND));
        inventory.setInventoryId(request.getAvailableQuantity());

        return InventoryMappers.toInventoryResponse(repository.save(inventory));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public InventoryResponse reserveProduct(UpdateReservedQuantityRequest request, Long inventoryId) {
        Inventory inventory = repository.findById(inventoryId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND));

        //check if this inventory has enough availableQuantity
        if (inventory.getAvailableQuantity() < request.getReservedQuantity()) {
            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - request.getReservedQuantity());
        inventory.setReservedQuantity(inventory.getReservedQuantity() + request.getReservedQuantity());

        return InventoryMappers.toInventoryResponse(inventory);
    }

    public int getAvailableQuantity(Long productVariantId){
        return repository.getAvailableQuantity(productVariantId);
    }
}
