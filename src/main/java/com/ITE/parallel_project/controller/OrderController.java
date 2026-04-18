package com.ITE.parallel_project.controller;


import com.ITE.parallel_project.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController
{
    private final OrderService orderService;
    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping("/confirm")
    public String confirmOrder(@RequestParam int user_id)
    {
        orderService.confirmOrder(user_id);
        return "Order confirmed";
    }
}
