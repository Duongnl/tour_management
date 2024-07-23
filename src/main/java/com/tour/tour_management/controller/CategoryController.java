package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.category.CategoryCreateRequest;
import com.tour.tour_management.dto.request.category.CategoryUpdateRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.dto.response.category.GetCategoryResponse;
import com.tour.tour_management.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // khai bao controller
@RequestMapping("/category") // api mac dinh, de kh can khai bao o funtion
// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    @GetMapping
    public  ApiResponse<List<CategoryResponse>> getCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result( categoryService.getCategories())
                .build();
    }

//    getDeletedCategories
    @GetMapping("/getDeletedCategories")
    public  ApiResponse<List<CategoryResponse>> getDeletedCategories() {
    return ApiResponse.<List<CategoryResponse>>builder()
            .result( categoryService.getDeletedCategories())
            .build();
    }

    @GetMapping("/{category_url}")
    public  ApiResponse<GetCategoryResponse> getCategory(@PathVariable String category_url) {
        return ApiResponse.<GetCategoryResponse>builder()
                .result( categoryService.getCategory(category_url))
                .build();
    }

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory (@RequestBody @Valid CategoryCreateRequest categoryCreateRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(categoryCreateRequest))
                .build();
    }

    @PutMapping("/{category_url}")
    public ApiResponse<CategoryResponse> updateCategory (@PathVariable String category_url
            ,@RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(category_url,categoryUpdateRequest))
                .build();
    }

    @PutMapping("/undoCategory/{category_url}")
    public ApiResponse<CategoryResponse> undoCategory (@PathVariable String category_url){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.undoCategory(category_url))
                .build();
    }

    @DeleteMapping("/{category_url}")
    public ApiResponse deleteCategory (@PathVariable String category_url) {
        return ApiResponse.builder()
                .result( categoryService.deleteCategory(category_url))
                .build();
    }

}
