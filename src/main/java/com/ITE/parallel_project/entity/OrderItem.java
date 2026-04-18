package com.ITE.parallel_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class OrderItem {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Getter
    @Setter
    private int quantity;


    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
