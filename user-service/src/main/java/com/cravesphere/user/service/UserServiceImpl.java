package com.cravesphere.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cravesphere.user.dto.AuthRequest;
import com.cravesphere.user.dto.AuthResponse;
import com.cravesphere.user.dto.UserDto;
import com.cravesphere.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthResponse authenticateUser(AuthRequest authRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
