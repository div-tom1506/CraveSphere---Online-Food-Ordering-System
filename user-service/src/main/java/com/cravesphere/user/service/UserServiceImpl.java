package com.cravesphere.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cravesphere.user.dto.AuthRequest;
import com.cravesphere.user.dto.AuthResponse;
import com.cravesphere.user.dto.UserDto;
import com.cravesphere.user.dto.UserResponse;
import com.cravesphere.user.exception.EmailAlreadyExistsException;
import com.cravesphere.user.exception.UserNotFoundException;
import com.cravesphere.user.model.User;
import com.cravesphere.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public String registerUser(UserDto userDto) {

		if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {

			LOGGER.info("email already exists: " + userDto.getEmail());
			throw new EmailAlreadyExistsException("User with " + userDto.getEmail() + " already exists");
		}

		LOGGER.info("Registering new user: " + userDto.getName());

		User user = new User();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);

		return "User registered successfully!";

	}

	@Override
	public AuthResponse authenticateUser(AuthRequest authRequest) {

		LOGGER.info("Authenticating user: " + authRequest.getEmail());

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		User user = userRepository.findByEmail(authRequest.getEmail())
				.orElseThrow(
						() -> new UserNotFoundException("User with email " + authRequest.getEmail() + " not found !"));

		String token = jwtUtil.generateToken(user.getEmail());
		return new AuthResponse(token);

	}

	@Override
	public UserResponse getUserById(int userId) {
		LOGGER.info("fetching user by ID: " + userId);
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
		
		return new UserResponse(user.getId(), user.getName(), user.getEmail());
	}

	@Override
	public UserResponse getUserByEmail(String email) {
		LOGGER.info("fetching user by email id: " + email);
		
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
		
		return new UserResponse(user.getId(), user.getName(), user.getEmail());
	}

	@Override
	public String removeUser(int userId) {
		LOGGER.info("removing user with id: " + userId);
		
		if (!userRepository.existsById(userId)) {
			LOGGER.error("User not found with id: " + userId);
			throw new UserNotFoundException("User with ID " + userId + " not found");
		}
		
		userRepository.deleteById(userId);
		return "User with ID " + userId + " is removed successfully";
	}
	
}
