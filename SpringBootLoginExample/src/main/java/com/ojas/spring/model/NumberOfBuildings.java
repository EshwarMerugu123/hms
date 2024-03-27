package com.ojas.spring.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfBuildings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int buildingId;
	private String buildingName;
	private String buildingAddress;

	@OneToMany(mappedBy = "numberOfBuildings")
	private List<NumberOfFloors> floors;
}
