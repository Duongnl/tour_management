package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.TourRequest;
import com.tour.tour_management.dto.response.TourResponse;
import com.tour.tour_management.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TourMapper {

     @Mapping(source = "category.category_id", target = "category_id")
     TourResponse toTourResponse (Tour tour) ;

     Tour toTour (TourRequest tourRequest);

//     void createTour(@MappingTarget  Tour tour, TourRequest tourRequest);
}
