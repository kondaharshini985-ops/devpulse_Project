package com.example.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.user.entity.User;



public interface UserRepo extends JpaRepository<User, Long> {
	
	 public Optional<User> findByEmail(String email);
	
	
	

}
