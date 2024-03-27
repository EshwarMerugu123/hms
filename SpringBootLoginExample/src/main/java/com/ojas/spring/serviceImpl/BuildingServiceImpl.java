package com.ojas.spring.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;
import com.ojas.spring.Repository.BuildingRepository;
import com.ojas.spring.Repository.FloorsRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.NumberOfBuildingDto;
import com.ojas.spring.dto.NumberOfRoomsDto;
import com.ojas.spring.model.NumberOfBuildings;
import com.ojas.spring.model.NumberOfFloors;
import com.ojas.spring.model.NumberOfRooms;
import com.ojas.spring.service.BuildingService;

import jakarta.transaction.Transactional;

@Service
public class BuildingServiceImpl implements BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private FloorsRepository floorsRepository;

	@Override
	@Transactional
	public String createBuildings(NumberOfBuildingDto numberOfBuildingsDto) {

		// Create a new NumberOfBuildings object
		NumberOfBuildings building = new NumberOfBuildings();
		building.setBuildingAddress(numberOfBuildingsDto.getBuildingAddress());
		building.setBuildingName(numberOfBuildingsDto.getBuildingName());

		// Create a list to store NumberOfFloors objects
		List<NumberOfFloors> floors = new ArrayList<>();
		List<NumberOfRooms> rooms = new ArrayList<>();

		// Iterate through the list of floor DTOs and create NumberOfFloors objects
		for (NumberFloorsDto floorDto : numberOfBuildingsDto.getFloors()) {

			// setting the floor data
			NumberOfFloors floor = new NumberOfFloors();
			floor.setFloorName(floorDto.getFloorName());
			floor.setNumberOfBuildings(building);

			for (NumberOfRoomsDto numberOfroomsdto : floorDto.getListOfRooms()) {

				// setting the rooms data
				NumberOfRooms numberOfRooms = new NumberOfRooms();
				numberOfRooms.setRoomName(numberOfroomsdto.getRoomName());
				numberOfRooms.setNumberOfFloors(floor);
				rooms.add(numberOfRooms);
			}
			floor.setRooms(rooms);

			floors.add(floor);
		}

		// Set the list of floors to the building
		building.setFloors(floors);

		// Save the building entity along with its floors
		NumberOfBuildings savedBuilding = buildingRepository.save(building);

		if (savedBuilding != null) {
			return "Building inserted successfully";
		} else {
			return "Failed to insert building";
		}
	}

	@Override
	public String removeBuildings(int buildingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateBuildings(int buildingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<NumberOfBuildingDto> getAllBuildings() {
		List<NumberOfBuildings> numberOfBuildings = buildingRepository.findAll();

		return numberOfBuildings.stream().map(building -> {
			NumberOfBuildingDto buildingDto = new NumberOfBuildingDto();
			buildingDto.setBuildingAddress(building.getBuildingAddress());
			buildingDto.setBuildingName(building.getBuildingName());

			List<NumberFloorsDto> floorDtos = building.getFloors().stream().map(floor -> {
				NumberFloorsDto floorDto = new NumberFloorsDto();
				floorDto.setFloorName(floor.getFloorName());

				List<NumberOfRoomsDto> roomDtos = floor.getRooms().stream().map(room -> {
					NumberOfRoomsDto roomDto = new NumberOfRoomsDto();
					roomDto.setRoomName(room.getRoomName());
					return roomDto;
				}).collect(Collectors.toList());

				floorDto.setListOfRooms(roomDtos);
				return floorDto;
			}).collect(Collectors.toList());

			buildingDto.setFloors(floorDtos);
			return buildingDto;
		}).collect(Collectors.toList());
	}

}
