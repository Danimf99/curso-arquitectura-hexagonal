package org.acme.hexagonal.infraestructure.out;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.hexagonal.domain.model.Order;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Order> {
}
