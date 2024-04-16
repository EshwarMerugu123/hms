package com.ojas.spring.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfRooms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomName;

	@ManyToOne
	@JoinColumn(name = "floorId") // name of the foreign key column in NumberOfFloors table
	private NumberOfFloors numberOfFloors;

	 @OneToMany(mappedBy = "numberOfRooms", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Employee> employee;
}
