package com.example.idea.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.exception.ResourseNotFoundException;
import com.example.idea.dto.IdeaListResponseDTO;
import com.example.idea.dto.IdeaRequestDTO;
import com.example.idea.dto.IdeaResponseDTO;
import com.example.idea.dto.TrendingIdeaDTO;
import com.example.idea.entity.Idea;

import com.example.idea.repository.IdeaRepo;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;



@Service
public class IdeaServiceImpl implements IdeaService{

	private IdeaRepo repo;
	private UserRepo urepo;
	
	public IdeaServiceImpl(IdeaRepo repo,UserRepo urepo) {
this.repo=repo;
this.urepo=urepo;
	}
	 // 🔥 Helper - get current logged in user from token
    private User getCurrentUser() {
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return urepo.findByEmail(email).orElseThrow(()->new ResourseNotFoundException("user not found"));
                
    }
	
	
	@Override
	public IdeaListResponseDTO createIdea(IdeaRequestDTO i) {
		
		
//		if(repo.existByTile(i.getTitle())) {
//			throw new ResourceNotFoundException("Tile already exist");
		
		
//		}
		
		 
		
		     List<Idea> similar=repo.findByTitleContaining(i.getTitle());
//		     
//		     if(!similar.isEmpty()) {
//		    	
//		    	 List<IdeaResponseDTO> similaridea =similar.stream()
//		    			 .map(idea -> new IdeaResponseDTO(
//		    					 idea.getIdea_id(),
//		    					 idea.getTitle(),
//		    					 idea.getDescription(),
//		    					 idea.getTechstack(),
//		    					 idea.getDifficulty(),
//		    					 idea.getCreatedBy().getId()))
//		    			        .toList();
		    	 
		List<IdeaResponseDTO> similaridea = new ArrayList<>();
		 if(!similar.isEmpty()) {
		for (Idea idea : similar) {
		    IdeaResponseDTO dto = new IdeaResponseDTO(
		            idea.getId(),
		            idea.getTitle(),
		            idea.getDescription(),
		            idea.getTechstack(),
		            idea.getDifficulty(),
		            idea.getCreatedBy().getId()
		    );

		    similaridea.add(dto);
		}
		    	 
		    	 
		    	 return new IdeaListResponseDTO(
		    			 "The similar idea is already existed ,Have a look ",similaridea,null);
		    	 
		     }
		     
    
			
		
		
		User user =getCurrentUser();
					//for dhini kosam security ni configure chese dhaka we need to give id manually 
		Idea idea= Idea.builder()
				.title(i.getTitle())
				.description(i.getDescription())
				.difficulty(i.getDifficulty())
				.techstack(i.getTechstack())
				.createdBy(user)
				.build();
		
	Idea  savedidea = repo.save(idea);
	
	IdeaResponseDTO res = new IdeaResponseDTO( savedidea.getId(),savedidea.getTitle(),savedidea.getDescription(),savedidea.getTechstack(), savedidea.getDifficulty(),savedidea.getCreatedBy().getId());
	
	
	 return new IdeaListResponseDTO("Idea Created",null,res);
		
	}

// GET ALL IDEAS
	@Override
	public List<IdeaResponseDTO> getAllIdeas() {
		 List<Idea> list =repo.findAll();
		 List<IdeaResponseDTO> response= new ArrayList();
		 for(Idea i : list) {
			IdeaResponseDTO r = new IdeaResponseDTO(i.getId(),i.getTitle(), i.getDescription(),i.getTechstack(),i.getDifficulty(),i.getCreatedBy().getId()) ;
			response.add(r);
		 }
		 
	return response;
	}

//GET IDEA BY ID
	@Override
	public IdeaResponseDTO getIdeaById(Long id) {
		Idea i = repo.findById(id).orElseThrow(() -> new ResourseNotFoundException("Idea not found"));
		IdeaResponseDTO r = new IdeaResponseDTO(i.getId(),i.getTitle(), i.getDescription(),i.getTechstack(),i.getDifficulty(),i.getCreatedBy().getId());
		return r;
		
	}

//UPDATE IDEA 
	@Override
	public IdeaResponseDTO updateIdea(Long id, IdeaRequestDTO dto ) {
		//FINDING EXISTING IDEA
		Idea existingidea = repo.findById(id).orElseThrow(() -> new ResourseNotFoundException("Idea id not found"));
		 // we need to update only the fields because the user and id would be the same  they automatically filled in the fields in frontend itself
		 
		 existingidea.setTitle(dto.getTitle());
		 existingidea.setDescription(dto.getDescription());
		 existingidea.setDifficulty(dto.getDifficulty());
		 existingidea.setTechstack(dto.getTechstack());
		 
		 //save the updated idea in db to replace the fields with new content
		  Idea updatedIdea = repo.save(existingidea);
		 //return the response 
		  IdeaResponseDTO result =new IdeaResponseDTO(updatedIdea.getId(),updatedIdea.getTitle(),updatedIdea.getDescription(),updatedIdea.getTechstack(),updatedIdea.getDifficulty(),updatedIdea.getCreatedBy().getId());
		  return result;
		
		  
	}

	// Delete the idea
//	@Override
//	public IdeaResponseDTO deleteIdea(Long id) {
//		
//			Idea i = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("idea not found"));
//			repo.delete(i);
//		
//		return new IdeaResponseDTO(i.getId(),i.getTitle(),i.getDescription(),i.getTechstack(),i.getDifficulty(),i.getCreatedBy().getId()); 
//	}

	///here the mistake is it is better to return string instead of wjole response and i create response after deleting ...we should create response before deleting it....
	
	
	                                // GOOD APPROACH 
//	@Override
//	public IdeaResponseDTO deleteIdea(Long id) {
//
//	    Idea idea = repo.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Idea not found"));
//
//	    // build response BEFORE deleting
//	    IdeaResponseDTO response = new IdeaResponseDTO(
//	            idea.getId(),
//	            idea.getTitle(),
//	            idea.getDescription(),
//	            idea.getTechstack(),
//	            idea.getDifficulty(),
//	            idea.getCreatedBy().getId()
//	    );
//
//	    repo.delete(idea);
//
//	    return response;
//	}
	                             
	                            
	                             
	                             // Delete the idea
	
	@Override
	public String deleteIdea(Long id) {
		
			Idea i = repo.findById(id).orElseThrow(()->new ResourseNotFoundException("idea not found"));
			repo.delete(i);
		
		return "idea deleted successfully";
	}
	
	
	/// TRENDING IDEA


	@Override
	public List<TrendingIdeaDTO> trendingIdeas() {
		List<Idea> trending = repo.findTrendingIdeas(PageRequest.of(0, 5));
		List<TrendingIdeaDTO> list = new ArrayList<>();
		for(Idea i :trending ) {
			TrendingIdeaDTO t = new TrendingIdeaDTO(i.getId(),i.getTitle(),i.getImplementations().size());
			list.add(t);
		}
		return list;
	}


	@Override
	public List<IdeaResponseDTO> latestIdeas() {
	    List<Idea> latest = repo.findLatstIdea(PageRequest.of(0, 5));
	    
	    List<IdeaResponseDTO> list = new ArrayList<>();
	    for(Idea i : latest) {
	    	IdeaResponseDTO dto = new IdeaResponseDTO(i.getId(),i.getTitle() , i.getDescription(), i.getTechstack(),i.getDifficulty(),i.getCreatedBy().getId());
	    	
	    	list.add(dto);
	    }
		return list;
	}
	
	
	
	
	
	

}
