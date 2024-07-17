package com.tour.tour_management.service;

import com.tour.tour_management.dto.response.RoleResponse;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.entity.Account;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.mapper.RoleMapper;
import com.tour.tour_management.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public List<RoleResponse> getRoles () {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();

        roleList.forEach( role ->{
            roleResponseList.add(roleMapper.toRoleResponse(role));
        });
        return roleResponseList;
    }

}
