package com.example.voting.service;

import com.example.voting.DTO.VotingRequestDTO;
import com.example.voting.DTO.VotingResponseDTO;

public interface VotingService {
	
	public VotingResponseDTO toggleVote(VotingRequestDTO dto);
	


}
