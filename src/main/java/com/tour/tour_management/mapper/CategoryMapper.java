package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.CategoryRequest;
import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.dto.response.GetCategoryResponse;
import com.tour.tour_management.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CategoryMapper {

//    map category vao categoryResponse
//    @Mapping(source = "tours", target = "tours", qualifiedByName = "convert")
    GetCategoryResponse toGetCategoryResponse(Category category);

    CategoryResponse toCategoryResponse(Category category);

//    mapping category request to category entity
    Category toCategory(CategoryRequest categoryRequest );

    void updateCategory(@MappingTarget Category category, CategoryRequest categoryRequest);

//    @Named("convert")
//    default Set<TourConvert> convert(Set<Tour> tours){
//        Set<TourConvert> toursConvert = new HashSet<>();
//        tours.forEach(tour -> {
//            toursConvert.add(TourConvert.builder()
//                    .tour_id(tour.getTour_id())
//                    .tour_name(tour.getTour_name())
//                    .build());
//        });
//        return toursConvert;
//    }
//
//    @Getter
//    @Setter
//    @Builder
//    class TourConvert{
//        private String tour_id;
//        private String tour_name;
//    }
}
