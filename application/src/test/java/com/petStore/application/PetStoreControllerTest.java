package com.petStore.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.petStore.application.controller.PetStoreController;
import com.petStore.application.service.PetStoreService;
import com.petStore.application.service.dto.PetDetails;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetStoreControllerTest {

	@Mock
	private PetStoreService mockPetStoreService;

	@InjectMocks
	PetStoreController mockPetStoreController;
	
	
	@Test
	public void getPetDeatilsByIdSuccess() {

		PetDetails expectedPetDetails = new PetDetails("p1", "max", "dog", 129.99);
		Mockito.when(mockPetStoreService.getPetDetailsByPetId("p1")).thenReturn(expectedPetDetails);
		PetDetails response = mockPetStoreController.retrivePetDetailsById("p1");
		
		assertEquals(expectedPetDetails.getName(), response.getName());

		verify(mockPetStoreService, times(1)).getPetDetailsByPetId("p1");

	}

	
	  @Test 
	  public void getAllPetDetailsSuccess() throws Exception {
		   Integer pageNo = 0;
			Integer pageSize = 100;
			String sortBy = "name";
			Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	 List<PetDetails> expectedPets = Arrays.asList( new PetDetails("p1", "max", "dog",
	  129.99), new PetDetails("p2", "aldy", "cat", 129.99));
	 
	 when(mockPetStoreService.getAllPetDeatails(pageable)).thenReturn(expectedPets);
	 
	  }

	@Test
	public void addNewPetDetails() throws Exception {

		PetDetails newPet = new PetDetails("p1", "max", "dog", 129.99);
		PetDetails expectedPetDetails = new PetDetails("p1", "max", "dog", 129.99);
		when(mockPetStoreService.addPetDetails(any(PetDetails.class))).thenReturn(newPet);
		PetDetails response = mockPetStoreController.addNewPetDetails(newPet);
		assertEquals(expectedPetDetails.getName(), response.getName());
		verify(mockPetStoreService, times(1)).addPetDetails(any(PetDetails.class));

	}

	@Test
	public void updatepetdetails() throws Exception {

		PetDetails newPet = new PetDetails("p1", "max", "dog", 99.99);
		PetDetails expectedPetDetails = new PetDetails("p1", "max", "dog", 99.99);
		when(mockPetStoreService.updatePetDetails(newPet, "p1")).thenReturn(expectedPetDetails);
		PetDetails response = mockPetStoreController.updatePet(newPet, "p1");
		assertEquals(expectedPetDetails.getName(), response.getName());
		
	}

	@Test
	public void deletePetDetaildSucess() {

		doNothing().when(mockPetStoreService).deletePetDetailsByPetId("p1");
		 mockPetStoreController.deletePetDetails("p1");
		verify(mockPetStoreService, times(1)).deletePetDetailsByPetId("p1");
	}

}
