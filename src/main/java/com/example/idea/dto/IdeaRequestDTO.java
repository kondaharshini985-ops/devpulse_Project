package com.example.idea.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdeaRequestDTO {
    
	@NotBlank(message ="title is required")
	
	private String title;
	
	@NotBlank(message ="description is required")
	private String description;
	
	@NotBlank(message ="teckstack should be mentioned")
	private String techstack;
	
	@NotBlank(message="mention level of difficulty")
	private String difficulty;
	
// 	private long user_id;
}
