package com.example.CartUp.cart.repositories;

import com.example.CartUp.cart.entities.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from cart_items where cart_id =:cartId and product_variant_id =productVariantId " , nativeQuery = true)
    int deleteProductInCart(@Param("cartId") Long cartId,@Param("productVariantId") Long productVariantId );
}
