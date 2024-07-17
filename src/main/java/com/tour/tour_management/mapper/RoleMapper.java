package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.RoleResponse;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.repository.RoleRepository;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse (Role role);
}
