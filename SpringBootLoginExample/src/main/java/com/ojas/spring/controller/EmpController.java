package com.ojas.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.spring.model.Employee;
import com.ojas.spring.serviceImpl.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
public class EmpController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@PostMapping("/create")
	public String insert(@RequestBody Employee employee) {
		return employeeServiceImpl.create(employee);
	}

	@PostMapping("/login")
	public String userLogin(@RequestBody Employee employee) {
		return employeeServiceImpl.userLogin(employee);

	}
}
