package com.example.implementation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class ImplementationRepoDTO {
	
	@JsonProperty("stargazers_count")
	private Integer stars;
	
	@JsonProperty("language")
	private String language;

}
