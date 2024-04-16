package com.ojas.spring.service;

import java.util.List;

import com.ojas.spring.dto.NumberOfEmployees;
import com.ojas.spring.model.Employee;

public interface EmpService {

	public String create(Employee employee);
	
	public String userLogin(Employee employee);
	
	List<NumberOfEmployees> getAllEmployeesByRoomId(int roomId);

}
