package com.cravesphere.restaurant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
	
	private int restaurantId;
	private String name;
	private String location;
	private String cuisineType;
	private boolean isActive;

}
