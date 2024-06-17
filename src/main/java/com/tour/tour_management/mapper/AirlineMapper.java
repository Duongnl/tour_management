package com.tour.tour_management.mapper;

import com.tour.tour_management.dto.request.AirlineRequest;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.entity.Airline;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AirlineMapper
{
    Airline toAirline (AirlineRequest airlineRequest);
    AirlineResponse toAirlineResponse (Airline airline);
    void updateAirline (@MappingTarget Airline airline, AirlineRequest airlineRequest);
}
