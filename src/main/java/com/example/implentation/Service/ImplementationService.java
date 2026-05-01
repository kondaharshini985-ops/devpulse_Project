package com.example.implentation.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.exception.ResourseNotFoundException;
import com.example.idea.entity.Idea;
import com.example.idea.repository.IdeaRepo;
import com.example.implementation.dto.ImplementationDetailsDTO;
import com.example.implementation.dto.ImplementationRepoDTO;
import com.example.implementation.dto.RequestImplementationDTO;
import com.example.implementation.dto.ResponseImplementationDTO;
import com.example.implementation.entity.Implementation;
import com.example.implementation.repository.ImplementationRepo;
import com.example.user.entity.User;
import com.example.user.repository.UserRepo;
import com.example.voting.repository.Votingrepo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;


@Service



public class ImplementationService implements ImplentationServiceInterface {
	
	
	ImplementationRepo repo;
	UserRepo userrepo;
	IdeaRepo idearepo;

	Votingrepo vrepo;
	
	
public ImplementationService(ImplementationRepo repo,UserRepo userrepo,IdeaRepo idearepo,  Votingrepo vrepo) {
        this.repo = repo;
		this.idearepo=idearepo;
		this.userrepo= userrepo;
		this.vrepo= vrepo;
		
	}
private User getCurrentUser() {
    String email = SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName();

    return userrepo.findByEmail(email).orElseThrow(()->new ResourseNotFoundException("user not found"));
            
}


	
	public ResponseImplementationDTO createImplementation(RequestImplementationDTO dto) {

	    // Step 1: Fetch User
	    User user = getCurrentUser();

	    // Step 2: Fetch Idea
	    Idea idea = idearepo.findById(dto.getIdeaId())
	            .orElseThrow(() -> new ResourseNotFoundException("Idea not found"));

	    // Step 3: Extract owner & repo from URL
	   
	    String url = dto.getGithuburl();
	    String[] parts = url.split("/");
	    
	    if (parts.length < 5) {
	        throw new ResourseNotFoundException("Invalid GitHub URL");
	    }

	    String owner = parts[3];
	    String repoName = parts[4]; // renamed ✅

	    // Step 4: Create API URL
	    String apiUrl = "https://api.github.com/repos/" + owner + "/" + repoName;

	    // Step 5: Call GitHub API
	    WebClient client = WebClient.create();

	    ImplementationRepoDTO imp = client.get()
	            .uri(apiUrl)
	            .retrieve()
	            .bodyToMono(ImplementationRepoDTO.class)
	            .block();
	    if(imp==null) {
	    	throw new ResourseNotFoundException("Repo not found");
	    }

	    // Step 6: Extract values from DTO
	    Integer stars = imp.getStars();
	    String language = imp.getLanguage();

	    // Step 7: Create Entity
	    Implementation impl = Implementation.builder()
	            .githuburl(url)
	            .stars(stars)
	            .language(language)
	            .submitedBy(user)
	            .idea(idea)
	            .build();

	    // Step 8: Save to DB
	    Implementation saved = repo.save(impl);

	    // Step 9: Convert to Response DTO
	    ResponseImplementationDTO res = new ResponseImplementationDTO(
	            saved.getId(),
	            saved.getGithuburl(),
	            saved.getStars(),
	            saved.getLanguage(),
	            saved.getSubmitedBy().getId(),
	            saved.getIdea().getId()
	    );

	    return res;
	}

	@Override
	public List<ImplementationDetailsDTO> getImplementationById(Long id) {
		
	    List<ImplementationDetailsDTO> result = new ArrayList<>();
	 

	    List<Implementation> list = repo.findByIdea_Id(id); 
		
		for(Implementation implementation :list) {
			
			String githuburl =implementation.getGithuburl();
			String[] parts =githuburl.split("/");
			String reponame=parts[4];
			Integer votes =vrepo.countByImplementation(implementation);
			
			ImplementationDetailsDTO dto = new ImplementationDetailsDTO(implementation.getId(),githuburl, reponame,implementation.getStars(), implementation.getLanguage(),implementation.getSubmitedBy().getEmail(),implementation.getIdea().getTitle(),votes);
			
			result.add(dto);
			
		}
		
		return result;
		
	}

	@Override
	public ResponseImplementationDTO updateImplementation(RequestImplementationDTO dto, Long id) {
		Implementation imp =repo.findById(id).orElseThrow(()->new ResourseNotFoundException("ementation not found"));
		User u = getCurrentUser();
		Idea i =idearepo.findById(dto.getIdeaId()).orElseThrow(()-> new ResourseNotFoundException("idea not found"));
		
		 String url =dto.getGithuburl();
		 String[] parts =url.split("/");
		  if (parts.length < 5) {
		        throw new ResourseNotFoundException("Invalid GitHub URL");
		    }
		 String owner =parts[3];
		 String reponame = parts[4];
		 
		 String githubApiurl ="https://api.github.com/repos/" + owner + "/" + reponame;
		 
		 WebClient client = WebClient.create();

		    ImplementationRepoDTO githuburl = client.get()
		            .uri(githubApiurl)
		            .retrieve()
		            .bodyToMono(ImplementationRepoDTO.class)
		            .block();
		    if(githuburl==null) {
		    	throw new ResourseNotFoundException("Repo not found");
		    }
		    
		    Integer starts =githuburl.getStars();
		    String language= githuburl.getLanguage();
		    
		    
		    imp.setId(id);
		    imp.setGithuburl(url);
		    imp.setStars(starts);
		    imp.setLanguage(language);
		    imp.setIdea(i);
		    imp.setSubmitedBy(u);
		    
		    Implementation saved =repo.save(imp);
		    
		    
		    return new ResponseImplementationDTO(saved.getId(),saved.getGithuburl(),saved.getStars(),saved.getLanguage(),saved.getSubmitedBy().getId(),saved.getIdea().getId());
		    


		 
		 
		 //ikkada manaki userid vunnadhi kani entity lo set cheyyadaniki we need user so direct access levvu kabatti we need to do like this before undaing entity
		 
		// then we need all the details fron github know to updte so
		 
		 
		 
		 
		
		
	}

	@Override
	public String deleteImp(Long id) {
		Implementation imp = repo.findById(id).orElseThrow(()->new ResourseNotFoundException("implementation not found"));
		repo.delete(imp);
		return "Implementation deleted";
	}
	
	
  //trending Implementations
	@Override
	public List<ImplementationDetailsDTO> trendingImplementations() {
		List<Implementation> trending =repo.findTrendingImplementation(PageRequest.of(0, 5));
		
		List<ImplementationDetailsDTO> list = new ArrayList<>();
		for(Implementation i :trending) {
			String[] parts =i.getGithuburl().split("/");
			String reponame=parts[4];
			Integer votesCount =vrepo.countByImplementation(i);
		    ImplementationDetailsDTO dto = new ImplementationDetailsDTO(i.getId(),i.getGithuburl(), reponame, i.getStars(), i.getLanguage(),i.getSubmitedBy().getEmail(),i.getIdea().getTitle(), votesCount);
			list.add(dto);
		}
		return list;
	}
	
	
	

	


		
	
	}

	

