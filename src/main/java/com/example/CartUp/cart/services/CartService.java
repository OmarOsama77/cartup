package com.example.CartUp.cart.services;

import com.example.CartUp.auth.entities.User;
import com.example.CartUp.auth.services.UserService;
import com.example.CartUp.cart.dots.request.AddProductRequest;
import com.example.CartUp.cart.dots.request.UpdateQuantityRequest;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public CartItemResponse updateCartItemQuantity(User user, UpdateQuantityRequest request) {
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(()->new ApplicationException(ErrorCode.CART_NOT_FOUND));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductVariantId(cart.getId(),request.getProductVariantId())
                .orElseThrow(()->new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND));

        validateQuantity(request.getProductVariantId(),request.getQuantity());

        cartItem.setQuantity(request.getQuantity());
       CartItem saved =  cartItemRepository.save(cartItem);

       return CartMappers.fromCartItemToCartItemResponse(saved);
    }


    public CartItemResponse addProductToCart(User user, AddProductRequest request) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> createCart(user));

        //make sure this product variant exists
        if (!productVariantService.existsById(request.getProductVariantId())) {
            throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        }


        Optional<CartItem> cartItem =
                cartItemRepository.findByCartIdAndProductVariantId(cart.getId(), request.getProductVariantId());
        if (cartItem.isPresent()) {
            //product is already in cart
            return updateCartItem(cartItem.get(), request.getQuantity());
        } else {
            //product is new to cart
            return addNewCartItem(request, cart);
        }


    }

    private CartItemResponse updateCartItem(CartItem cartItem, int additionalQuantity) {
        int newQuantity = cartItem.getQuantity() + additionalQuantity;
        validateQuantity(cartItem.getProductVariant().getId(), newQuantity);
        cartItem.setQuantity(newQuantity);
        CartItem saved = cartItemRepository.save(cartItem);
        return CartMappers.fromCartItemToCartItemResponse(saved);
    }

    private CartItemResponse addNewCartItem(AddProductRequest request, Cart cart) {
        validateQuantity(request.getProductVariantId(), request.getQuantity());
        //now we want to upload a new CartItem
        CartItem cartItem = CartItem
                .builder()
                .productVariant(productVariantService.findById(request.getProductVariantId()))
                .quantity(request.getQuantity())
                .cart(cart)
                .build();
        CartItem saved = cartItemRepository.save(cartItem);
        return CartMappers.fromCartItemToCartItemResponse(saved);
    }


    private Cart createCart(User user) {
        Cart cart = Cart.builder().user(user).build();
        user.setCart(cart);
        return cartRepository.save(cart);
    }

    @Transactional
    public void clearCart(UUID userId){
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(()->new ApplicationException(ErrorCode.USER_HAS_NO_CART));

        System.out.println("cart is "+cart.getId());
        cartItemRepository.deleteAllByCartId(cart.getId());
    }


    public void deleteProductFromCart(User user, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(()->new ApplicationException(ErrorCode.CART_ITEM_NOT_FOUND));
        if(!cartItem.getCart().getUser().getId().equals( user.getId())){
            throw new ApplicationException(ErrorCode.ACCESS_DENIED);
        }
        cartItemRepository.deleteById(cartItemId);
    }

    public void validateQuantity(Long productVariantId, int desiredQuantity) {
        int availableQuantity = inventoryService.getAvailableQuantity(productVariantId);
        if (availableQuantity < desiredQuantity) {

            throw new ApplicationException(ErrorCode.INSUFFICIENT_INVENTORY);
        }
    }

    public Cart getUserCart(UUID userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_HAS_NO_CART));
    }

    @Transactional
    public void deleteCart(Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.USER_HAS_NO_CART));
        System.out.println("Before delete, count = " + cartRepository.count());
        cartRepository.deleteById(cartId);
        System.out.println("After deleteById call, count = " + cartRepository.count());
        cartRepository.flush();
        System.out.println("After flush, count = " + cartRepository.count());
    }


}
