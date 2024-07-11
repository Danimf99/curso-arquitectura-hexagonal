package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateOrder() {
        Order order = new Order(LocalDateTime.now(), "Created");
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertEquals(order.getStatus(), createdOrder.getStatus());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrder() {
        Order order = new Order(1L, "Created", LocalDateTime.now());
        when(orderRepository.findById(1L)).thenReturn(order);

        Order fetchedOrder = orderService.getOrderById(1L);

        assertEquals(order.getStatus(), fetchedOrder.getStatus());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteOrder() {
        doNothing().when(orderRepository).delete(1L);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).delete(1L);
    }
}