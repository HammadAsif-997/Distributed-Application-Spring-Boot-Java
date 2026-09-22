package com.example.Distributed.Application.Order;

import org.springframework.stereotype.Component;

import com.example.Distributed.Application.User.UserService;

import java.math.BigDecimal;

@Component
public class OrderFacade {
    private final OrderService orderService;
    private final UserService userService;

    public OrderFacade(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    public Order finalizeOrder(BigDecimal totalPrice) {
        String userId = userService.getUserId();
        return orderService.finalizeOrder(totalPrice, userId);
    }
}
