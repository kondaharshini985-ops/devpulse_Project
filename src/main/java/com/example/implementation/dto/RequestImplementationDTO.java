package com.example.implementation.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestImplementationDTO {
	
	@NotBlank(message="Github url  is required")
	private String githuburl;
	
//	@NotNull
//	private Long userId;
	
	@NotNull
	private Long ideaId;
	


}
