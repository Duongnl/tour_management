package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.dto.response.reserve.ReserveTourTimeResponse;
import com.tour.tour_management.dto.response.role.RoleResponse;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.entity.TourTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReserveMapper {

    // reserve
    @Mapping(source = "category.category_name", target = "category_name")
    ReserveTourResponse toReserveTourResponse (Tour tour);
    ReserveTourTimeResponse toReserveTourTimeResponse(TourTime tourTime);


}
