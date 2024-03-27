package com.ojas.spring.service;

import java.util.List;

import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.NumberOfRoomsDto;

public interface RoomsService {

	public String createRooms(NumberOfRoomsDto numberOfRooms);

	public String removeRooms(int roomId);

	public String updateRooms(int roomId);

	List<NumberOfRoomsDto> getAllRooms();
}
