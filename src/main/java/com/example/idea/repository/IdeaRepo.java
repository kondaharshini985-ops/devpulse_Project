package com.example.idea.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.idea.entity.Idea;

public interface IdeaRepo extends JpaRepository<Idea, Long>{

//	boolean existByTile(String title);

	List<Idea> findByTitleContaining(String title);
	
//	@Query("SELECT i FROM Idea i LEFT JOIN i.implementations imp GROUP BY i ORDER BY COUNT(imp) DESC")
	
	// Tending implementations for an Idea
	@Query("SELECT i FROM Idea i ORDER BY SIZE(i.implementations) DESC")
	List<Idea> findTrendingIdeas(Pageable pageable);
	
	//Latest Idea
	@Query("SELECT i FROM Idea i ORDER BY i.id DESC")
	public List<Idea> findLatstIdea(Pageable pageable);
	
	
 

}
