package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Customer;
import com.tour.tour_management.entity.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//String boi vi kieu du lieu cua customer_id la integer
public interface TourRepository extends JpaRepository<Tour,Integer> {
    List<Tour> findByStatus (int status);



    //lấy danh sách người dùng theo trang thái và sắp xếp
    @Query("SELECT c FROM Tour c WHERE c.status = ?1")
    List<Tour> findByStatusWithSorting(@Param("status") int status, Sort sort);

    //lấy danh sách người dùng theo trang thái và sắp xếp
    @Query("SELECT c FROM Tour c WHERE c.category.category_id = ?1")
    List<Tour> findByCategoryId(@Param("category_id") int category_id);

}
