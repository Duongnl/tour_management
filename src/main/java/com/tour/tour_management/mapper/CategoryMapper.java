package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.entity.Category;
import org.mapstruct.Mapper;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CategoryMapper {
//    map category vao categoryResponse
    CategoryResponse toCategoryResponse(Category category);
}
