package com.cravesphere.order.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cravesphere.order.dto.OrderDto;
import com.cravesphere.order.dto.OrderResponseDto;
import com.cravesphere.order.model.Order;
import com.cravesphere.order.service.OrderService;
import com.cravesphere.order.service.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
    public Order placeOrder(@RequestBody OrderDto orderDto) {
		LOGGER.info("Received request to place new order");
		
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrderById(@PathVariable int orderId) {
    	LOGGER.info("Recevied request to get order by ID: " + orderId);
    	
        return orderService.getOrderByOrderId(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable int userId) {
    	LOGGER.info("Recevied request to get orders by userId: " + userId);
    	
        return orderService.getOrdersByUserId(userId);
    }

    @PutMapping("/{orderId}/status")
    public Order updateOrderStatus(@PathVariable int orderId, @RequestParam String status) {
    	LOGGER.info("Received request to update status of order for orderId: " + orderId);
    	
        return orderService.updateOrderStatus(orderId, status);
    }
	
}
