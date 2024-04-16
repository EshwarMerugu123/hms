package com.ojas.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.RoomsRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.NumberOfEmployees;
import com.ojas.spring.dto.NumberOfRoomsDto;
import com.ojas.spring.dto.RoomDTO;
import com.ojas.spring.model.Employee;
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
	public List<RoomDTO> getAllRooms() {

//		List<NumberOfRooms> numberOfRooms = roomsRepository.findAll();
//		List<NumberOfRoomsDto> roomsDTOList = new ArrayList<>();
//
//		for (NumberOfRooms rooms : numberOfRooms) {
//			NumberOfRoomsDto roomsDTO = new NumberOfRoomsDto();
//			roomsDTO.setRoomName(rooms.getRoomName());
//
//			List<NumberOfEmployees> employeesList = new ArrayList<>();
//			for (Employee employee : rooms.getEmployee()) {
//				NumberOfEmployees numberOfEmployees = new NumberOfEmployees();
//				numberOfEmployees.setEmployeeName(employee.getEmployeeName());
//				numberOfEmployees.setEmail(employee.getEmail());
//				numberOfEmployees.setEmployeeAddress(employee.getEmployeeAddress());
//				numberOfEmployees.setAge(employee.getAge());
//				numberOfEmployees.setGender(employee.getGender());
//				numberOfEmployees.setPassword(employee.getPassword());
//				employeesList.add(numberOfEmployees);
//			}
//			roomsDTO.setNumberOfEmployees(employeesList);
//			roomsDTOList.add(roomsDTO);
//		}
//
		return null;
	}

}
