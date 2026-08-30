package com.example.CartUp.cart.repositories;

import com.example.CartUp.cart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


    @Query(value = "select * from cart_items where product_variant_id =:productVariantId  and cart_id=:cartId ",nativeQuery = true)
    Optional<CartItem> findByCartIdAndProductVariantId(@Param("cartId") Long cartId,@Param("productVariantId") Long productVariantId);

    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.cart.id = :cartId")
    void deleteAllByCartId(@Param("cartId") Long cartId);
}
