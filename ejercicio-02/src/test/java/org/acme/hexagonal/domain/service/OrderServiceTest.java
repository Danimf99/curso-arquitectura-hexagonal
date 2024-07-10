package org.acme.hexagonal.domain.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.hexagonal.application.service.OrderService;
import org.acme.hexagonal.domain.model.Order;
import org.acme.hexagonal.domain.model.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class OrderServiceTest {

    @Inject
    OrderService orderService;

    @Test
    public void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        assertNotNull(order);
    }

    @Test
    public void testAddItemOrder() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        OrderItem item = new OrderItem("product1", 2, new BigDecimal(("22.0")));
        orderService.addItemToOrder(order.getOrderId(), item);
        assertEquals(1, orderService.findOrderById(order.getOrderId()).getItems().size());
    }

    @Test
    public void testUpdateOrderStatus() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        orderService.createOrder(order);
        orderService.updateOrderStatus(order.getOrderId(), "CONFIRMED");
        assertEquals("CONFIRMED", orderService.findOrderById(order.getOrderId()).getStatus());
    }
}
