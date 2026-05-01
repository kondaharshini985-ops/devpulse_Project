package com.example.voting.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VotingRequestDTO {
//	
//	@NotNull(message="user_id should be present")
//	private Long User_id;
	@NotNull(message ="implementation_id is required")
	private Long implementation_id;
	
	
	
	
	
	
	

}
