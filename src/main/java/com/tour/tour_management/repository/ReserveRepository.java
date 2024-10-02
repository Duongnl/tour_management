package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Reserve;
import com.tour.tour_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve,Integer> {

}
