package com.example.brickorderingsystem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrickOrderController {
    private final BrickOrderService orderService;

    public BrickOrderController(final BrickOrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/createOrder")
    public String createOrder(@RequestParam final int numberOfBricks) {
        return this.orderService.createOrder(numberOfBricks);
    }

    @GetMapping("/getOrder")
    public String getOrder(@RequestParam final String reference) {
//        return this.orderService.get(numberOfBricks);
        return "";
    }

    @GetMapping("/getOrders")
    public String getOrders(@RequestParam final String reference) {
//        return this.orderService.get(numberOfBricks);
        return "";
    }
}
