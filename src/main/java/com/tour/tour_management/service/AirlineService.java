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
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class AirlineService {
    AirlineRepository airlineRepository;
    AirlineMapper airlineMapper;
    HistoryService historyService;

    public List<AirlineResponse> getAirlines() {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    public List<AirlineResponse> getAirlines(int status) {
        List<Airline> airlineList = airlineRepository.findAll();
        List<AirlineResponse> airlineResponseList = new ArrayList<>();
        airlineList.forEach(airline -> {
            if (airline.getStatus() == status)
                airlineResponseList.add(airlineMapper.toAirlineResponse(airline));
        });
        return airlineResponseList;
    }

    public AirlineResponse createAirline(AirlineCreateRequest airlineCreateRequest) {
        Airline airline = airlineMapper.toAirline(airlineCreateRequest);

        Airline airlineSaved = airlineRepository.save(airline);
        historyService.createHistory("Create Airline " + airlineSaved.getAirline_name());

        return airlineMapper.toAirlineResponse(airlineSaved);
    }

    public AirlineResponse updateAirline(int airline_id, AirlineCreateRequest airlineCreateRequest) {
        Airline airline = airlineRepository.findById(airline_id)
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));

        Airline airlineOld = airlineMapper.copy(airline);

        airlineMapper.updateAirline(airline, airlineCreateRequest);

        Airline airlineSaved = airlineRepository.save(airline);
        historyService.createHistory(getAirlineChangedString(airlineOld, airlineSaved));
        return airlineMapper.toAirlineResponse(airlineSaved);
    }

    private static @NotNull String getAirlineChangedString(Airline airline, Airline airlineSaved) {
        StringBuilder airlineChangedString = new StringBuilder("Update airline: ");

        // lấy tất cả các field trong các entity
        Field[] fields = Airline.class.getDeclaredFields();

        for (Field field : fields) {
            try {
                // Lấy giá trị của field cho cả hai object
                field.setAccessible(true);
                Object value1 = field.get(airline);
                Object value2 = field.get(airlineSaved);

                // So sánh giá trị, nếu khác nhau thì thêm vào chuỗi thay đổi
                if (!Objects.equals(value1, value2)) {
                    airlineChangedString.append(" ")
                            .append(field.getName())
                            .append(": ")
                            .append(value1)
                            .append(" -> ")
                            .append(value2)
                            .append(";");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace(); // Xử lý lỗi nếu có
            }
        }

        return airlineChangedString.toString();
    }


    public void deleteAirline(int airline_id) {
        Airline airline = airlineRepository.findById(airline_id)
                .orElseThrow(() -> new AppException(AirlineErrorCode.AIRLINE_NOT_FOUND));
        String name =airline.getAirline_name();
        airlineRepository.delete(airline);
        historyService.createHistory("Delete Airline " + name);
    }

}
