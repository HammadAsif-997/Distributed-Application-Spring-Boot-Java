package com.example.Distributed.Application.Order;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.Distributed.Application.User.UserService;

import java.math.BigDecimal;

@Service
public class OrderService {
    private final UserService userService;

    public OrderService(@Lazy UserService userService) {
        this.userService = userService;
    }


    public Order finalizeOrder(BigDecimal totalPrice, String userId) {
        return new Order(totalPrice, userId);
    }

    public Order finalizeOrderWithTotal(BigDecimal total) {
        String userId = userService.getUserId();
        return new Order(total, userId);
    }

    public Order getRecentOrderForUser(String userId) {
        return new Order(BigDecimal.ZERO, userId);
    }
}

