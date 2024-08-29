package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Customer;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//String boi vi kieu du lieu cua customer_id la integer
public interface TourTimeRepository extends JpaRepository<TourTime,Integer> {
    List<Tour> findByStatus (int status);
}
