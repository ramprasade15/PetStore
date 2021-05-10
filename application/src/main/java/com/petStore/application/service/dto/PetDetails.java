package com.petStore.application.service.dto;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class PetDetails {
	
	@Id
	private String petId;

	private String name;
	private String type; 
	private double price;

}
