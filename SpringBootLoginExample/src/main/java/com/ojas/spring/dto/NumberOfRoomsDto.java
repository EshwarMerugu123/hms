package com.ojas.spring.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NumberOfRoomsDto {
	private int roomId;
	private String roomName;
	List<NumberOfEmployees> numberOfEmployees;
	private int employeesCount;
}
