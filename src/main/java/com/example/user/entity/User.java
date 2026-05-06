package com.example.user.entity;


import java.time.LocalDateTime;
import java.util.List;

import com.example.idea.entity.Idea;
import com.example.implementation.entity.Implementation;
import com.example.voting.entity.Voting;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long id;
	@Column(name="username")
	
	private String username;
	@Column(name="user_email", unique=true)
	private String email;
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(name="user_password")
	private String password;
	
	@Column(name="created_at",updatable = false )
	private LocalDateTime createdAt;
	
	@PrePersist
	public void setCreatedAt() {
	 	this.createdAt=LocalDateTime.now();
	}
	
	@OneToMany(mappedBy = "createdBy",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Idea> ideas;
	
    @OneToMany(mappedBy ="submitedBy")
    @JsonIgnore
    private List<Implementation> impls;
    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Voting> votes;
}
