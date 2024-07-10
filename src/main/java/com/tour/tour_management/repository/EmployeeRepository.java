package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee,Integer> {
}
