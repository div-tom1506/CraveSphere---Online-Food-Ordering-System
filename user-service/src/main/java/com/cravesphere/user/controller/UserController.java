package com.cravesphere.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cravesphere.user.dto.AuthRequest;
import com.cravesphere.user.dto.AuthResponse;
import com.cravesphere.user.dto.UserDto;
import com.cravesphere.user.dto.UserResponse;
import com.cravesphere.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {

		LOGGER.info("Received request to register user: " + userDto.getEmail());

		return ResponseEntity.ok(userService.registerUser(userDto));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {

		LOGGER.info("Received request to register user: " + authRequest.getEmail());

		return ResponseEntity.ok(userService.authenticateUser(authRequest));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") int userId) {
		LOGGER.info("Recevied request to get user by ID: " + userId);
		
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable String email) {
		LOGGER.info("Recevied request to get user by name: " + email);
		
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUser(@PathVariable("id") int userId) {
		LOGGER.info("Received request to remove user with ID: " + userId);
		
		return ResponseEntity.ok(userService.removeUser(userId));
	}

}
