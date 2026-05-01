package com.example.voting.service;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.exception.ResourseNotFoundException;
import com.example.implementation.entity.Implementation;
import com.example.implementation.repository.ImplementationRepo;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;
import com.example.voting.DTO.VotingRequestDTO;
import com.example.voting.DTO.VotingResponseDTO;
import com.example.voting.entity.Voting;
import com.example.voting.repository.Votingrepo;

@Service
public class VotingServiceImpl implements VotingService{
	
	
	private final Votingrepo voteRepo;
	private UserRepo userRepo;
	private ImplementationRepo impRepo;
	
	public VotingServiceImpl(Votingrepo repo,UserRepo userRepo,ImplementationRepo impRepo) {
		this.voteRepo=repo;
		this.userRepo=userRepo;
		this.impRepo=impRepo;
		
	}
	private User getCurrentUser() {
	    String email = SecurityContextHolder
	            .getContext()
	            .getAuthentication()
	            .getName();

	    return userRepo.findByEmail(email).orElseThrow(()->new ResourseNotFoundException("user not found"));
	            
	}
	@Override
	public VotingResponseDTO toggleVote(VotingRequestDTO dto) {
		
	
		   User user =getCurrentUser();
		    Long implementationId = dto.getImplementation_id();

		    // check if vote already exists
		    boolean exists = voteRepo
		            .existsByUser_IdAndImplementation_Id(user.getId(), implementationId);

		    if (exists) {
		        // 🔴 UNVOTE (delete)
		    	Implementation imp = impRepo.findById(implementationId)
		                .orElseThrow(() -> new ResourseNotFoundException("Implementation not found"));

		        Voting vote = voteRepo
		                .findByUser_IdAndImplementation_Id(user.getId(), implementationId)
		                .orElseThrow();
		            

		        voteRepo.delete(vote);

		        Integer count = voteRepo.countByImplementation(imp);

		        return new VotingResponseDTO("Vote removed", count);

		    } else {
		        // 🟢 VOTE (create)

		       
		        Implementation imp = impRepo.findById(implementationId)
		                .orElseThrow(() -> new ResourseNotFoundException("Implementation not found"));

		        Voting vote = Voting.builder()
		                .user(user)
		                .implementation(imp)
		                .build();

		        voteRepo.save(vote);

		        Integer count = voteRepo.countByImplementation(imp);

		        return new VotingResponseDTO("Voted successfully", count);
		    }
		    
		}
	
	
	
		
		
	}

	
		


