package com.example.voting.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.voting.DTO.VotingRequestDTO;
import com.example.voting.DTO.VotingResponseDTO;
import com.example.voting.service.VotingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/votes")
public class VotingController {
	
	VotingService service;
	
	public VotingController(VotingService service) {
		this.service= service;
	}
	@PostMapping("/toggle")
	public VotingResponseDTO toggleVote(@Valid @RequestBody VotingRequestDTO v ) {
		return service.toggleVote(v);
	}
	

}
