package com.ojas.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.spring.Repository.FloorsRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.model.NumberOfBuildings;
import com.ojas.spring.model.NumberOfFloors;
import com.ojas.spring.serviceImpl.FloorServiceImpl;

@RestController
@RequestMapping("/floorsService")
@CrossOrigin(origins = "*")
public class FloorsController {

	@Autowired
	private FloorServiceImpl floorsServiceImpl;

	@GetMapping("/test")
	public String test() {
		return "hi am eshwar";
	}

	@PostMapping("/addFloors")
	public String addFloors(@RequestBody NumberFloorsDto numberOfFloors) {
		return floorsServiceImpl.createfloors(numberOfFloors);
	}
	
	@GetMapping("/getAllFloors")
	public ResponseEntity<?> getAllFloorDetails() {
		return ResponseEntity.ok(floorsServiceImpl.getAllfloors());
	}
	
	@GetMapping("/getRoomsByFloorId")
	public ResponseEntity<?> getRoomsbyFloorId(@RequestParam int floorId) {
		return ResponseEntity.ok(floorsServiceImpl.getRoomsByFloorId(floorId));
	}
}
