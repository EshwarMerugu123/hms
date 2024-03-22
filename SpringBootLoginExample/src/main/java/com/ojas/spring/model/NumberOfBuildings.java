package com.ojas.spring.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfBuildings {

	@Id
	@GeneratedValue
	private int buildingId;
	private String buildingName;
	private String buildingAddress;

	@OneToMany(cascade=CascadeType.ALL)  
	private Set<NumberOfFloors> floors;
}
