package com.ITE.parallel_project.controller;


import com.ITE.parallel_project.dto.AddProductRequest;
import com.ITE.parallel_project.dto.AddToCartRequest;
import com.ITE.parallel_project.entity.Cart;
import com.ITE.parallel_project.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/add")
    public String addProductToCart(
            @RequestParam int userId,
            @RequestBody AddToCartRequest addToCartRequest
            ) {

        Cart cart = cartService.getCartByUserId(userId);

        cartService.addProductToCart(cart, addToCartRequest.getProductId(),  addToCartRequest.getQuantity());

        return "Product added successfully";



    }
}
