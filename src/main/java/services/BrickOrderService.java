package services;

import exceptions.OrderException;
import entities.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BrickOrderService {
    private final Map<String, Order> ordersByReference;

    public BrickOrderService() {
        this.ordersByReference = new HashMap<>();
    }

    public String createOrder(final int numberOfBricks) {
        if (numberOfBricks == 0) {
            throw new IllegalArgumentException(OrderException.ILLEGAL_ARG_ZERO_OR_NULL);
        }

        final Order order = new Order(numberOfBricks);
        final String id = order.getId();

        ordersByReference.put(id, order);

        return id;
    }

    public Order getOrder(final String reference) {
        if (!ordersByReference.containsKey(reference)) {
            throw new IllegalArgumentException(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
        }

        return ordersByReference.get(reference);
    }

    public List<Order> getOrders() {
        if (ordersByReference.isEmpty()) {
            throw new OrderException(OrderException.NO_EXISTING_ORDERS);
        }

        return ordersByReference.values().stream().toList();
    }

    /**
     * Initially wanted this method to create a new reference for the order
     * but soon realised this approach wasn't scalable.
     * (what if we want to add order history in the future?)
     */
    public String updateOrder(final String reference, final int numberOfBricks) {
        if (this.ordersByReference.isEmpty()) {
            throw new OrderException(OrderException.NO_EXISTING_ORDERS);
        }

        if (!this.ordersByReference.containsKey(reference)) {
            throw new IllegalArgumentException(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
        }

        if (numberOfBricks == 0) {
            throw new IllegalArgumentException(OrderException.ILLEGAL_ARG_ZERO_OR_NULL);
        }

        this.ordersByReference.put(reference, new Order(reference, numberOfBricks));
        return reference;
    }
}
