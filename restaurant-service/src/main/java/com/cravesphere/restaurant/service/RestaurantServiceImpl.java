package com.cravesphere.restaurant.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cravesphere.restaurant.dto.RestaurantDto;
import com.cravesphere.restaurant.exception.RestaurantNotFoundException;
import com.cravesphere.restaurant.model.Restaurant;
import com.cravesphere.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantServiceImpl.class);

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
		LOGGER.info("adding new restaurant: " + restaurantDto.getName());

		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantDto.getName());
		restaurant.setLocation(restaurantDto.getLocation());
		restaurant.setCuisineType(restaurantDto.getCuisineType());
		restaurant.setActive(restaurantDto.isActive());
		restaurantRepository.save(restaurant);

		return restaurantDto;
	}

	@Override
	public List<RestaurantDto> getAllRestaurants() {
		LOGGER.info("fetching all active restaurant");

		return restaurantRepository.findByIsActiveTrue()
				.stream()
				.map(r -> new RestaurantDto(r.getName(), r.getLocation(), r.getCuisineType(), r.isActive()))
				.collect(Collectors.toList());

	}

	@Override
	public RestaurantDto getRestaurantById(int id) {
		LOGGER.info("fetching restaurant by ID: " + id);

		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID " + id + " not found"));

		return new RestaurantDto(restaurant.getName(), restaurant.getLocation(), restaurant.getCuisineType(),
				restaurant.isActive());
	}

	@Override
	public RestaurantDto getRestaurantByName(String name) {
		LOGGER.info("fetching restaurant by name: " + name);

		Restaurant restaurant = restaurantRepository.findByName(name)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant with name " + name + " not found"));

		return new RestaurantDto(restaurant.getName(), restaurant.getLocation(), restaurant.getCuisineType(),
				restaurant.isActive());
	}

	@Override
	public RestaurantDto updateRestaurants(int id, RestaurantDto restaurantDto) {
		LOGGER.info("updating restaurant data for id: " + id);

		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException("Restaurant with ID " + id + " not found"));

		restaurant.setName(restaurantDto.getName());
		restaurant.setLocation(restaurantDto.getLocation());
		restaurant.setCuisineType(restaurantDto.getCuisineType());
		restaurant.setActive(restaurantDto.isActive());
		restaurantRepository.save(restaurant);

		return restaurantDto;
	}

	@Override
	public String deleteRestaurant(int id) {
		LOGGER.info("removing restaurant with id: " + id);

		if (!restaurantRepository.existsById(id)) {
			LOGGER.error("Restaurant not found with id: " + id);
			throw new RestaurantNotFoundException("Restaurant with ID " + id + " not found");
		}

		restaurantRepository.deleteById(id);
		return "Restaurant with ID " + id + " is removed successfully";

	}

}
