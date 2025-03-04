package com.cravesphere.order.service;

import java.util.List;

import com.cravesphere.order.dto.OrderDto;
import com.cravesphere.order.dto.OrderResponseDto;
import com.cravesphere.order.model.Order;

public interface OrderService {
	
	Order placeOrder(OrderDto orderDto);
	
	OrderResponseDto getOrderByOrderId(int orderId);
	
	List<Order> getOrdersByUserId(int userId);
	
	Order updateOrderStatus(int orderId, String status);

}
