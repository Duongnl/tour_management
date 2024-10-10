package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.airline.AirlineCreateRequest;
import com.tour.tour_management.dto.response.AirlineResponse;
import com.tour.tour_management.entity.Airline;
import com.tour.tour_management.exception.AirlineErrorCode;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.mapper.AirlineMapper;
import com.tour.tour_management.repository.AirlineRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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

    public List<AirlineResponse> getAirlines()  {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    public List<AirlineResponse> getAirlines(int status)  {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            if(airline.getStatus()==status)
            airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    public AirlineResponse createAirline (AirlineCreateRequest airlineCreateRequest) {
//        kiểm tra  xem thời gian xuất phát có trước thời gian bay hay không
//        if (airlineRequest.getDeparture_time().isAfter(airlineRequest.getReturn_time())) {
//            throw new AppException(AirlineErrorCode.AIRLINE_DEPARTURE_RETURN);
//        }
        Airline airline = airlineMapper.toAirline(airlineCreateRequest);

        airlineRepository.save(airline);
        historyService.createHistory("Created airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());

        return airlineMapper.toAirlineResponse(airline);
    }

    public AirlineResponse updateAirline ( int airline_id , AirlineCreateRequest airlineCreateRequest) {
        Airline airline = airlineRepository.findById(airline_id)
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));

        //        kiểm tra  xem thời gian xuất phát có trước thời gian bay hay không
//        if (airlineRequest.getDeparture_time().isAfter(airlineRequest.getReturn_time())) {
//            throw new AppException(AirlineErrorCode.AIRLINE_DEPARTURE_RETURN);
//        }
        airlineMapper.updateAirline(airline, airlineCreateRequest);

        airlineRepository.save(airline);
        historyService.createHistory("Updated airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());

        return airlineMapper.toAirlineResponse(airline);
    }

    public void deleteAirline (int airline_id) {
        Airline airline = airlineRepository.findById(airline_id)
                .orElseThrow(()-> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));
        airlineRepository.delete(airline);

        historyService.createHistory("Deleted airline: "+airline.getAirline_name()+" : "+airline.getAirline_id());
        
    }

}
