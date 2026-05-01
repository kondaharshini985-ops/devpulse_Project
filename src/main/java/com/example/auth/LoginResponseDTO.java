package com.example.auth;

import com.example.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
	
	private String email;
	private Role role;
	private String token;
	private String username;
	

}
