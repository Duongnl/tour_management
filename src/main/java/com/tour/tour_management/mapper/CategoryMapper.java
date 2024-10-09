package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.category.CategoryRequest;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.dto.response.category.GetCategoryResponse;
import com.tour.tour_management.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    map category vao categoryResponse
//    @Mapping(source = "tours", target = "tours", qualifiedByName = "convert")
    GetCategoryResponse toGetCategoryResponse(Category category);

    CategoryResponse toCategoryResponse(Category category);

//    mapping category request to category entity
    Category toCategory(CategoryRequest categoryRequest);

    void updateCategory(@MappingTarget Category category, CategoryRequest categoryUpdateRequest);

    Category copy(Category category);
}
