package com.ojas.spring.service;

import java.util.List;

import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.RoomDTO;
import com.ojas.spring.model.NumberOfFloors;
import com.ojas.spring.model.NumberOfRooms;

public interface FloorService {

	public String createfloors(NumberFloorsDto numberOfFloors);

	public String removefloors(int floorId);

	public String updatefloors(int floorId);

	List<NumberFloorsDto> getAllfloors();

	List<RoomDTO> getRoomsByFloorId(int floorId);
}
