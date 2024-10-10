package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.category.CategoryRequest;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.mapper.CategoryMapper;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;


// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getCategories(){
        List<Category> categoryList =  categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                        categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getLockedCategories(){
        List<Category> categoryList =  categoryRepository.findByStatus(0);
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                        categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getActiveCategories(){
        List<Category> categoryList =  categoryRepository.findByStatus(1);
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                    categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }


    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public CategoryResponse getCategory(String category_url) {

        if (StringUtils.getIdFromUrl(category_url) == -1) {
            throw new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND);
        }

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        return categoryMapper.toCategoryResponse(category);
    }

    @PreAuthorize("hasRole('CREATE_CATEGORY')")
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategory(categoryRequest);
        category.setStatus(1);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @PreAuthorize("hasRole('UPDATE_CATEGORY')")
    public CategoryResponse updateCategory(String category_url , CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));

        categoryMapper.updateCategory(category, categoryRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    @PreAuthorize("hasRole('CHANGE_CATEGORY_STATUS')")
    public CategoryResponse changeCategoryStatus (String category_id ) {
        Category category = categoryRepository.findById(Integer.parseInt(category_id))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
                // cần khóa
        if (category.getStatus() == 1) {
            category.getTours().forEach(tour -> {
                if (tour.getStatus() == 1) {
                    throw new AppException(CategoryErrorCode.CATEGORY_TOUR_USE);
                }
            });
            category.setStatus(0);
        } else {
            category.setStatus(1);
        }
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


    @PreAuthorize("hasRole('CHANGE_CATEGORY_STATUS')")
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
