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

    public Inventory createInventory(ProductVariant productVariant, int availableQuantity) {
        //now we start creating new inventory
        if (repository.existsByProductVariantId(productVariant.getId())) {
            throw new ApplicationException(ErrorCode.INVENTORY_ALREADY_EXISTS);
        }
        Inventory inventory = Inventory
                .builder()
                .availableQuantity(availableQuantity)
                .reservedQuantity(0)
                .productVariant(productVariant)
                .build();
        return repository.save(inventory);
    }

    @Transactional
    public InventoryResponse reserveProduct(UpdateReservedQuantityRequest request, Long productVariantId) {
        Inventory inventory = repository.findByProductVariantId(productVariantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND));

        if(inventory.getAvailableQuantity()<request.getReservedQuantity()){
            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
        inventory.setReservedQuantity(inventory.getReservedQuantity() + request.getReservedQuantity());
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - request.getReservedQuantity());
        return InventoryResponse
                .builder()
                .productVariantId(inventory.getProductVariant().getId())
                .reservedQuantity(inventory.getReservedQuantity())
                .inventoryId(inventory.getInventoryId())
                .availableQuantity(inventory.getAvailableQuantity())
                .build();
    }


    public void decreaseInventory(Long productVariantId,int quantityInCart){
        Inventory inventory = repository.findByProductVariantId(productVariantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND));

        if(inventory.getAvailableQuantity() < quantityInCart){
            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
        inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantityInCart);
    }

    public int getAvailableQuantity(Long productVariantId){
        return repository.getAvailableQuantity(productVariantId);
    }
}
