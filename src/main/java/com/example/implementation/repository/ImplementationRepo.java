package com.example.implementation.repository;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.implementation.entity.Implementation;

public interface ImplementationRepo extends JpaRepository<Implementation, Long>{



	public List<Implementation> findByIdea_Id(Long id);
	
	
	
	// Entity name and its field names
	
	@Query("SELECT i  FROM Implementation i ORDER BY SIZE(i.votes) DESC")
	public List<Implementation> findTrendingImplementation(Pageable pageble);
	

}
