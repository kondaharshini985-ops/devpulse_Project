package com.example.idea.service;

import java.util.List;

import com.example.idea.dto.IdeaListResponseDTO;
import com.example.idea.dto.IdeaRequestDTO;
import com.example.idea.dto.IdeaResponseDTO;
import com.example.idea.dto.TrendingIdeaDTO;

public interface IdeaService {
	
	public IdeaListResponseDTO createIdea(IdeaRequestDTO i);
	
	public List<IdeaResponseDTO> getAllIdeas();

	public IdeaResponseDTO getIdeaById(Long id);
	
	public IdeaResponseDTO updateIdea(Long id,IdeaRequestDTO dto);
	
	public String deleteIdea(Long id);
	
	// more implementations for the Idea is the Trending idea
	public List<TrendingIdeaDTO> trendingIdeas();
	// most recently added idea is latest idea
	public List<IdeaResponseDTO> latestIdeas();

}
