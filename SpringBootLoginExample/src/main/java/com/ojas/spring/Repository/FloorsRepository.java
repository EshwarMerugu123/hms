package com.ojas.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.spring.model.NumberOfFloors;

@Repository
public interface FloorsRepository extends JpaRepository<NumberOfFloors, Integer> {

}
