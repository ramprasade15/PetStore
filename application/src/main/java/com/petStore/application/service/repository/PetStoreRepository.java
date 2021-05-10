package com.petStore.application.service.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.petStore.application.service.dto.PetDetails;

@Repository
@Transactional 
public interface PetStoreRepository extends PagingAndSortingRepository<PetDetails, Integer>{
	
	PetDetails save(PetDetails petDetails);
	
	void deleteByPetId(String petId);
	
	
	List<PetDetails> findAll();

	PetDetails findByPetId(String petId);
	
	
	

}
