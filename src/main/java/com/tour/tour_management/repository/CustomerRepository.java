package com.tour.tour_management.repository;

import com.tour.tour_management.dto.response.customer.CustomerResponse;
import com.tour.tour_management.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
//String boi vi kieu du lieu cua customer_id la integer
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findByStatus(int status);

//kiểm tra số điện thoại đã được sử dụng chưa
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.phone_number = :phone_number")
    boolean existsByPhone_number(@Param("phone_number") String phone_number);

// kiểm tra người dùng có tồn tại không
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.customer_id = :customer_id")
    boolean existsByCustomer_id(@Param("customer_id") Integer customer_id);



}
