package com.example.hexagonal.application.ports;

import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

public interface OrderRepository {

    Order save(Order order);
    Order findById(Long id);

    List<Order> findAll();

    void delete(Long id);

}
