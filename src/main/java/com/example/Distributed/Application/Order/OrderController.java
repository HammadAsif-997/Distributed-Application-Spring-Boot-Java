package com.example.Distributed.Application.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;

@Controller
public class OrderController {
    private final OrderAdapter orderAdapter;

    public OrderController(OrderAdapter orderAdapter) {
        this.orderAdapter = orderAdapter;
    }

    @PostMapping("/checkout")
    public String finalizeOrder(@RequestParam("totalPrice") BigDecimal totalPrice, Model model) {
        Order order = orderAdapter.finalizeOrder(totalPrice);
        model.addAttribute("order", order);
        return "order-success";
    }
}
