package com.example.user.service;

import java.util.List;

import com.example.user.dto.UserDTORequest;
import com.example.user.dto.UserResponseDTO;
import com.example.user.entity.User;

public interface UserService {

	public UserResponseDTO createUser(UserDTORequest dto);
	public List<UserResponseDTO> getUsers(); 
	public UserResponseDTO getUserById(Long l);
	public UserResponseDTO updateUser(UserDTORequest u , Long id);
	public String deleteUser(Long id);
	 
}
