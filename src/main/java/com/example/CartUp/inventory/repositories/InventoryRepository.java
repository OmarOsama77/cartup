package com.example.CartUp.inventory.repositories;

import com.example.CartUp.inventory.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    boolean existsByProductVariantId(Long productVariantId);

    @Query(value = "SELECT * FROM inventory WHERE product_variant_id = :productVariantId FOR UPDATE", nativeQuery = true)
    Optional<Inventory> findByProductVariantId(@Param("productVariantId") Long productVariantId);

    @Query(value = "SELECT * FROM inventory WHERE product_variant_id IN (:productVariantIds) FOR UPDATE", nativeQuery = true)
    List<Inventory> findAllByProductVariantIds(@Param("productVariantIds") List<Long> productVariantIds);

    @Query(value = "SELECT available_quantity FROM inventory WHERE product_variant_id = :productVariantId", nativeQuery = true)
    int getAvailableQuantity(@Param("productVariantId") Long productVariantId);

    @Query(value = "SELECT available_quantity FROM inventory WHERE product_variant_id IN (:productVariantIds)", nativeQuery = true)
    List<Integer> getInventories(@Param("productVariantIds") List<Long> productVariantIds);
}
