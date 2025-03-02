package com.cravesphere.restaurant.service;

import java.util.List;

import com.cravesphere.restaurant.dto.RestaurantDto;

public interface RestaurantService {

	RestaurantDto addRestaurant(RestaurantDto restaurantDto);
	
	List<RestaurantDto> getAllRestaurants();
	
	RestaurantDto getRestaurantById(int id);
	
	RestaurantDto getRestaurantByName(String name);
	
	RestaurantDto updateRestaurants(int id, RestaurantDto restaurantDto);
	
	String deleteRestaurant(int id);
}
