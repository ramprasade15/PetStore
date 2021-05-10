package com.petStore.application;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.petStore.application.service.dto.PetDetails;
import com.petStore.application.service.impl.PetStoreServiceImpl;
import com.petStore.application.service.repository.PetStoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetStoreServiceImplTest {
	
	@InjectMocks
	private PetStoreServiceImpl mockPetStoreService;
	
	@Mock
	private PetStoreRepository petStoreRepository;
	
	@Mock
	PageRequest mockPageRequest;
	
	 @Mock
	 private Page<PetDetails> PetDetailsPage;
	
	@Test
	public void addPetDetailsTest() {
		PetDetails addNewPet = new PetDetails("p1", "max", "dog", 199.99);
		PetDetails expectedPetDetails =new PetDetails("p1", "max", "dog", 199.99);
		
		 Mockito.when(petStoreRepository.save(addNewPet)).thenReturn(expectedPetDetails);
			
		 PetDetails petDetails =mockPetStoreService.addPetDetails(addNewPet);
		 
		 Assert.assertEquals(expectedPetDetails.getName(), petDetails.getName());
	}
	
	@Test
	public void updatePetDetailsTest() {
		String petId = "p1";
		PetDetails pet = new PetDetails("p1", "max", "dog", 129.99);
		PetDetails addNewPet = new PetDetails("p1", "max", "dog", 99.99);
		PetDetails expectedPetDetails =new PetDetails("p1", "max", "dog", 99.99);
		 Mockito.when(petStoreRepository.findByPetId(petId)).thenReturn(pet);
		 Mockito.when(petStoreRepository.save(addNewPet)).thenReturn(expectedPetDetails);
			
		 PetDetails petDetails =mockPetStoreService.updatePetDetails(addNewPet, petId);
		 
		 Assert.assertEquals(expectedPetDetails.getName(), petDetails.getName());
	}
	
	@Test
	public void getAllPetDeatailsTest() {
		List<PetDetails> expectedPetsList = Arrays.asList( new PetDetails("p1", "max", "dog",
				  129.99), new PetDetails("p2", "aldy", "cat", 129.99));
		Integer pageNo = 0;
		Integer pageSize = 100;
		String sortBy = "name";
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<PetDetails> pagedPetDetails = new PageImpl(expectedPetsList);
		
        Mockito.when(petStoreRepository.findAll(pageable)).thenReturn(pagedPetDetails);
			
		 List<PetDetails> petDetailsList = mockPetStoreService.getAllPetDeatails(pageable);
		 assertThat(petDetailsList.size(), equalTo(2));
		 
	}
	
	@Test
	public void getPetDetailsByPetIdTest() {
		String petId = "p1";
		PetDetails expectedPetDetails =new PetDetails("p1", "max", "dog", 99.99);		 	
		 Mockito.when(petStoreRepository.findByPetId(petId)).thenReturn(expectedPetDetails);
			
		 
		 PetDetails petDetails =mockPetStoreService.getPetDetailsByPetId(petId);
		 
		 Assert.assertEquals(expectedPetDetails.getName(), petDetails.getName());
	}
	
	@Test
	public void deletePetDetailsByPetIdTest() {
		String petId = "p1";
		doNothing().when(petStoreRepository).deleteByPetId(petId);
		 mockPetStoreService.deletePetDetailsByPetId(petId);
		 
		 verify(petStoreRepository).deleteByPetId(petId);
	}

}
