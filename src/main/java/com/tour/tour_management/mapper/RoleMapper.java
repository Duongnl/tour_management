package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.role.RoleResponse;
import com.tour.tour_management.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toRoleResponse (Role role);

    Role copy(Role role);

}
