package com.cravesphere.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException {
	
	public RestaurantNotFoundException(String message) {
		super(message);
	}

}
