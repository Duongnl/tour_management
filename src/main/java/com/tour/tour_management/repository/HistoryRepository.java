package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.History;
import com.tour.tour_management.entity.Employee;
import com.tour.tour_management.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History,String> {

    @Query("SELECT a FROM History a WHERE a.time BETWEEN :startDate AND :endDate ")
        List<History> findHistoryByTime(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate );

    @Query("SELECT c FROM History c ORDER BY c.time DESC")
    List<History> findAllOrderedByDateTime();
}
