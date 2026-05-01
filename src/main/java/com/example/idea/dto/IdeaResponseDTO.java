package com.example.idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdeaResponseDTO {
	
	private long id;
	private String title;
	 private String description;
	 private String techstack;
	 private String difficulty;
	 private long user_id;

}
