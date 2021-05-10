package com.petStore.application.controller;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petStore.application.service.PetStoreService;
import com.petStore.application.service.dto.PetDetails;
import com.petStore.application.service.repository.PetStoreRepository;

@RestController
public class PetStoreController {

	
	@Autowired
	PetStoreService petStoreService;
	
	@PostMapping("/addpet")
	public PetDetails addNewPetDetails(@Validated @RequestBody PetDetails petDetails) {
		
		return petStoreService.addPetDetails(petDetails);
		
	}
	
	
	@DeleteMapping("/deletepet/{petId}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePetDetails(@PathVariable String petId) {
		petStoreService.deletePetDetailsByPetId(petId);
		
	}
	
	@GetMapping("/retrivepet/{petId}")
	@ResponseStatus(HttpStatus.OK)
	public PetDetails retrivePetDetailsById(@PathVariable String petId) {
		return petStoreService.getPetDetailsByPetId(petId);
		
	}
	
	@GetMapping("/retriveallpets")
	@ResponseStatus(HttpStatus.OK)
	public List<PetDetails> getAllPetDeatails(@RequestParam Integer limit) {
		Integer pageNo = 0;
		String sortBy = "name";
		Pageable paging = PageRequest.of(pageNo, limit, Sort.by(sortBy));
		return petStoreService.getAllPetDeatails(paging);
		
	}

	
	@PutMapping("/updatepetdetails/{petId}")
	public PetDetails updatePet(@RequestBody PetDetails petDetails, @PathVariable String petId) {
		
		return petStoreService.updatePetDetails(petDetails, petId);
		
		
    }
	
	@DeleteMapping("/deletepetbytype/{petType}")
	@ResponseStatus(HttpStatus.OK)
	public void deletePetDetailsByPetType(@PathVariable String petType) {
		petStoreService.deletePetDetailsByPetType(petType);
		
	}


}
