package org.acme.hexagonal.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.hexagonal.infraestructure.out.OrderRepository;
import org.acme.hexagonal.domain.model.Order;
import org.acme.hexagonal.domain.model.OrderItem;

import java.util.List;

@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    OrderRepository orderRepository;

    public Order createOrder(Order order) {
        orderRepository.persist(order);
        return order;
    }

    public void addItemToOrder(Long orderId, OrderItem item) {
        Order order = orderRepository.findById(orderId);

        if(order != null) {
            order.addItem(item);
            orderRepository.persist(order);
        }
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId);

        if(order != null) {
            order.updateStatus(status);
            orderRepository.persist(order);
        }
    }

    public List<Order> getAllOrders() {
       return orderRepository.listAll();
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
