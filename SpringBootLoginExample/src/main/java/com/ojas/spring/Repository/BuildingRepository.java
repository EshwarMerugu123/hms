package com.ojas.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ojas.spring.model.Employee;
import com.ojas.spring.model.NumberOfBuildings;
@Repository
public interface BuildingRepository extends JpaRepository<NumberOfBuildings, Integer>{

}
