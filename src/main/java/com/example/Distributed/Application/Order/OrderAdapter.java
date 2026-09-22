package com.example.Distributed.Application.Order;

import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class OrderAdapter {
    private final OrderFacade orderFacade;
    private final EmailService emailService;

    public OrderAdapter(OrderFacade orderFacade, EmailService emailService) {
        this.orderFacade = orderFacade;
        this.emailService = emailService;
    }

    public Order finalizeOrder(BigDecimal totalPrice) {
        Order order = orderFacade.finalizeOrder(totalPrice);
        emailService.sendEmail(order.getUserId());
        return order;
    }
}

