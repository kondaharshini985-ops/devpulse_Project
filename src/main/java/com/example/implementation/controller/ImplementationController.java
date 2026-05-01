package com.example.implementation.controller;




import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.implementation.dto.ImplementationDetailsDTO;
import com.example.implementation.dto.RequestImplementationDTO;
import com.example.implementation.dto.ResponseImplementationDTO;
import com.example.implentation.Service.ImplentationServiceInterface;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/implementations")
public class ImplementationController {

	private ImplentationServiceInterface service;
	
	public ImplementationController(ImplentationServiceInterface service) {
		this.service=service;
	}
	
	@PostMapping
	
		public ResponseImplementationDTO createImplementation(@Valid   @RequestBody RequestImplementationDTO req) {
			return service.createImplementation(req);
		}
	
	@GetMapping("/idea/{id}")
	public List<ImplementationDetailsDTO> getByIdea(@PathVariable Long id){
		return service.getImplementationById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseImplementationDTO update(@RequestBody RequestImplementationDTO dto,@PathVariable Long id) {
		return service.updateImplementation(dto,id);
	}
	@DeleteMapping("/{id}")
	public String deleteImp(@PathVariable Long id) {
		return service.deleteImp(id);
	}
	@GetMapping("/trending")
	public List<ImplementationDetailsDTO> trendingImplementations(){
		return service.trendingImplementations();
	}
	
	
	
	
		   
	
	
		
	}

