package com.ojas.spring.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberOfFloors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int floorId;
    private String floorName;

    @ManyToOne
    @JoinColumn(name = "building_id") // name of the foreign key column in NumberOfFloors table
    private NumberOfBuildings numberOfBuildings;

    @OneToMany(mappedBy = "numberOfFloors")
    private List<NumberOfRooms> rooms;
}
