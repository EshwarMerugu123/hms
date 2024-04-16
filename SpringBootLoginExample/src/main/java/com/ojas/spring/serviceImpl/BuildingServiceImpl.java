package com.ojas.spring.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.BuildingRepository;
import com.ojas.spring.Repository.EmployeeRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.dto.NumberOfBuildingDto;
import com.ojas.spring.dto.NumberOfEmployees;
import com.ojas.spring.dto.NumberOfRoomsDto;
import com.ojas.spring.model.Employee;
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
	private EmployeeRepository employeeRepository;

	@Override
	@Transactional
	public String createBuildings(NumberOfBuildingDto buildingDto) {
		NumberOfBuildings building = new NumberOfBuildings();
		building.setBuildingName(buildingDto.getBuildingName());
		building.setBuildingAddress(buildingDto.getBuildingAddress());

		List<NumberOfFloors> floorsList = new ArrayList<>();
		for (NumberFloorsDto floorsDto : buildingDto.getFloors()) {
			NumberOfFloors floor = new NumberOfFloors();
			floor.setFloorName(floorsDto.getFloorName());
			floor.setNumberOfBuildings(building);

			List<NumberOfRooms> roomsList = new ArrayList<>();
			for (NumberOfRoomsDto roomsDto : floorsDto.getListOfRooms()) {
				NumberOfRooms room = new NumberOfRooms();
				room.setRoomName(roomsDto.getRoomName());
				room.setNumberOfFloors(floor);

				List<NumberOfEmployees> employees = roomsDto.getNumberOfEmployees() != null
						? roomsDto.getNumberOfEmployees()
						: Collections.emptyList();
				List<Employee> employeeList = new ArrayList<>();
				for (NumberOfEmployees employeeDto : employees) {
					Employee employee = new Employee();
					employee.setEmployeeName(employeeDto.getEmployeeName());
					employee.setEmail(employeeDto.getEmail());
					employee.setAge(employeeDto.getAge());
					employee.setGender(employeeDto.getGender());
					employee.setPassword(employeeDto.getPassword());
					employee.setEmployeeAddress(employeeDto.getEmployeeAddress());

					// setting room obj into employee
					employee.setNumberOfRooms(room);

					employeeList.add(employee);
					System.out.println("employeeList is:" + employee);
				}

				room.setEmployee(employeeList);
				roomsList.add(room);
			}
			floor.setRooms(roomsList);
			floorsList.add(floor);
		}
		building.setFloors(floorsList);
		// Save the building
		buildingRepository.save(building);
		return "Buildings created successfully.";
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
		List<NumberOfBuildingDto> buildingDtos = buildingRepository.findAll().stream().map(building -> {
			NumberOfBuildingDto buildingDto = new NumberOfBuildingDto();

			buildingDto.setBuildingAddress(building.getBuildingAddress());
			buildingDto.setBuildingName(building.getBuildingName());
			buildingDto.setFloorCount(building.getFloors().size());

			List<NumberFloorsDto> floorDtos = building.getFloors().stream().map(floor -> {

				NumberFloorsDto floorDto = new NumberFloorsDto();
				floorDto.setFloorId(floor.getFloorId());
				floorDto.setFloorName(floor.getFloorName());
				floorDto.setRoomCount(floor.getRooms().size());

				List<NumberOfRoomsDto> roomDtos = floor.getRooms().stream().map(room -> {
					NumberOfRoomsDto roomDto = new NumberOfRoomsDto();
					roomDto.setRoomId(room.getRoomId());
					roomDto.setRoomName(room.getRoomName());
					roomDto.setEmployeesCount(room.getEmployee().size());

					List<NumberOfEmployees> employeesDTO = room.getEmployee().stream().map(emp -> {
						NumberOfEmployees empDTO = new NumberOfEmployees();
						empDTO.setEmployeeName(emp.getEmployeeName());
						empDTO.setEmail(emp.getEmail());
						empDTO.setEmployeeAddress(emp.getEmployeeAddress());
						empDTO.setGender(emp.getGender());
						empDTO.setPassword(emp.getPassword());

						return empDTO;
					}).collect(Collectors.toList());

					roomDto.setNumberOfEmployees(employeesDTO);
					return roomDto;
				}).collect(Collectors.toList());

				floorDto.setListOfRooms(roomDtos);
				return floorDto;
			}).collect(Collectors.toList());

			buildingDto.setFloors(floorDtos);
			return buildingDto;
		}).collect(Collectors.toList());

		return buildingDtos;
	}

}