package com.ITE.parallel_project.service;

import com.ITE.parallel_project.entity.Product;
import com.ITE.parallel_project.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public void buyProductUnSafe(Long productId, int quantity) {


        Product product = productRepository.findById(productId).orElseThrow();

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Out of stock");
        }

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);
    }
    @Transactional
    public void buyProduct(Long productId, int quantity) {


        Product product = productRepository.findIdLock(productId);

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Out of stock");
        }

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);
    }

    public void addProduct(String name, int quantity) {
        Product product = new Product();
        if(name == null ){
            throw new RuntimeException("Product name is null");
        }
        if(quantity <= 0){
            throw new RuntimeException("Quantity is negative");
        }
        product.setName(name);
        product.setQuantity(quantity);
        productRepository.save(product);
    }
}
