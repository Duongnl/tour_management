package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//String boi vi kieu du lieu cua category_id la string
public interface TourRepository extends JpaRepository<Tour,String> {


}
