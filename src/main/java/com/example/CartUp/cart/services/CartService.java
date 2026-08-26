package com.example.CartUp.cart.services;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.services.UserService;
import com.example.CartUp.cart.dots.request.AddProductRequest;
import com.example.CartUp.cart.dots.response.CartItemResponse;
import com.example.CartUp.cart.dots.response.CartResponse;
import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.cart.mappers.CartMappers;
import com.example.CartUp.cart.repositories.CartItemRepository;
import com.example.CartUp.cart.repositories.CartRepository;
import com.example.CartUp.inventory.services.InventoryService;
import com.example.CartUp.product.services.ProductVariantService;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantService productVariantService;
    private final InventoryService inventoryService;

    public CartResponse getCart(User user) {
        Cart saved = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new ApplicationException(ErrorCode.CART_NOT_FOUND));

        return CartMappers.fromCartToCartResponse(saved);
    }


    public CartItemResponse addProductToCart(User user, AddProductRequest request) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createCart(user));

        //make sure this product variant exists
        if(!productVariantService.existsById(request.getProductVariantId())){
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        //make sure we have enough quantity
        validateQuantity(request);

        CartItem cartItem = CartItem
                .builder()
                .cart(cart)
                .productVariant(productVariantService.findById(request.getProductVariantId()))
                .quantity(request.getQuantity())
                .build();

        CartItem saved = cartItemRepository.save(cartItem);

        return CartMappers.fromCartItemToCartItemResponse(saved);
    }

    private Cart createCart(User user) {
        Cart cart = Cart.builder().user(user).build();
        user.setCart(cart);
        return cartRepository.save(cart);
    }


    public void clearCart(User user) {
        Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new ApplicationException(ErrorCode.CART_NOT_FOUND));
        cartRepository.delete(cart);
    }

    public void deleteProductFromCart(User user, Long cartItemId) {

        Long cartId = cartItemRepository.findById(cartItemId).orElseThrow(() -> new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND)).getCart().getId();
        if (user.getCart() == null || !cartId.equals(user.getCart().getId())) {
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }

        cartItemRepository.deleteById(cartItemId);
    }

    public void validateQuantity(AddProductRequest request) {
        int availableQuantity = inventoryService.getAvailableQuantity(request.getProductVariantId());
        if (availableQuantity < request.getQuantity()) {
            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
    }
}
