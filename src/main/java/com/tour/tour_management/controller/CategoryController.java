package com.tour.tour_management.controller;

import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/category") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public  ApiResponse<List<CategoryResponse>> getAll() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result( categoryService.getAll())
                .build();
    }
}
