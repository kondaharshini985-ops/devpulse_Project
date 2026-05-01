package com.example.idea.entity;



import java.time.LocalDateTime;
import java.util.List;

import com.example.implementation.entity.Implementation;
import com.example.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Idea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	String title;
	
	@Column(columnDefinition ="TEXT")
	String description;
	
	
	private String difficulty;
	
	private String techstack;
	@ManyToOne
	@JoinColumn(name="user_id")
	 User createdBy;
	
	 private LocalDateTime createdAt;
	 
	 @PrePersist
	 public void createdate() {
		this.createdAt =LocalDateTime.now();
	 }
	 
	 @OneToMany(mappedBy = "idea")
	 private  List<Implementation> implementations;
	
	 
	
	
	

}
