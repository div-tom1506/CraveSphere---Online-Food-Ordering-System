package com.cravesphere.user.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	private static final String SECRET_KEY = "mySecretKey";
	private static final int EXPIRATION_TIME = 86400000; // 1 day
	
	// Generate JWT token
	public String generateToken(String email) {
		
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	// Extract userName form JWT token
	public String extractEmail(String token) {
		return getClaims(token).getSubject();
	}
	
	// Validate token expiry
	public boolean isTokenValid(String token) {
		return getClaims(token).getExpiration().after(new Date());
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
	}
	
}
