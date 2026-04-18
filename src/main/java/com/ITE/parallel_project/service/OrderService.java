package com.ITE.parallel_project.service;

import com.ITE.parallel_project.entity.*;
import com.ITE.parallel_project.repository.CartRepository;
import com.ITE.parallel_project.repository.OrderRepository;
import com.ITE.parallel_project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {
    OrderRepository orderRepository;
    ProductRepository productRepository;
    CartRepository cartRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public void confirmOrder(int user_id) {
        Cart cart = cartRepository.findByUserId(user_id).orElseThrow();

        Order order = new Order();
        order.setUser(cart.getUser());


        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        for (CartItem cartItem : cart.getItems()) {

            Product product = productRepository.findProductAndLock(
                    cartItem.getProduct().getId()
            );

            if (product.getQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Product out of stock: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - cartItem.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());

            order.addItem(orderItem);
        }

        orderRepository.save(order);

        cart.getItems().clear();
    }
}

