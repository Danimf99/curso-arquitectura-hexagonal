package com.example.hexagonal.adapter.out;

import com.example.hexagonal.application.ports.OrderRepository;
import com.example.hexagonal.domain.model.Order;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@ApplicationScoped
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Order save(Order order) {
        if(order.getOrderId() == null) {
            entityManager.persist(order);
            return order;
        } else {
           return entityManager.merge(order);
        }
    }

    @Override
    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(findById(id));
    }
}
