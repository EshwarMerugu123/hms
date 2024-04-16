package com.ojas.spring.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.FloorsRepository;
import com.ojas.spring.Repository.RoomsRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.NumberOfEmployees;
import com.ojas.spring.dto.NumberOfRoomsDto;
import com.ojas.spring.dto.RoomDTO;
import com.ojas.spring.model.Employee;
import com.ojas.spring.model.NumberOfFloors;
import com.ojas.spring.model.NumberOfRooms;
import com.ojas.spring.service.FloorService;

import jakarta.transaction.Transactional;

@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorsRepository floorsRepository;

	@Override
	public String createfloors(NumberFloorsDto numberOfFloors) {

		NumberOfFloors numberOfFloors2 = new NumberOfFloors();
		numberOfFloors2.setFloorName(numberOfFloors.getFloorName());

		NumberOfFloors savefloors = floorsRepository.save(numberOfFloors2);

		if (savefloors != null) {
			return "inserted success";
		} else {
			return "Insertion of buildings is failed";
		}
	}

	@Override
	public String removefloors(int floorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updatefloors(int floorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public List<NumberFloorsDto> getAllfloors() {
		List<NumberOfFloors> numberOfFloors = floorsRepository.findAll();
		List<NumberFloorsDto> floorsDtoList = new ArrayList<>();

		for (NumberOfFloors floor : numberOfFloors) {
			NumberFloorsDto floorsDTO = new NumberFloorsDto();
			floorsDTO.setFloorId(floor.getFloorId());
			floorsDTO.setFloorName(floor.getFloorName());

			List<NumberOfRoomsDto> roomDtos = new ArrayList<>();
			for (NumberOfRooms room : floor.getRooms()) {
				NumberOfRoomsDto roomDto = new NumberOfRoomsDto();
				roomDto.setRoomName(room.getRoomName());

				List<NumberOfEmployees> employeesList = new ArrayList<>();
				for (Employee employee : room.getEmployee()) {
					NumberOfEmployees numberOfEmployees = new NumberOfEmployees();
					numberOfEmployees.setEmployeeName(employee.getEmployeeName());
					numberOfEmployees.setEmail(employee.getEmail());
					numberOfEmployees.setEmployeeAddress(employee.getEmployeeAddress());
					numberOfEmployees.setAge(employee.getAge());
					numberOfEmployees.setGender(employee.getGender());
					numberOfEmployees.setPassword(employee.getPassword());
					employeesList.add(numberOfEmployees);
				}
				roomDto.setNumberOfEmployees(employeesList);
				roomDtos.add(roomDto);
			}

			floorsDTO.setListOfRooms(roomDtos);
			floorsDtoList.add(floorsDTO);
		}

		return floorsDtoList;
	}

	@Override
	public List<RoomDTO> getRoomsByFloorId(int floorId) {

		NumberOfFloors findById = floorsRepository.findById(floorId).orElse(null);
		List<NumberOfRooms> rooms = findById.getRooms();
		List<RoomDTO> list = new ArrayList<>();
		for (NumberOfRooms rooms2 : rooms) {
			RoomDTO numberOfRooms = new RoomDTO();
			numberOfRooms.setRoomId(rooms2.getRoomId());
			numberOfRooms.setRoomName(rooms2.getRoomName());
			list.add(numberOfRooms);
		}
		return list;
	}

}
