package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Reserve;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OverviewRepository extends JpaRepository<Reserve, String> {
    @Query("SELECT a.employee_name AS employeeName, " +
            "SUM(CASE WHEN MONTH(r.time) = 1 THEN r.commission ELSE 0 END) AS Month1, " +
            "SUM(CASE WHEN MONTH(r.time) = 2 THEN r.commission ELSE 0 END) AS Month2, " +
            "SUM(CASE WHEN MONTH(r.time) = 3 THEN r.commission ELSE 0 END) AS Month3, " +
            "SUM(CASE WHEN MONTH(r.time) = 4 THEN r.commission ELSE 0 END) AS Month4, " +
            "SUM(CASE WHEN MONTH(r.time) = 5 THEN r.commission ELSE 0 END) AS Month5, " +
            "SUM(CASE WHEN MONTH(r.time) = 6 THEN r.commission ELSE 0 END) AS Month6, " +
            "SUM(CASE WHEN MONTH(r.time) = 7 THEN r.commission ELSE 0 END) AS Month7, " +
            "SUM(CASE WHEN MONTH(r.time) = 8 THEN r.commission ELSE 0 END) AS Month8, " +
            "SUM(CASE WHEN MONTH(r.time) = 9 THEN r.commission ELSE 0 END) AS Month9, " +
            "SUM(CASE WHEN MONTH(r.time) = 10 THEN r.commission ELSE 0 END) AS Month10, " +
            "SUM(CASE WHEN MONTH(r.time) = 11 THEN r.commission ELSE 0 END) AS Month11, " +
            "SUM(CASE WHEN MONTH(r.time) = 12 THEN r.commission ELSE 0 END) AS Month12, " +
            "SUM(r.commission) AS Total " +
            "FROM Reserve r " +
            "LEFT JOIN r.employee a " +
            "WHERE YEAR(r.time) = :year " +
            "GROUP BY a.employee_id")
    List<Tuple> getReportCommissionPerEmployeeAsTuple(@Param("year") int year);

    @Query("SELECT a.employee_name AS employeeName, " +
            "SUM(CASE WHEN MONTH(r.time) = 1 THEN r.price ELSE 0 END) AS Month1, " +
            "SUM(CASE WHEN MONTH(r.time) = 2 THEN r.price ELSE 0 END) AS Month2, " +
            "SUM(CASE WHEN MONTH(r.time) = 3 THEN r.price ELSE 0 END) AS Month3, " +
            "SUM(CASE WHEN MONTH(r.time) = 4 THEN r.price ELSE 0 END) AS Month4, " +
            "SUM(CASE WHEN MONTH(r.time) = 5 THEN r.price ELSE 0 END) AS Month5, " +
            "SUM(CASE WHEN MONTH(r.time) = 6 THEN r.price ELSE 0 END) AS Month6, " +
            "SUM(CASE WHEN MONTH(r.time) = 7 THEN r.price ELSE 0 END) AS Month7, " +
            "SUM(CASE WHEN MONTH(r.time) = 8 THEN r.price ELSE 0 END) AS Month8, " +
            "SUM(CASE WHEN MONTH(r.time) = 9 THEN r.price ELSE 0 END) AS Month9, " +
            "SUM(CASE WHEN MONTH(r.time) = 10 THEN r.price ELSE 0 END) AS Month10, " +
            "SUM(CASE WHEN MONTH(r.time) = 11 THEN r.price ELSE 0 END) AS Month11, " +
            "SUM(CASE WHEN MONTH(r.time) = 12 THEN r.price ELSE 0 END) AS Month12, " +
            "SUM(r.price) AS Total " +
            "FROM Reserve r " +
            "LEFT JOIN r.employee a " +
            "WHERE YEAR(r.time) = :year " +
            "GROUP BY a.employee_id")
    List<Tuple> getReportSalePerEmployeeAsTuple(@Param("year") int year);

    @Query("SELECT " +
            "SUM(CASE WHEN DAY(r.time) = 1 THEN r.price ELSE 0 END) AS Day1, " +
            "SUM(CASE WHEN DAY(r.time) = 2 THEN r.price ELSE 0 END) AS Day2, " +
            "SUM(CASE WHEN DAY(r.time) = 3 THEN r.price ELSE 0 END) AS Day3, " +
            "SUM(CASE WHEN DAY(r.time) = 4 THEN r.price ELSE 0 END) AS Day4, " +
            "SUM(CASE WHEN DAY(r.time) = 5 THEN r.price ELSE 0 END) AS Day5, " +
            "SUM(CASE WHEN DAY(r.time) = 6 THEN r.price ELSE 0 END) AS Day6, " +
            "SUM(CASE WHEN DAY(r.time) = 7 THEN r.price ELSE 0 END) AS Day7, " +
            "SUM(CASE WHEN DAY(r.time) = 8 THEN r.price ELSE 0 END) AS Day8, " +
            "SUM(CASE WHEN DAY(r.time) = 9 THEN r.price ELSE 0 END) AS Day9, " +
            "SUM(CASE WHEN DAY(r.time) = 10 THEN r.price ELSE 0 END) AS Day10, " +
            "SUM(CASE WHEN DAY(r.time) = 11 THEN r.price ELSE 0 END) AS Day11, " +
            "SUM(CASE WHEN DAY(r.time) = 12 THEN r.price ELSE 0 END) AS Day12, " +
            "SUM(CASE WHEN DAY(r.time) = 13 THEN r.price ELSE 0 END) AS Day13, " +
            "SUM(CASE WHEN DAY(r.time) = 14 THEN r.price ELSE 0 END) AS Day14, " +
            "SUM(CASE WHEN DAY(r.time) = 15 THEN r.price ELSE 0 END) AS Day15, " +
            "SUM(CASE WHEN DAY(r.time) = 16 THEN r.price ELSE 0 END) AS Day16, " +
            "SUM(CASE WHEN DAY(r.time) = 17 THEN r.price ELSE 0 END) AS Day17, " +
            "SUM(CASE WHEN DAY(r.time) = 18 THEN r.price ELSE 0 END) AS Day18, " +
            "SUM(CASE WHEN DAY(r.time) = 19 THEN r.price ELSE 0 END) AS Day19, " +
            "SUM(CASE WHEN DAY(r.time) = 20 THEN r.price ELSE 0 END) AS Day20, " +
            "SUM(CASE WHEN DAY(r.time) = 21 THEN r.price ELSE 0 END) AS Day21, " +
            "SUM(CASE WHEN DAY(r.time) = 22 THEN r.price ELSE 0 END) AS Day22, " +
            "SUM(CASE WHEN DAY(r.time) = 23 THEN r.price ELSE 0 END) AS Day23, " +
            "SUM(CASE WHEN DAY(r.time) = 24 THEN r.price ELSE 0 END) AS Day24, " +
            "SUM(CASE WHEN DAY(r.time) = 25 THEN r.price ELSE 0 END) AS Day25, " +
            "SUM(CASE WHEN DAY(r.time) = 26 THEN r.price ELSE 0 END) AS Day26, " +
            "SUM(CASE WHEN DAY(r.time) = 27 THEN r.price ELSE 0 END) AS Day27, " +
            "SUM(CASE WHEN DAY(r.time) = 28 THEN r.price ELSE 0 END) AS Day28, " +
            "SUM(CASE WHEN DAY(r.time) = 29 THEN r.price ELSE 0 END) AS Day29, " +
            "SUM(CASE WHEN DAY(r.time) = 30 THEN r.price ELSE 0 END) AS Day30, " +
            "SUM(CASE WHEN DAY(r.time) = 31 THEN r.price ELSE 0 END) AS Day31, " +
            "SUM(r.price) AS Total," +
            "MONTH(r.time) AS Month " +
            "FROM Reserve r " +
            "WHERE YEAR(r.time) = :year AND MONTH(r.time) BETWEEN :fromMonth AND :toMonth " +
            "GROUP BY MONTH(r.time)")
    List<Tuple> getReportSaleAsTuple(@Param("year") int year, @Param("fromMonth") int fromMonth, @Param("toMonth") int toMonth);

    @Query("SELECT " +
            "SUM(CASE WHEN DAY(r.time) = 1 THEN r.commission ELSE 0 END) AS Day1, " +
            "SUM(CASE WHEN DAY(r.time) = 2 THEN r.commission ELSE 0 END) AS Day2, " +
            "SUM(CASE WHEN DAY(r.time) = 3 THEN r.commission ELSE 0 END) AS Day3, " +
            "SUM(CASE WHEN DAY(r.time) = 4 THEN r.commission ELSE 0 END) AS Day4, " +
            "SUM(CASE WHEN DAY(r.time) = 5 THEN r.commission ELSE 0 END) AS Day5, " +
            "SUM(CASE WHEN DAY(r.time) = 6 THEN r.commission ELSE 0 END) AS Day6, " +
            "SUM(CASE WHEN DAY(r.time) = 7 THEN r.commission ELSE 0 END) AS Day7, " +
            "SUM(CASE WHEN DAY(r.time) = 8 THEN r.commission ELSE 0 END) AS Day8, " +
            "SUM(CASE WHEN DAY(r.time) = 9 THEN r.commission ELSE 0 END) AS Day9, " +
            "SUM(CASE WHEN DAY(r.time) = 10 THEN r.commission ELSE 0 END) AS Day10, " +
            "SUM(CASE WHEN DAY(r.time) = 11 THEN r.commission ELSE 0 END) AS Day11, " +
            "SUM(CASE WHEN DAY(r.time) = 12 THEN r.commission ELSE 0 END) AS Day12, " +
            "SUM(CASE WHEN DAY(r.time) = 13 THEN r.commission ELSE 0 END) AS Day13, " +
            "SUM(CASE WHEN DAY(r.time) = 14 THEN r.commission ELSE 0 END) AS Day14, " +
            "SUM(CASE WHEN DAY(r.time) = 15 THEN r.commission ELSE 0 END) AS Day15, " +
            "SUM(CASE WHEN DAY(r.time) = 16 THEN r.commission ELSE 0 END) AS Day16, " +
            "SUM(CASE WHEN DAY(r.time) = 17 THEN r.commission ELSE 0 END) AS Day17, " +
            "SUM(CASE WHEN DAY(r.time) = 18 THEN r.commission ELSE 0 END) AS Day18, " +
            "SUM(CASE WHEN DAY(r.time) = 19 THEN r.commission ELSE 0 END) AS Day19, " +
            "SUM(CASE WHEN DAY(r.time) = 20 THEN r.commission ELSE 0 END) AS Day20, " +
            "SUM(CASE WHEN DAY(r.time) = 21 THEN r.commission ELSE 0 END) AS Day21, " +
            "SUM(CASE WHEN DAY(r.time) = 22 THEN r.commission ELSE 0 END) AS Day22, " +
            "SUM(CASE WHEN DAY(r.time) = 23 THEN r.commission ELSE 0 END) AS Day23, " +
            "SUM(CASE WHEN DAY(r.time) = 24 THEN r.commission ELSE 0 END) AS Day24, " +
            "SUM(CASE WHEN DAY(r.time) = 25 THEN r.commission ELSE 0 END) AS Day25, " +
            "SUM(CASE WHEN DAY(r.time) = 26 THEN r.commission ELSE 0 END) AS Day26, " +
            "SUM(CASE WHEN DAY(r.time) = 27 THEN r.commission ELSE 0 END) AS Day27, " +
            "SUM(CASE WHEN DAY(r.time) = 28 THEN r.commission ELSE 0 END) AS Day28, " +
            "SUM(CASE WHEN DAY(r.time) = 29 THEN r.commission ELSE 0 END) AS Day29, " +
            "SUM(CASE WHEN DAY(r.time) = 30 THEN r.commission ELSE 0 END) AS Day30, " +
            "SUM(CASE WHEN DAY(r.time) = 31 THEN r.commission ELSE 0 END) AS Day31, " +
            "SUM(r.commission) AS Total, " +
            "MONTH(r.time) AS Month " +
            "FROM Reserve r " +
            "WHERE YEAR(r.time) = :year AND MONTH(r.time) BETWEEN :fromMonth AND :toMonth " +
            "GROUP BY MONTH(r.time)")
    List<Tuple> getReportCommissionAsTuple(@Param("year") int year, @Param("fromMonth") int fromMonth, @Param("toMonth") int toMonth);

    @Query("SELECT " +
            "SUM(r.commission) AS Total " +
            "FROM Reserve r " +
            "WHERE YEAR(r.time) = :year AND MONTH(r.time) BETWEEN :fromMonth AND :toMonth " +
            "GROUP BY MONTH(r.time)")
    int getTotalSale(@Param("year") int year, @Param("fromMonth") int fromMonth, @Param("toMonth") int toMonth);

}
