package com.example.brickorderingsystem;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BrickOrderService {
    private final Map<String,Integer> orderByReferences = new HashMap<>();

    public String createOrder(final int numberOfBricks) {
        final Order order = new Order(numberOfBricks);
        final String id = order.getId();

        orderByReferences.put(id, order.getNumberOfBricks());

        return id;
    }
}
