package com.example.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.LoginRequestDTO;
import com.example.auth.LoginResponseDTO;
import com.example.user.dto.UserDTORequest;
import com.example.user.dto.UserResponseDTO;
import com.example.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	private final UserService service;
	
	public UserController( UserService service) {
		this.service=service;
	}
	
	@PostMapping
	public UserResponseDTO saveUser(@Valid  @RequestBody UserDTORequest requser) {
		
		return service.createUser(requser);
		
	}
	
	
	@GetMapping
	public List<UserResponseDTO> getAllUsers(){
		return service.getUsers();
		
	}
	@GetMapping("/{id}")
	public UserResponseDTO getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public UserResponseDTO updateUser(@RequestBody UserDTORequest u ,@PathVariable Long id) {
		return service.updateUser(u,id);
	}
	 
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id) {
		return service.deleteUser(id);
	}
}
