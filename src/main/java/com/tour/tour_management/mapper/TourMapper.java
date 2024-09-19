package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.dto.response.reserve.ReserveTourTimeResponse;
import com.tour.tour_management.dto.response.tour.GetTourResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourMapper {

//     @Mapping(source = "category.category_id", target = "category_id")
//     @Mapping(source = "category.category_name", target = "category_name")
     TourResponse toTourResponse (Tour tour) ;



     @Mapping(source = "category.category_id", target = "category_id")
     @Mapping(source = "category.category_name", target = "category_name")
     GetTourResponse  toGetTourResponse (Tour tour);

     Tour toTour (TourCreateRequest tourCreateRequest);

     void updateTour(@MappingTarget Tour tour, TourUpdateRequest tourUpdateRequest);
     //     void createTour(@MappingTarget  Tour tour, TourRequest tourRequest);

}
