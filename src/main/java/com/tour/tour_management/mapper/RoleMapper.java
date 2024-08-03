package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.role.GetRoleResponse;
import com.tour.tour_management.dto.response.role.RoleResponse;
import com.tour.tour_management.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse (Role role);

    GetRoleResponse toGetRoleResponse (Role role);
}
