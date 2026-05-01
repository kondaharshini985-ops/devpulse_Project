package com.example.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.exception.ResourseNotFoundException;
import com.example.user.dto.UserDTORequest;
import com.example.user.dto.UserResponseDTO;
import com.example.user.entity.Role;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;
@Service
public class UserServiceImpl implements UserService{
	
	
	private final UserRepo userRepo;
	private final PasswordEncoder passwordencoder; /// watch we are adding passwordEncoder Class
	 
	public UserServiceImpl (UserRepo repo,PasswordEncoder passwordEncoder) {
		this.userRepo =repo;
		this.passwordencoder= passwordEncoder;
	}

	@Override
	public UserResponseDTO createUser(UserDTORequest dto) {
		
		Role role =Role.ROLE_USER;
		if (dto.getEmail().equals("admin@gmail.com")) {
	        role = Role.ROLE_ADMIN;
	    }

		
		User u = User.builder()
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(passwordencoder.encode(dto.getPassword()))
				.role(role)
				.build();
		
		User savedUser = userRepo.save(u);
	
		return  new UserResponseDTO(
				savedUser.getId(),
				savedUser.getUsername(),
				savedUser.getRole().name(),
				savedUser.getEmail());
		
		
		
	}

	@Override
	public List<UserResponseDTO> getUsers() {
		List<UserResponseDTO> users = new ArrayList<>();
		List<User> allusers = userRepo.findAll();
	
		
	for(User u :allusers) {
		UserResponseDTO dto = new UserResponseDTO(u.getId(),u.getUsername(),u.getRole().name(),u.getEmail());
		users.add(dto);
	}
		return users;
	}

	@Override
	public UserResponseDTO getUserById(Long l) {
		User u =userRepo.findById(l).orElseThrow(()->new ResourseNotFoundException("user not found"));
		return  new UserResponseDTO(u.getId(),u.getUsername(),u.getRole().name(),u.getEmail()) ;
	}

	@Override
	public UserResponseDTO updateUser(UserDTORequest u, Long id) {
		User user = userRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("user not found"));
		user.setUsername(u.getUsername());
		user.setEmail(u.getEmail());
		user.setPassword(passwordencoder.encode(u.getPassword()));
		
		User updatedUser = userRepo.save(user);
				
		return  new UserResponseDTO(updatedUser.getId(),updatedUser.getUsername(),updatedUser.getRole().name(),updatedUser.getEmail());
	}

	@Override
	public String deleteUser(Long id) {
		User u =userRepo.findById(id).orElseThrow(()->new ResourseNotFoundException("user not found"));
		userRepo.delete(u);
		return "user deleted successfully";
	}
	
	
	
	
	
	

}
