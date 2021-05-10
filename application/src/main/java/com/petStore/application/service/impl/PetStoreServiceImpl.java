package com.petStore.application.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.petStore.application.service.PetStoreService;
import com.petStore.application.service.dto.PetDetails;
import com.petStore.application.service.repository.PetStoreRepository;

@Service
public class PetStoreServiceImpl implements PetStoreService {

	@Autowired
	PetStoreRepository petStoreRepository;
	
	
	@Override
	public PetDetails updatePetDetails(PetDetails petDetails, String petId) {
		
		PetDetails p1 = petStoreRepository.findByPetId(petId);
		
		if(petDetails.getName() != null) { 
		p1.setName(petDetails.getName());
		}else {
			p1.setName(p1.getName());
		}
		p1.setPetId(petId);
		if(petDetails.getType() != null) {
			p1.setType(petDetails.getType());
		}else {
			p1.setType(p1.getType());
		}
		p1.setPrice(petDetails.getPrice());
		
	
		return petStoreRepository.save(p1);
	}
	
	
	@Override
	public List<PetDetails> getAllPetDeatails(Pageable paging) {
		
		Page<PetDetails> pageResult = petStoreRepository.findAll(paging);
		return pageResult.toList();
		
	}
	
	@Override
	public PetDetails getPetDetailsByPetId(String petId) {
		
		return petStoreRepository.findByPetId(petId);
	}
	@Override
	public void deletePetDetailsByPetId(String petId) {
		petStoreRepository.deleteByPetId(petId);
		
	}
	@Override
	public PetDetails addPetDetails(PetDetails petDetails) {
		
		return petStoreRepository.save(petDetails);
	}


	@Override
	public void deletePetDetailsByPetType(String petType) {
		List<PetDetails> petsList = petStoreRepository.findAll();
		for(PetDetails pet: petsList) {
			if(pet.getType().equals(petType)) {
			petStoreRepository.deleteByPetId(pet.getPetId());
			}
		}
		 
		
	}
	

}
