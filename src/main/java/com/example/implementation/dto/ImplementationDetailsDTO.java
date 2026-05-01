package com.example.implementation.dto;





import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImplementationDetailsDTO {
           
 private Long implementationId;
 private String githuburl;
 private String repoName;
 private Integer stars;
 private String primarylanguage;
 private String SubmittedBy;
 private String IdeaTitle;
 private Integer votecount;
 
 
}
