package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.role.RoleRequest;
import com.tour.tour_management.dto.response.role.RoleResponse;
import com.tour.tour_management.entity.Permission;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.PermissionErrorCode;
import com.tour.tour_management.exception.RoleErrorCode;
import com.tour.tour_management.mapper.RoleMapper;
import com.tour.tour_management.repository.PermissionRepository;
import com.tour.tour_management.repository.RoleRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class RoleService {

    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    @PreAuthorize("hasRole('ACCESS_ROLE')")
    public List<RoleResponse> getRoles () {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();

        roleList.forEach( role ->{
            
            roleResponseList.add(roleMapper.toRoleResponse(role));
        });
        return roleResponseList;
    }
    @PreAuthorize("hasRole('ACCESS_ROLE')")
    public List<RoleResponse> getLockedRoles () {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();

        roleList.forEach( role ->{
            if (role.getStatus() == 0) {
                roleResponseList.add(roleMapper.toRoleResponse(role));
            }
        });
        return roleResponseList;
    }
    @PreAuthorize("hasRole('ACCESS_ROLE')")
    public List<RoleResponse> getActiveRoles () {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = new ArrayList<>();

        roleList.forEach( role ->{
            if (role.getStatus() == 1) {
                roleResponseList.add(roleMapper.toRoleResponse(role));
            }
        });
        return roleResponseList;
    }
    @PreAuthorize("hasRole('ACCESS_ROLE')")
    public RoleResponse getRole (String slug) {
        Role role = roleRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(() -> new AppException(RoleErrorCode.ROLE_NOT_FOUND));

        return roleMapper.toRoleResponse(role);
    }

    @PreAuthorize("hasRole('CREATE_ROLE')")
    public RoleResponse createRole (RoleRequest roleRequest) {
        Role role = new Role();
        Set<Permission> permissionSet = new HashSet<>();
        for (int i =0; i< roleRequest.getPermission().length; i++) {
                Permission permission = permissionRepository.findById(roleRequest.getPermission()[i])
                        .orElseThrow(() -> new AppException(PermissionErrorCode.PERMISSION_NOT_FOUND));
                permissionSet.add(permission);
        }
        role.setRole_name(roleRequest.getRole_name());
        role.setPermissions(permissionSet);
        role.setStatus(1);
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @PreAuthorize("hasRole('UPDATE_ROLE')")
    public RoleResponse updateRole (String slug, RoleRequest roleRequest) {
        Role role = roleRepository.findById(StringUtils.getIdFromUrl(slug))
                .orElseThrow(() -> new AppException(RoleErrorCode.ROLE_NOT_FOUND));
        Set<Permission> permissionSet = new HashSet<>();
        for (int i =0; i< roleRequest.getPermission().length; i++) {
            Permission permission = permissionRepository.findById(roleRequest.getPermission()[i])
                    .orElseThrow(() -> new AppException(PermissionErrorCode.PERMISSION_NOT_FOUND));
            permissionSet.add(permission);
        }
        role.setRole_name(roleRequest.getRole_name());
        role.setPermissions(permissionSet);

        return roleMapper.toRoleResponse(roleRepository.save(role));
    }

    @PreAuthorize("hasRole('CHANGE_ROLE_STATUS')")
    public RoleResponse changeStatusRole(String role_id) {
        Role role = roleRepository.findById(Integer.parseInt(role_id))
                .orElseThrow(() -> new AppException(RoleErrorCode.ROLE_NOT_FOUND));
        // cần khóa
        if (role.getStatus() == 1) {
            role.getAccounts().forEach(account -> {
                if (account.getStatus() == 1) {
                    throw new AppException(RoleErrorCode.ROLE_ACCOUNT_USE);
                }
            });
            role.setStatus(0);
        } else {
          role.setStatus(1);
        }
        return roleMapper.toRoleResponse(roleRepository.save(role));
    }
}
