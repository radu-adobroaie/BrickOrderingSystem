package com.example.brickorderingsystem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrickOrderService {
    private final Map<String, Order> ordersByReference = new HashMap<>();

    public String createOrder(final int numberOfBricks) {
        final Order order = new Order(numberOfBricks);
        final String id = order.getId();

        ordersByReference.put(id, order);

        return id;
    }

    public Order getOrder(final String reference) {
        return ordersByReference.get(reference);
    }

    public List<Order> getOrders() {
        return ordersByReference.values().stream().toList();
    }
}
