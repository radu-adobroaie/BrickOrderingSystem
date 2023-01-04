package com.example.brickorderingsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
                .withMessage("Illegal argument: empty or null reference");
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
}