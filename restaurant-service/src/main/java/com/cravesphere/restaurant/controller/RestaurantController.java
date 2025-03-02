package com.cravesphere.restaurant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cravesphere.restaurant.dto.RestaurantDto;
import com.cravesphere.restaurant.service.RestaurantService;

@RestController
@RequestMapping("restaurants")
public class RestaurantController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping("/add")
	public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto restaurantDto) {
		LOGGER.info("Received request to add new restaurant: " + restaurantDto.getName());

		return ResponseEntity.ok(restaurantService.addRestaurant(restaurantDto));
	}

	@GetMapping()
	public ResponseEntity<List<RestaurantDto>> getAllRestaurant() {
		LOGGER.info("Received request to fetch all active restaurants");

		return ResponseEntity.ok(restaurantService.getAllRestaurants());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable int id) {
		LOGGER.info("Recevied request to get restaurant by ID: " + id);

		return ResponseEntity.ok(restaurantService.getRestaurantById(id));
	}

	@GetMapping("/{name}")
	public ResponseEntity<RestaurantDto> getRestaurantByName(@PathVariable String name) {
		LOGGER.info("Recevied request to get restaurant by name: " + name);

		return ResponseEntity.ok(restaurantService.getRestaurantByName(name));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable int id,
			@RequestBody RestaurantDto restaurantDto) {
		LOGGER.info("Received request to update restaurant with ID: " + id);

		return ResponseEntity.ok(restaurantService.updateRestaurants(id, restaurantDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
		LOGGER.info("Received request to delete restaurant with ID: " + id);

		return ResponseEntity.ok(restaurantService.deleteRestaurant(id));
	}

}
