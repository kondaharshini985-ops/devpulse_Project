package com.example.implementation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseImplementationDTO {
	
	
	private  Long id;
	private String githuburl;
	private Integer stars;
	private String  language;
	private Long user_id;
	private Long idea_id;
	

}
