package com.ojas.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.spring.serviceImpl.RoomsServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/RoomService")
public class RoomsController {

	@Autowired
	private RoomsServiceImpl roomsServiceImpl;
	
	@GetMapping("/getAllRooms")
	public ResponseEntity<?> getAllRoomsDetails() {
		return ResponseEntity.ok(roomsServiceImpl.getAllRooms());
	}
}
