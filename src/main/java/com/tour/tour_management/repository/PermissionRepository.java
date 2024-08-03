package com.tour.tour_management.repository;

import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,String> {


}
