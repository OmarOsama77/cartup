package com.example.CartUp.cart.services;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.cart.dots.request.CartItemRequest;
import com.example.CartUp.cart.dots.response.CartItemResponse;
import com.example.CartUp.cart.dots.response.CartResponse;
import com.example.CartUp.cart.entities.Cart;
import com.example.CartUp.cart.entities.CartItem;
import com.example.CartUp.cart.mappers.CartMappers;
import com.example.CartUp.cart.repositories.CartItemRepository;
import com.example.CartUp.cart.repositories.CartRepository;
import com.example.CartUp.inventory.services.InventoryService;
import com.example.CartUp.product.entities.ProductVariant;
import com.example.CartUp.product.services.ProductVariantService;
import com.example.CartUp.shared.exceptions.ApplicationException;
import com.example.CartUp.shared.exceptions.enums.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductVariantService productVariantService;
    private final InventoryService inventoryService;


    public CartItemResponse updateCartItemQuantity(User user, CartItemRequest request) {
        Cart cart = getCartOrThrow(user);

        CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), request.getProductVariantId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND));

        inventoryService.validateQuantity(request.getProductVariantId(), request.getQuantity());

        cartItem.setQuantity(request.getQuantity());
        cartItemRepository.save(cartItem);

        return CartMappers.fromCartItemToCartItemResponse(cartItem);
    }

    @Transactional
    public CartItemResponse addProductToCart(User user, CartItemRequest request) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createCart(user));

        //make sure this product variant exists
        ProductVariant productVariant = productVariantService
                .findById(request.getProductVariantId());


        Optional<CartItem> cartItem =
                cartItemRepository.findByCartIdAndProductVariantId(cart.getId(),request.getProductVariantId());
        if (cartItem.isPresent()) {
            //product is already in cart
            return updateCartItem(cartItem.get(), request.getQuantity(), productVariant);
        } else {
            //product is new to cart
            return addNewCartItem(request, cart, productVariant);
        }


    }

    private CartItemResponse updateCartItem(CartItem cartItem, int additionalQuantity, ProductVariant productVariant) {
        int newQuantity = cartItem.getQuantity() + additionalQuantity;
        inventoryService.validateQuantity(productVariant.getId(), newQuantity);
        cartItem.setQuantity(newQuantity);
        cartItemRepository.save(cartItem);
        return CartMappers.fromCartItemToCartItemResponse(cartItem);
    }

    private CartItemResponse addNewCartItem(CartItemRequest request, Cart cart, ProductVariant productVariant) {
        inventoryService.validateQuantity(request.getProductVariantId(), request.getQuantity());
        CartItem cartItem = CartItem
                .builder()
                .productVariant(productVariant)
                .quantity(request.getQuantity())
                .cart(cart)
                .build();
        cartItemRepository.save(cartItem);
        return CartMappers.fromCartItemToCartItemResponse(cartItem);
    }


    private Cart createCart(User user) {
        Cart cart = Cart.builder().user(user).build();
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(User user) {
        Cart cart = getCartOrThrow(user);

        cartItemRepository.deleteAllByCartId(cart.getId());
    }


    public void deleteProductFromCart(User user, Long cartItemId) {
        Cart cart = getCartOrThrow(user);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND));

        if(!cart.getId().equals(cartItem.getCart().getId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }
        cartItemRepository.delete(cartItem);

    }

    public Cart getCartOrThrow(User user) {
        return cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ApplicationException(ErrorCode.CART_NOT_FOUND));
    }

    public CartResponse getCart(User user){
        return CartMappers.fromCartToCartResponse(getCartOrThrow(user));
    }



}
