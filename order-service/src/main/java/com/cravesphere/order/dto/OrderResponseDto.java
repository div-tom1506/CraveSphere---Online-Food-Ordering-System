package com.cravesphere.order.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {

	private int orderId;
    private Double totalAmount;
    private String status;
    private LocalDateTime orderTime;
    private UserDto user;
    private RestaurantDto restaurant;
}
