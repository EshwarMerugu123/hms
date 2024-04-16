package com.ojas.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.spring.dto.NumberOfBuildingDto;
import com.ojas.spring.model.NumberOfBuildings;
import com.ojas.spring.serviceImpl.BuildingServiceImpl;

@RestController
@RequestMapping("/BuildingService")
@CrossOrigin(origins = "*")
public class BuildingController {

	@Autowired
	private BuildingServiceImpl buildingserverimpl;

	@GetMapping("/test")
	public String test() {
		return "hi am eshwar";
	}

	@PostMapping("/addbuilding")
	public String addBuildings(@RequestBody NumberOfBuildingDto numberOfbuildings) {
		return buildingserverimpl.createBuildings(numberOfbuildings);
	}
	
	@GetMapping("/getbuildingDetails")
	public ResponseEntity<?> getAllBuildingDetails() {
		return ResponseEntity.ok(buildingserverimpl.getAllBuildings());
	}
}
