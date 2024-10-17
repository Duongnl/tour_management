package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.category.CategoryRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.category.CategoryResponse;
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
    @GetMapping("/locked")
    public  ApiResponse<List<CategoryResponse>> getDeletedCategories() {
    return ApiResponse.<List<CategoryResponse>>builder()
            .result( categoryService.getLockedCategories())
            .build();
    }

    @GetMapping("/active")
    public  ApiResponse<List<CategoryResponse>> getActiveCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result( categoryService.getActiveCategories())
                .build();
    }



    @GetMapping("/{category_url}")
    public  ApiResponse<CategoryResponse> getCategory(@PathVariable String category_url) {
        return ApiResponse.<CategoryResponse>builder()
                .result( categoryService.getCategory(category_url))
                .build();
    }

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory (@RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(categoryRequest))
                .build();
    }

    @PutMapping("/{category_url}")
    public ApiResponse<CategoryResponse> updateCategory (@PathVariable String category_url
            ,@RequestBody @Valid CategoryRequest categoryRequest){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(category_url,categoryRequest))
                .build();
    }

    @PutMapping("/change-status/{category_id}")
    public ApiResponse<CategoryResponse> changeStatusCategory (@PathVariable String category_id) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.changeCategoryStatus(category_id))
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
