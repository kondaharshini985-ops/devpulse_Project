package com.example.implementation.entity;

import java.util.List;

import com.example.idea.entity.Idea;
import com.example.user.entity.User;
import com.example.voting.entity.Voting;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "implementations")
public class Implementation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
   private String githuburl;
	
	private Integer stars;
	
	private String language;
	
	@ManyToOne
	@JoinColumn(name="idea_id")
	private Idea idea;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User submitedBy;
	
	@OneToMany(mappedBy = "implementation")
	private List<Voting> votes;

	
	}


	


