package com.example.hexagonal.application.service;

import com.example.hexagonal.application.ports.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public void deleteOrder(Long id) {
        orderRepository.delete(id);
    }
}
