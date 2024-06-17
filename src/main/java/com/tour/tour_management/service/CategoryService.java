package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.category.CategoryCreateRequest;
import com.tour.tour_management.dto.request.category.CategoryUpdateRequest;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.dto.response.category.GetCategoryResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.mapper.CategoryMapper;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public GetCategoryResponse getCategory(String category_url) {

        if (StringUtils.getIdFromUrl(category_url) == -1) {
            throw new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toGetCategoryResponse(category);
    }

    public CategoryResponse createCategory(CategoryCreateRequest categoryCreateRequest) {
        Category category = categoryMapper.toCategory(categoryCreateRequest);
        category.setStatus(1);
        category.setUrl(StringUtils.createSlug(category.getCategory_name()));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse updateCategory(String category_url , CategoryUpdateRequest categoryUpdateRequest) {

        if (StringUtils.getIdFromUrl(category_url) == -1) {
            throw new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        categoryMapper.updateCategory(category, categoryUpdateRequest);
        category.setUrl(StringUtils.createSlug(category.getUrl()));
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public CategoryResponse undoCategory(String category_url) {
        if (StringUtils.getIdFromUrl(category_url) == -1) {
            throw new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        // hiển thị lại các tour trong danh mục đó
        category.getTours().forEach(
                tour -> {tour.setStatus(1);}
        );
        // hiển thị lại tour
        category.setStatus(1);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }


    public CategoryResponse deleteCategory(String category_url) {
        if (StringUtils.getIdFromUrl(category_url) == -1) {
            throw new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
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
