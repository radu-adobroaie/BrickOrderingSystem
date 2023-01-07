package com.example.brickorderingsystem;

import controllers.BrickOrderController;
import entities.Order;
import errorHandling.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.BrickOrderService;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Normally I would have mocked the order service here and made this a unit test.
 * However, since I'm pressed for time I decided to write integration tests instead.
 */
class BrickOrderControllerTest {
    BrickOrderController orderController;

    @BeforeEach
    void beforeEach() {
        this.orderController = new BrickOrderController(new BrickOrderService());
    }

    @Test
    void createOrder_withZeroBricks_throwsException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.createOrder(0))
                .withMessage(OrderException.ILLEGAL_ARG_ZERO_OR_NULL);
    }

    @Test
    void createOrder_withMultipleBricks_returnsReference() {
        final String response = this.orderController.createOrder(3);
        assertThat(response).isInstanceOf(String.class);
    }
    
    @Test
    void createOrder_withSameNumberOfBricks_returnsDifferentReference() {
        final String firstReference = this.orderController.createOrder(1);
        final String secondReference = this.orderController.createOrder(2);

        assertThat(firstReference).isNotEqualTo(secondReference);
    }
    
    @Test
    void getOrder_withWrongReference_throwsException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.getOrder(null))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }
    
    @Test
    void getOrder_withNullReference_throwsException() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.getOrder(""))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }
    
    @Test
    void getOrder_withCorrectReference_returnsOrder() {
        final String reference = this.orderController.createOrder(3);
        final Order response = this.orderController.getOrder(reference);

        assertThat(response).isInstanceOf(Order.class);
        assertThat(response.getId()).isEqualTo(reference);
    }
    
    @Test
    void getOrders_withNoExistingOrders_throwsException() {
        // TODO: Do we really want the system to break for no references?
        assertThatThrownBy(() -> this.orderController.getOrders())
                .isInstanceOf(OrderException.class)
                .hasMessage(OrderException.NO_EXISTING_ORDERS);
    }

    @Test
    void getOrders_withExistingOrders_returnsOrders() {
        final String firstReference = this.orderController.createOrder(1);
        final String secondReference = this.orderController.createOrder(2);

        assertThat(this.orderController.getOrders().stream().map(Order::getId))
                .contains(firstReference, secondReference);
    }

    @Test
    void updateOrders_withNoExistingOrders_throwsException() {
        assertThatThrownBy(() -> this.orderController.updateOrder("reference", 1))
                .isInstanceOf(OrderException.class)
                .hasMessage(OrderException.NO_EXISTING_ORDERS);
    }

    @Test
    void updateOrder_withZeroBricks_throwsException() {
        final String reference = this.orderController.createOrder(1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.updateOrder(reference, 0))
                .withMessage(OrderException.ILLEGAL_ARG_ZERO_OR_NULL);
    }

    @Test
    void updateOrder_withWrongReference_throwsException() {
        this.orderController.createOrder(2);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.updateOrder("", 1))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }

    @Test
    void updateOrder_withNullReference_throwsException() {
        this.orderController.createOrder(2);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.updateOrder(null, 1))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }

    @Test
    void updateOrder_withCorrectReferenceAndOrder_returnsReference() {
        final String reference = this.orderController.createOrder(3);
        final String response = this.orderController.updateOrder(reference, 2);

        assertThat(response).isEqualTo(reference);
    }

    @Test
    void fulfilOrder_withWrongReference_throwsException() {
        this.orderController.createOrder(2);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.fulfilOrder(""))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }

    @Test
    void fulfilOrder_withNullReference_throwsException() {
        this.orderController.createOrder(2);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> this.orderController.fulfilOrder(null))
                .withMessage(OrderException.ILLEGAL_ARG_NO_SUCH_REFERENCE);
    }

    @Test
    void fulfilOrder_withDispatchedOrder_throwsException() {
        final String reference = this.orderController.createOrder(3);
        this.orderController.fulfilOrder(reference);

        assertThatThrownBy(() -> this.orderController.fulfilOrder(reference))
                .isInstanceOf(OrderException.class)
                .hasMessage(OrderException.ALREADY_DISPATCHED);
    }

    @Test
    void fulfilOrder_withCorrectReference_DispatchesOrder() {
        final String reference = this.orderController.createOrder(3);

        final Order order = this.orderController.getOrder(reference);
        final ResponseEntity<?> response = this.orderController.fulfilOrder(reference);

        assertTrue(order.isDispatched());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
    }
}