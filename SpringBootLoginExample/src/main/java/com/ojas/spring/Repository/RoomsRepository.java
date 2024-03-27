package com.ojas.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.spring.model.NumberOfRooms;

@Repository
public interface RoomsRepository extends JpaRepository<NumberOfRooms,Integer>{

}
