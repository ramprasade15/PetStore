package com.petStore.application.service;

import java.util.List;
import org.springframework.data.domain.Pageable;

import com.petStore.application.service.dto.PetDetails;

public interface PetStoreService {

	PetDetails updatePetDetails(PetDetails petDetails, String petId);

	List<PetDetails> getAllPetDeatails(Pageable paging);

	PetDetails getPetDetailsByPetId(String petId);

	void deletePetDetailsByPetId(String petId);

	PetDetails addPetDetails(PetDetails petDetails);

	void deletePetDetailsByPetType(String petType);

}
