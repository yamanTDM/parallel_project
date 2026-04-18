package com.ITE.parallel_project.service;

import com.ITE.parallel_project.entity.Cart;
import com.ITE.parallel_project.entity.CartItem;
import com.ITE.parallel_project.entity.Product;
import com.ITE.parallel_project.repository.CartRepository;
import com.ITE.parallel_project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService {
    CartRepository cartRepository;
    ProductRepository productRepository;
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProductToCart(Cart cart, int productId, int quantity) {

        Product product = productRepository.findById(productId).orElseThrow();

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        CartItem existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);

            cart.addItem(cartItem);
        }

        cartRepository.save(cart);
    }

    public Cart getCartByUserId(int userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found for user"));
    }
}
