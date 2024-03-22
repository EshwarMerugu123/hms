package com.ojas.spring.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ojas.spring.Repository.FloorsRepository;
import com.ojas.spring.dto.NumberFloorsDto;
import com.ojas.spring.model.NumberOfFloors;
import com.ojas.spring.service.FloorService;

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

	@Override
	public List<NumberFloorsDto> getAllfloors() {
		// TODO Auto-generated method stub
		return null;
	}

}
