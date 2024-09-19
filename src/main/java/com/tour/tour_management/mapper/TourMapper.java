package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeRequest;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourMapper {

     @Mapping(source = "category.category_id", target = "category_id")
     @Mapping(source = "category.category_name", target = "category_name")
     TourResponse toTourResponse (Tour tour) ;


     @Mapping(source = "category.category_id", target = "category_id")
     @Mapping(source = "category.category_name", target = "category_name")
     TourDetailResponse  toTourDetailResponse (Tour tour);

     Tour toTour (TourCreateRequest tourCreateRequest);
     Tour toTour (TourDetailResponse tourDetailResponse);

     void updateTour(@MappingTarget Tour tour, TourUpdateRequest tourUpdateRequest);

     void updateTourTime(@MappingTarget TourTime tourTime, TourTimeRequest tourTimeRequest);

     void CreateTourTime(@MappingTarget TourTime tourTime, TourTimeRequest tourTimeRequest);
     TourTime toTourTime(TourTimeRequest tourTimeRequest);
     //     void createTour(@MappingTarget  Tour tour, TourRequest tourRequest);

}
