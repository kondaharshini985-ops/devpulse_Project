package com.example.idea.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.idea.dto.IdeaListResponseDTO;
import com.example.idea.dto.IdeaRequestDTO;
import com.example.idea.dto.IdeaResponseDTO;
import com.example.idea.dto.TrendingIdeaDTO;
import com.example.idea.service.IdeaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ideas")
public class IdeaController {
	
	private final IdeaService service;
	
	public IdeaController(IdeaService service) {
		this.service=service;
	}
	
	@PostMapping
	public IdeaListResponseDTO ideacreate(@Valid @RequestBody IdeaRequestDTO idea) {
		return service.createIdea(idea);
	}
	
	@GetMapping
	public List<IdeaResponseDTO> getIdea(){
		return service.getAllIdeas();
	}
	
	@GetMapping("/{id}")
	public IdeaResponseDTO  getIdeaById(@PathVariable Long id) {
		return service.getIdeaById(id);
	}
	
	@PutMapping("/{id}")
	public IdeaResponseDTO updateIdea(@PathVariable Long i,@RequestBody IdeaRequestDTO dto) {
	
	return service.updateIdea(i,dto);
	
	}
	
	@DeleteMapping("/{id}")
	public  String  deleteIdea(@PathVariable Long id) {
		return service.deleteIdea(id);
	}
	
	//Trending Idea based on implementation count
	
	@GetMapping("/trending")
	public List<TrendingIdeaDTO> getTrendingIdeas(){
		return  service.trendingIdeas();
		
	}
	
	
	//Latest idea based on the latest ids
	 @GetMapping("/latest")
	 public List<IdeaResponseDTO> getLatestIdeas(){
		return  service.latestIdeas();
		
	 }
	 
	 

}
