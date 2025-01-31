package com.example.Distributed.Application.Order;

import java.math.BigDecimal;

public class Order {
    private BigDecimal totalPrice;
    private String userId;

    public Order(BigDecimal totalPrice, String userId) {
        this.totalPrice = totalPrice;
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getUserId() {
        return userId;
    }
}

