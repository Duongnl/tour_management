package com.tour.tour_management.controller;

import com.tour.tour_management.dto.request.CategoryRequest;
import com.tour.tour_management.dto.response.ApiResponse;
import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.dto.response.GetCategoryResponse;
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

    @GetMapping("/{category_id}")
    public  ApiResponse<GetCategoryResponse> getCategory(@PathVariable Integer category_id) {
        return ApiResponse.<GetCategoryResponse>builder()
                .result( categoryService.getCategory(category_id))
                .build();
    }

    @PostMapping
    public ApiResponse<CategoryResponse> createCategory (@RequestBody @Valid CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(categoryRequest))
                .build();
    }

    @PutMapping("/{category_id}")
    public ApiResponse<CategoryResponse> updateCategory (@PathVariable Integer category_id
            ,@RequestBody @Valid CategoryRequest categoryRequest){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(category_id,categoryRequest))
                .build();
    }

    @PutMapping("/undoCategory/{category_id}")
    public ApiResponse<CategoryResponse> undoCategory (@PathVariable Integer category_id){
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.undoCategory(category_id))
                .build();
    }

    @DeleteMapping("/{category_id}")
    public ApiResponse deleteCategory (@PathVariable Integer category_id) {
        return ApiResponse.builder()
                .result( categoryService.deleteCategory(category_id))
                .build();
    }

}
