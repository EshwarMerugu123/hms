package com.ojas.spring.service;

import java.util.List;

import com.ojas.spring.dto.NumberOfBuildingDto;
import com.ojas.spring.model.NumberOfBuildings;

public interface BuildingService {

	public String createBuildings(NumberOfBuildingDto numberOfBuildingsDto);

	public String removeBuildings(int buildingId);
	
	public String updateBuildings(int buildingId);
	
	List<NumberOfBuildingDto> getAllBuildings();
}
