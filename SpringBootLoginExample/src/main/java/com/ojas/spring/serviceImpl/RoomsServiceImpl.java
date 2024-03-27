package com.ojas.spring.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.RoomsRepository;
import com.ojas.spring.dto.NumberOfRoomsDto;
import com.ojas.spring.model.NumberOfRooms;
import com.ojas.spring.service.RoomsService;

@Service
public class RoomsServiceImpl implements RoomsService {

	@Autowired
	private RoomsRepository roomsRepository;

	@Override
	public String createRooms(NumberOfRoomsDto numberOfRooms) {

		NumberOfRooms rooms = new NumberOfRooms();
		rooms.setRoomName(numberOfRooms.getRoomName());

		NumberOfRooms saveRooms = roomsRepository.save(rooms);

		if (saveRooms != null) {
			return "inserted success";
		} else {
			return "Insertion of floors is failed";
		}
	}

	@Override
	public String removeRooms(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateRooms(int roomId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NumberOfRoomsDto> getAllRooms() {
		// TODO Auto-generated method stub
		return null;
	}

}
