package com.example.voting.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.implementation.entity.Implementation;
import com.example.user.entity.User;
import com.example.voting.entity.Voting;

@Repository
public interface Votingrepo extends JpaRepository<Voting, Long>{
	
	public boolean existsByUser_IdAndImplementation_Id(Long id, Long implementation_id);
	public int countByImplementation(Implementation imp);
	public Optional<Voting> findByUser_IdAndImplementation_Id(Long id, Long implementationId);

}
