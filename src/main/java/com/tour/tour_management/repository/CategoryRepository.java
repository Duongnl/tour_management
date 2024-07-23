package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//String boi vi kieu du lieu cua category_id la integer
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    List<Category> findByStatus(int status);

}
