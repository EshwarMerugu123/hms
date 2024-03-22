package com.ojas.spring.serviceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.BuildingRepository;
import com.ojas.spring.Repository.FloorsRepository;
import com.ojas.spring.dto.NumberOfBuildingDto;
import com.ojas.spring.model.NumberOfBuildings;
import com.ojas.spring.model.NumberOfFloors;
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
	public String createBuildings(NumberOfBuildingDto numberOfBuildings) {
		
		NumberOfBuildings building = new NumberOfBuildings();
		building.setBuildingAddress(numberOfBuildings.getBuildingAddress());
		building.setBuildingName(numberOfBuildings.getBuildingName());
		
		 Set<NumberOfFloors> floors = new HashSet<>();
		 for (Integer floorId : numberOfBuildings.getFloorId()) {
			    Optional<NumberOfFloors> findById = floorsRepository.findById(floorId);
			    if (findById.isPresent()) {
			        floors.add(findById.get());
			    } 
			}
			building.setFloors(floors);
		 	
	        NumberOfBuildings save = buildingRepository.save(building);
		
		if(save !=null) {
			return "inserted success";
		}else {
			return "Insertion of buildings is failed";
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
	public List<NumberOfBuildingDto> getAllBuildings() {
		// TODO Auto-generated method stub
		return null;
	}

}
