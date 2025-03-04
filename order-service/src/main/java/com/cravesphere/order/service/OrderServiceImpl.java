package com.cravesphere.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cravesphere.order.dto.OrderDto;
import com.cravesphere.order.dto.OrderResponseDto;
import com.cravesphere.order.dto.RestaurantDto;
import com.cravesphere.order.dto.UserDto;
import com.cravesphere.order.exception.OrderNotFoundException;
import com.cravesphere.order.model.Order;
import com.cravesphere.order.repository.OrderRepository;

import freemarker.core.JSONCFormat;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	private RestTemplate restTemplate;

	@Override
	public Order placeOrder(OrderDto orderDto) {
		LOGGER.info("Placing new order");
		
		Order order = new Order();
		order.setUserId(orderDto.getUser().getId());                  // To pass userId in UserDto of user service
		order.setRestaurantId(orderDto.getRestaurant().getId());      // To pass restaurantId in RestaurantDto of restaurant service
		order.setOrderTime(orderDto.getOrderTime());
		order.setStatus(orderDto.getStatus());
		order.setTotalAmount(orderDto.getTotalAmount());
		
		return orderRepository.save(order);
		
	}

	@Override
	public OrderResponseDto getOrderByOrderId(int orderId) {
		LOGGER.info("Fetching order by ID: " + orderId);
	    
	    // Fetch order from database
	    Order order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new OrderNotFoundException("Order with orderId " + orderId + " not found"));

	    // Fetch user details
	    UserDto user = restTemplate.getForObject("http://USER-SERVICE/api/users/" + order.getUserId(), UserDto.class);

	    // Fetch restaurant details
	    RestaurantDto restaurant = restTemplate.getForObject(
	    		"http://RESTAURANT-SERVICE/api/restaurants/" + order.getRestaurantId(), RestaurantDto.class);

	    LOGGER.info("Fetched User: " + user);
	    LOGGER.info("Fetched Restaurant: " + restaurant);

	    return OrderResponseDto.builder()
	            .orderId(order.getOrderId())
	            .totalAmount(order.getTotalAmount())
	            .status(order.getStatus())
	            .orderTime(order.getOrderTime())
	            .user(user)
	            .restaurant(restaurant)
	            .build();

	}

	@Override
	public List<Order> getOrdersByUserId(int userId) {
		LOGGER.info("fetching user by userId: " + userId);
		
		return orderRepository.findByUserId(userId);
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) {
		LOGGER.info("updating order status for id: " + orderId);
		
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order with orderId " + orderId + " not found"));
		
		order.setStatus(status);
		
		return orderRepository.save(order);
	}

}
