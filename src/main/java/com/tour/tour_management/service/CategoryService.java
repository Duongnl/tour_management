package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.CategoryRequest;
import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.dto.response.GetCategoryResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.mapper.CategoryMapper;
import com.tour.tour_management.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;


    public List<CategoryResponse> getCategories(){
        List<Category> categoryList =  categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                        categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    public List<CategoryResponse> getDeletedCategories(){
        List<Category> categoryList =  categoryRepository.findByStatus(0);
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                        categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    public GetCategoryResponse getCategory(int category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toGetCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        category.setStatus(1);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse updateCategory(int category_id , CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        categoryMapper.updateCategory(category, categoryRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse undoCategory(int category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        // hiển thị lại các tour trong danh mục đó
        category.getTours().forEach(
                tour -> {tour.setStatus(1);}
        );
        // hiển thị lại tour
        category.setStatus(1);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }


    public CategoryResponse deleteCategory(int category_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        // ẩn hết các tour trong danh mục đó
        category.getTours().forEach(
                tour -> {tour.setStatus(0);}
        );
        // ẩn danh mục đó
        category.setStatus(0);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }
}
