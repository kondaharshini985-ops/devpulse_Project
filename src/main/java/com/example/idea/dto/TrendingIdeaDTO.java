package com.example.idea.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TrendingIdeaDTO {
	
	private Long id;
	private String title;
	private Integer count;
	

}
