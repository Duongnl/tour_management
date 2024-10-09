package com.tour.tour_management.repository;


import com.tour.tour_management.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository  extends JpaRepository<Airline,Integer> {

}
