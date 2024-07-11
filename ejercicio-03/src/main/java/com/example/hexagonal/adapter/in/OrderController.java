package com.example.hexagonal.adapter.in;

import com.example.hexagonal.application.service.OrderService;
import com.example.hexagonal.domain.model.Order;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @POST
    public Response createOrder(Order order) {
        orderService.createOrder(order);

        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("orderId") Long orderId) {
        return orderService.getOrderById(orderId);
    }
}
