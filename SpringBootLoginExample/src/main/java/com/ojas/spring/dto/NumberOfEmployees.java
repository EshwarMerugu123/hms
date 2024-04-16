package com.ojas.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfEmployees {
	private String employeeName;
	private String email;
	private int age;
	private String gender;
	private String password;
	private String employeeAddress;

}
