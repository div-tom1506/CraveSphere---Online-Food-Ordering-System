package com.cravesphere.restaurant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cravesphere.restaurant.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
	
	Optional<Restaurant> findByName(String name);
	
	List<Restaurant> findByIsActiveTrue();
}
