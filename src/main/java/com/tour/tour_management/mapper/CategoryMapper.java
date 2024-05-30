package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.CategoryResponse;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.entity.Tour;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.Set;

// khoi tao mapper
@Mapper(componentModel = "spring")
public interface CategoryMapper {
//    map category vao categoryResponse

    @Mapping(source = "tours", target = "tours", qualifiedByName = "convert")
    CategoryResponse toCategoryResponse(Category category);


    @Named("convert")
    default Set<TourConvert> convert(Set<Tour> tours){
        Set<TourConvert> toursConvert = new HashSet<>();
        tours.forEach(tour -> {
            toursConvert.add(TourConvert.builder()
                    .tour_id(tour.getTour_id())
                    .tour_name(tour.getTour_name())
                    .build());
        });
        return toursConvert;
    }

    @Getter
    @Setter
    @Builder
    class TourConvert{
        private String tour_id;
        private String tour_name;
    }
}
