package com.tour.tour_management.controller;


import com.tour.tour_management.dto.request.role.RoleRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.role.RoleResponse;
import com.tour.tour_management.service.RoleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/role") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {

    RoleService roleService;

    @GetMapping
    public ApiResponse<List<RoleResponse>> getRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result( roleService.getRoles())
                .build();
    }

    @GetMapping("/active")
    public ApiResponse<List<RoleResponse>> getActiveRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result( roleService.getActiveRoles())
                .build();
    }

    @GetMapping("/locked")
    public ApiResponse<List<RoleResponse>> getLockedRoles() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result( roleService.getLockedRoles())
                .build();
    }


    @GetMapping("/{slug}")
    public ApiResponse<RoleResponse> getRole (@PathVariable String slug) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.getRole(slug))
                .build();
    }

    @PostMapping
    public ApiResponse<RoleResponse> createRole (@RequestBody @Valid RoleRequest roleRequest) {
       return ApiResponse.<RoleResponse>builder()
               .result(roleService.createRole(roleRequest))
               .build();
    }

    @PutMapping("/{slug}")
    public ApiResponse<RoleResponse> updateRole (@PathVariable String slug,@RequestBody @Valid RoleRequest roleRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.updateRole(slug,roleRequest))
                .build();
    }

    @PutMapping("/change-status/{role_id}")
    public ApiResponse<RoleResponse> changeStatusRole (@PathVariable String role_id) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.changeStatusRole(role_id))
                .build();
    }
}
