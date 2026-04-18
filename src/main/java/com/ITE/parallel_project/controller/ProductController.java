package com.ITE.parallel_project.controller;


import com.ITE.parallel_project.dto.AddProductRequest;
import com.ITE.parallel_project.dto.BuyProductRequest;
import com.ITE.parallel_project.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/buy")
    public String buyProduct(
           @RequestBody BuyProductRequest buyProductRequest
    ){
        productService.buyProduct(buyProductRequest.getId(),buyProductRequest.getQuantity());

        return "Product Bought Successfully";
    }
    @PostMapping("/add")
    public String addProduct(
            @RequestBody AddProductRequest addProductRequest
            ) {
        productService.addProduct(addProductRequest.getName(), addProductRequest.getQuantity());
        return "Product Added Successfully";
    }
    @PostMapping("/simulate")
    public String simulate() throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(50);

        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                productService.buyProduct(1, 1);
            });
        }
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                productService.buyProductUnSafe(2, 1);
            });
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        return "Done";
    }
}
