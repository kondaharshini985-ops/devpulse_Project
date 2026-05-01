package com.example.implentation.Service;

import java.util.List;

import com.example.implementation.dto.ImplementationDetailsDTO;
import com.example.implementation.dto.RequestImplementationDTO;
import com.example.implementation.dto.ResponseImplementationDTO;

public interface ImplentationServiceInterface {

	public ResponseImplementationDTO createImplementation(RequestImplementationDTO dto);
	
	public List<ImplementationDetailsDTO> getImplementationById(Long id);
	
	public ResponseImplementationDTO updateImplementation(RequestImplementationDTO dto,Long id);
	public String deleteImp(Long id);
	
	public List<ImplementationDetailsDTO> trendingImplementations();



	

	

	
}
