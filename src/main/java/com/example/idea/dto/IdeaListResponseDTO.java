package com.example.idea.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IdeaListResponseDTO {
	
	private String message;
	private List<IdeaResponseDTO> similarIdeas;
	private IdeaResponseDTO createIdea;
	

}
