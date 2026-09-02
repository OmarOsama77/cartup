package com.example.CartUp.inventory.services;

import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.inventory.entities.Inventory;
import com.example.CartUp.inventory.repositories.InventoryRepository;
import com.example.CartUp.product.entities.ProductVariant;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
                .productVariant(productVariant)
                .build();
        return repository.save(inventory);
    }


    public void decreaseInventory(List<CartItem> cartItemList) {
        List<Long> productVariantsIds = cartItemList.stream().map(it -> it.getProductVariant().getId()).toList();
        List<Inventory> inventoryList = repository.findAllByProductVariantIds(productVariantsIds);

        Map<Long, Inventory> availableMap = inventoryList.stream()
                .collect(Collectors.toMap(
                        inv -> inv.getProductVariant().getId(),
                        inv -> inv
                ));

        for (CartItem cartItem : cartItemList) {
            int quantity = cartItem.getQuantity();
            Inventory inventory = availableMap.get(cartItem.getProductVariant().getId());

            if (inventory == null) {
                throw new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND);
            }
            if (quantity > inventory.getAvailableQuantity()) {
                throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
            }
            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
        }
    }

    public void validateQuantity(Long productVariantId, int quantity) {
        Inventory inventory = repository.findByProductVariantId(productVariantId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.INVENTORY_NOT_FOUND));
        if (inventory.getAvailableQuantity() < quantity) {
            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
    }
}
