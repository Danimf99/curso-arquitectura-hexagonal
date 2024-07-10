package org.acme.hexagonal.infraestructure.in;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.hexagonal.domain.model.Order;
import org.acme.hexagonal.domain.model.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@QuarkusTest
public class OrderControllerTest {

    @Test
    public void testCreateOrderEndpoint() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                    .statusCode(201)
                    .body("status", is("PENDING"));
    }

    @Test
    public void testAddItemOrderEndpoint() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                    .statusCode(201);

        OrderItem orderItem = new OrderItem("product1", 2, new BigDecimal(("22.0")));
        given()
                .contentType("application/json")
                .body(orderItem)
                .when().post("/orders/{orderId}/items", order.getOrderId())
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateOrderStatusEndpoint() {
        Order order = new Order(LocalDateTime.now(), "PENDING");
        given()
                .contentType("application/json")
                .body(order)
                .when().post("/orders")
                .then()
                .statusCode(201);

        given()
                .contentType("application/json")
                .body("CONFIRMED")
                .when().post("/orders/{orderId}/status", order.getOrderId())
                .then()
                .statusCode(200)
                .body("status", is("CONFIRMED"));
    }
}
