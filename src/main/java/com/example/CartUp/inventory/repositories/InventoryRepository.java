package com.example.CartUp.inventory.repositories;

import com.example.CartUp.inventory.entities.Inventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByProductVariantId(Long productVariantId);

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Optional<Inventory> findById(Long inventoryId);

    @Query(
            value = "SELECT available_quantity FROM inventory WHERE product_variant_id = :productVariantId"
            , nativeQuery = true)
    int getAvailableQuantity(@Param("productVariantId") Long productVariantId);
}
