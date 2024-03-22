package com.ojas.spring.serviceImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ojas.spring.Repository.EmployeeRepository;
import com.ojas.spring.exceptions.CustomExceptions;
import com.ojas.spring.model.Employee;
import com.ojas.spring.service.EmpService;

@Service
public class EmployeeServiceImpl implements EmpService {

	private EmployeeRepository employeeRepository;

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
}
