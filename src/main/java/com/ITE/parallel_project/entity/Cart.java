package com.ITE.parallel_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();


    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

}