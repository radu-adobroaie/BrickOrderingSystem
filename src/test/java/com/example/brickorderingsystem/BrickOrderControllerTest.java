package com.example.brickorderingsystem;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class BrickOrderControllerTest {
    @Mock
    BrickOrderService orderService;
    
    @InjectMocks
    BrickOrderController orderController;

    @Test
    void createOrder_withZeroBricks_throwsException() {
//        final OrderException actual;
//        
//        when(this.orderService.createOrder(0)).thenThrow(new OrderException());
//        
//        try {
//            this.orderController.createOrder(0);
//        } catch (OrderException e) {
//            actual = e;
//        }
//        
//        assertNotNull(actual);
    }
    
    @Test
    void createOrder_withNullBricks_throwsException() {
        
    }

    @Test
    void createOrder_withMultipleBricks_returnsReference() {
        
    }
    
    @Test
    void createOrder_withSameNumberOfBricks_returnsDifferentReference() {
        
    }
    
    @Test
    void getOrder_withWrongReference_throwsException() {

    }
    
    @Test
    void getOrder_withNullReference_throwsException() {
        
    }
    
    @Test
    void getOrder_withCorrectReference_returnsOrder() {
        
    }
    
    @Test
    void getOrders_withNoExistingOrders_throwsException() {
        
    }

    @Test
    void getOrders_withExistingOrders_returnsOrders() {

    }
}