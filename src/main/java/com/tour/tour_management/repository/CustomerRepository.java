package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
//String boi vi kieu du lieu cua customer_id la integer
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    List<Customer> findByStatus(int status);

    //lấy danh sách người dùng theo trang thái và sắp xếp
    @Query("SELECT c FROM Customer c WHERE c.status = ?1")
    List<Customer> findByStatusWithSorting(@Param("status") int status, Sort sort);

     //kiểm tra số điện thoại đã được sử dụng chưa
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.phone_number = :phone_number")
    boolean existsByPhone_number(@Param("phone_number") String phone_number);

    /**
     * kiểm tra customer có tồn tại không
     * @param customer_id id customer
     * @return true nếu customer đó tồn tại
     */
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.customer_id = :customer_id")
    boolean existsByCustomer_id(@Param("customer_id") Integer customer_id);

    /**
     * Lấy danh sách khách hàng theo tên
     * @param customer_name tên khách hàng
     * @return danh sách khách hàng tương ứng với tên đã cho
     */
    @Query ("SELECT c FROM Customer c WHERE c.customer_name = ?1")
    Optional<Customer> findByCustomerName (String customer_name);

    /**
     * Lấy danh sách người dùng sắp xếp theo thời gian tạo
     * @return danh sách khách hàng  theo thứ tự người mới tạo trước
     */
    @Query("SELECT c FROM Customer c ORDER BY c.time DESC")
    List<Customer> findAllOrderedByDateTime();
}
