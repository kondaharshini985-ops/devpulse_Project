package com.example.securityconfig;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.example.exception.ResourseNotFoundException;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	private final  UserRepo repo;
 public CustomUserDetailService(UserRepo repo) {
	 this.repo= repo;
 }
 
 @Override
 public UserDetails loadUserByUsername(String username) {

     User u = repo.findByEmail(username)
             .orElseThrow(() -> new ResourseNotFoundException(username));

     return org.springframework.security.core.userdetails.User
             .withUsername(u.getEmail())
             .password(u.getPassword())
             .authorities(u.getRole().name())   // ✅ IMPORTANT
             .build();
 }

}
