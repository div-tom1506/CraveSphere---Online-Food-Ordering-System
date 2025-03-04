package com.cravesphere.user.service;

import com.cravesphere.user.dto.AuthRequest;
import com.cravesphere.user.dto.AuthResponse;
import com.cravesphere.user.dto.UserDto;
import com.cravesphere.user.dto.UserResponse;

public interface UserService {
	
	String registerUser (UserDto userDto);
	
	AuthResponse authenticateUser(AuthRequest authRequest);
	
	UserResponse getUserById(int userId);
	
	UserResponse getUserByEmail(String email);
	
	String removeUser(int userId);

}
