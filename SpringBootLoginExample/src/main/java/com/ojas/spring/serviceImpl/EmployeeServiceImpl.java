package com.ojas.spring.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.EmployeeRepository;
import com.ojas.spring.Repository.RoomsRepository;
import com.ojas.spring.dto.NumberOfEmployees;
import com.ojas.spring.exceptions.CustomExceptions;
import com.ojas.spring.model.Employee;
import com.ojas.spring.model.NumberOfRooms;
import com.ojas.spring.service.EmpService;

@Service
public class EmployeeServiceImpl implements EmpService {

	private EmployeeRepository employeeRepository;

	@Autowired
	private RoomsRepository roomRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public String create(Employee employee) {

		employeeRepository.save(employee);

		return "Data inserted in the database";
	}

	@Override
	public String userLogin(Employee employee) {

		Optional<Employee> emailAndPassword = employeeRepository.findByEmailAndPassword(employee.getEmail(),
				employee.getPassword());

		if (emailAndPassword.isPresent()) {
			System.out.println("Email" + emailAndPassword);
			return "login successfull";
		} else {
			throw new CustomExceptions("Invalid Credenitials");
		}
	}

	@Override
	public List<NumberOfEmployees> getAllEmployeesByRoomId(int roomId) {
		
		// Find the room by roomId
		NumberOfRooms room = roomRepository.findById(roomId).orElse(null);

		// If the room doesn't exist, return an empty list
		if (room == null) {
			return Collections.emptyList();
		}

		// Get the list of employees in the room
		List<Employee> employees = room.getEmployee();

		// Convert the list of Employee entities to NumberOfEmployees DTOs
		return employees.stream().map(employee -> {
			NumberOfEmployees numberOfEmployees = new NumberOfEmployees();
			numberOfEmployees.setEmployeeName(employee.getEmployeeName());
			numberOfEmployees.setEmail(employee.getEmail());
			numberOfEmployees.setAge(employee.getAge());
			numberOfEmployees.setGender(employee.getGender());
			numberOfEmployees.setPassword(employee.getPassword());
			numberOfEmployees.setEmployeeAddress(employee.getEmployeeAddress());
			return numberOfEmployees;
		}).toList();
	}

}
