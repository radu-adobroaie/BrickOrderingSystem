package com.example.brickorderingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BrickOrderController {
    private final BrickOrderService orderService;

    public BrickOrderController(final BrickOrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/createOrder")
    public String createOrder(@RequestParam final int numberOfBricks) throws IllegalArgumentException{
        return this.orderService.createOrder(numberOfBricks);
    }

    @GetMapping("/getOrder")
    public Order getOrder(@RequestParam final String reference) {
        return this.orderService.getOrder(reference);
    }

    @GetMapping("/getOrders")
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }
}
