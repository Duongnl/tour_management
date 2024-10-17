package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.airline.AirlineRequest;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.dto.response.category.CategoryResponse;
import com.tour.tour_management.entity.Airline;
import com.tour.tour_management.entity.Category;
import com.tour.tour_management.exception.AirlineErrorCode;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.mapper.AirlineMapper;
import com.tour.tour_management.repository.AirlineRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class AirlineService {
    AirlineRepository airlineRepository;
    AirlineMapper airlineMapper;
    HistoryService historyService;

    @PreAuthorize("hasRole('ACCESS_AIRLINE')")
    public List<AirlineResponse> getAirlines()  {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_AIRLINE')")
    public List<AirlineResponse> getAirlines(int status)  {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            if(airline.getStatus()==status)
            airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    @PreAuthorize("hasRole('CREATE_AIRLINE')")
    public AirlineResponse createAirline (AirlineRequest airlineCreateRequest) {
//        kiểm tra  xem thời gian xuất phát có trước thời gian bay hay không
//        if (airlineRequest.getDeparture_time().isAfter(airlineRequest.getReturn_time())) {
//            throw new AppException(AirlineErrorCode.AIRLINE_DEPARTURE_RETURN);
//        }
        Airline airline = airlineMapper.toAirline(airlineCreateRequest);
        airline.setStatus(1);
        airlineRepository.save(airline);
        historyService.createHistory("Created airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());

        return airlineMapper.toAirlineResponse(airline);
    }

    @PreAuthorize("hasRole('UPDATE_AIRLINE')")
    public AirlineResponse updateAirline ( String airline_slug , AirlineRequest airlineCreateRequest) {
        Airline airline = airlineRepository.findById(StringUtils.getIdFromUrl(airline_slug))
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));

        airlineMapper.updateAirline(airline, airlineCreateRequest);

        airlineRepository.save(airline);
        historyService.createHistory("Updated airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());

        return airlineMapper.toAirlineResponse(airline);
    }

    @PreAuthorize("hasRole('CHANGE_AIRLINE_STATUS')")
    public void deleteAirline (int airline_id) {
        Airline airline = airlineRepository.findById(airline_id)
                .orElseThrow(()-> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));
        airlineRepository.delete(airline);

        historyService.createHistory("Deleted airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());
        
    }

    @PreAuthorize("hasRole('CHANGE_AIRLINE_STATUS')")
    public AirlineResponse changeAirlineStatus (String airline_id ) {
        Airline airline = airlineRepository.findById(Integer.parseInt(airline_id))
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));
        // cần khóa
        if (airline.getStatus() == 1) {
            airline.getDepartureTourTimes().forEach(tourTime -> {
                if (tourTime.getStatus() == 1) {
                    throw new AppException(AirlineErrorCode.AIRLINE_TOUR_TIME_USE);
                }
            });
            airline.getReturnTourTimes().forEach(tourTime -> {
                if (tourTime.getStatus() == 1) {
                    throw new AppException(AirlineErrorCode.AIRLINE_TOUR_TIME_USE);
                }
            });

            airline.setStatus(0);
        } else {
            airline.setStatus(1);
        }

        airlineRepository.save(airline);
        historyService.createHistory("Changed status category: "+airline.getAirline_name()+" : "+airline.getAirline_id());

        return airlineMapper.toAirlineResponse(airline);
    }

    @PreAuthorize("hasRole('ACCESS_AIRLINE')")
    public AirlineResponse getAirline(String airline_url) {

        if (StringUtils.getIdFromUrl(airline_url) == -1) {
            throw new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND);
        }

        Airline airline = airlineRepository.findById(StringUtils.getIdFromUrl(airline_url))
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));
        return airlineMapper.toAirlineResponse(airline);
    }

}
