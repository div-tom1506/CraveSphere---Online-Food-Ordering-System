package com.cravesphere.order.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
	
	private UserDto user;
	private RestaurantDto restaurant;
	private String status;
	private Double totalAmount;
	private LocalDateTime orderTime;

}
