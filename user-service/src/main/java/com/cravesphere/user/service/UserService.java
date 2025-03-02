package com.cravesphere.user.service;

import com.cravesphere.user.dto.AuthRequest;
import com.cravesphere.user.dto.AuthResponse;
import com.cravesphere.user.dto.UserDto;

public interface UserService {
	
	String registerUser (UserDto userDto);
	
	AuthResponse authenticateUser(AuthRequest authRequest);

}
