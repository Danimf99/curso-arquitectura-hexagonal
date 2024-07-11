package com.example.hexagonal.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDateTime creationDate;
    private String status;

    public Order() {
    }

    public Order(LocalDateTime creationDate, String status) {
        this.creationDate = creationDate;
        this.status = status;
    }

    public Order(long id, String status, LocalDateTime now) {
        this.orderId = id;
        this.creationDate = now;
        this.status = status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
