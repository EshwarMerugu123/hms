package com.ojas.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberFloorsDto {
	private int floorId;
	private String floorName;
	List<NumberOfRoomsDto> listOfRooms;
	private int roomCount;
}
