package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//String boi vi kieu du lieu cua customer_id la integer
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByStatus(int status);

}
