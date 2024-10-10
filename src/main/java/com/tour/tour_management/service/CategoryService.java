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
import org.jetbrains.annotations.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    HistoryService historyService;

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                    categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getLockedCategories() {
        List<Category> categoryList = categoryRepository.findByStatus(0);
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        categoryList.forEach(
                category -> {
                    categoryResponseList.add(categoryMapper.toCategoryResponse(category));
                });
        return categoryResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_CATEGORY')")
    public List<CategoryResponse> getActiveCategories() {
        List<Category> categoryList = categoryRepository.findByStatus(1);
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

        Category categorySaved = categoryRepository.save(category);
        historyService.createHistory("Create category: " + categorySaved.getCategory_name());

        return categoryMapper.toCategoryResponse(categorySaved);
    }

    @PreAuthorize("hasRole('UPDATE_CATEGORY')")
    public CategoryResponse updateCategory(String category_url, CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(StringUtils.getIdFromUrl(category_url))
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Category categoryOld=categoryMapper.copy(category);

        categoryMapper.updateCategory(category, categoryRequest);

        Category categorySaved = categoryRepository.save(category);
        historyService.createHistory(getCategorytChangedString(categoryOld, categorySaved));

        return categoryMapper.toCategoryResponse(categorySaved);
    }

    @PreAuthorize("hasRole('CHANGE_CATEGORY_STATUS')")
    public CategoryResponse changeCategoryStatus(String category_id) {
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
        Category categorySaved = categoryRepository.save(category);
        historyService.createHistory("Changed status category: " + categorySaved.getCategory_name());
        return categoryMapper.toCategoryResponse(categorySaved);
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

        Category categorySaved = categoryRepository.save(category);
        historyService.createHistory("Changed status category: " + categorySaved.getCategory_name());

        return categoryMapper.toCategoryResponse(categorySaved);
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
                tour -> {
                    tour.setStatus(0);
                }
        );
        // ẩn danh mục đó
        category.setStatus(0);
        Category categorySaved = categoryRepository.save(category);
        historyService.createHistory("Changed status category: " + categorySaved.getCategory_name());
        return categoryMapper.toCategoryResponse(categorySaved);
    }

    private static @NotNull String getCategorytChangedString(Category category, Category categorySaved) {
        StringBuilder categoryChangedString = new StringBuilder("Update category: ");

        // lấy tất cả các field trong các entity
        Field[] fields = Category.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                // Lấy giá trị của field cho cả hai object
                field.setAccessible(true);
                Object value1 = field.get(category);
                Object value2 = field.get(categorySaved);

                // So sánh giá trị, nếu khác nhau thì thêm vào chuỗi thay đổi
                if (!Objects.equals(value1, value2)) {
                    categoryChangedString.append(" ")
                            .append(field.getName())
                            .append(": ")
                            .append(value1)
                            .append(" -> ")
                            .append(value2)
                            .append(";");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
            }
        }

        return categoryChangedString.toString();
    }
}
