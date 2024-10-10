package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Tour;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

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

//    SELECT * FROM tour JOIN tour_time ON tour.tour_id = tour_time.tour_id
//    WHERE tour_time.departure_date >= '2024-08-24' AND tour_time.departure_date <= '2024-09-07'
//    @Query("SELECT Tour FROM Tour JOIN Tour.tourTimes WHERE tourTimes.departure_date >= '2024-08-24' AND tourTimes.departure_date <= '2024-09-07'")
@Query("SELECT t FROM Tour t JOIN t.tourTimes tt WHERE (tt.departure_date BETWEEN :startDay AND :endDay OR :startDay IS NULL OR :endDay  IS NULL) AND (t.category.category_id = :category_id OR  :category_id IS NULL)")
List<Tour> daysFilterReserveTours(@Param("startDay") LocalDate startDay, @Param("endDay") LocalDate endDay, @Param("category_id") Integer category_id);


    @Query("SELECT t FROM Tour t JOIN t.tourTimes tt WHERE t.category.category_id = :category_id AND tt.departure_date BETWEEN :startDay AND :endDay")
    List <Tour> filterReserveTours (@Param("category_id")  Integer category_id,@Param("startDay") LocalDate startDay, @Param("endDay") LocalDate endDay);

}
