package com.cravesphere.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cravesphere.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
