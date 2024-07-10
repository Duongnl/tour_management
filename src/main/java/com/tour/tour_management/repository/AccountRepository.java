package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " + "FROM Account  a  WHERE a.account_name = ?1")
    boolean existedAccountName( String account_name);

    @Query ("SELECT a FROM Account a WHERE a.account_name = ?1")
    Optional<Account> findByAccountName (String username);
}
