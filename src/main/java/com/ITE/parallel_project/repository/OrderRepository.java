package com.ITE.parallel_project.repository;

import com.ITE.parallel_project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
