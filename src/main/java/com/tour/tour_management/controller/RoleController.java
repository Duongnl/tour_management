package com.tour.tour_management.controller;


import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.RoleResponse;
import com.tour.tour_management.dto.response.account.AccountResponse;
import com.tour.tour_management.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
