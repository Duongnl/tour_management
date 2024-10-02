package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.reserve.ReserveRequests;
import com.tour.tour_management.dto.request.reserve.ReserveTourFilterRequest;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.entity.*;
import com.tour.tour_management.exception.*;
import com.tour.tour_management.mapper.CustomerMapper;
import com.tour.tour_management.mapper.ReserveMapper;
import com.tour.tour_management.repository.*;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class ReserveService {

    TourRepository tourRepository;
    ReserveMapper reserveMapper;
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    EmployeeRepository employeeRepository;
    TourTimeRepository tourTimeRepository;

    private final ReserveRepository reserveRepository;

    public List<ReserveTourResponse> getReserveTours () {
        List<Tour> tourList = tourRepository.findAll();
        List<ReserveTourResponse> reserveTourList = new ArrayList<>();
        tourList.forEach(tour -> {

            tour.getTourTimes().forEach(tourTime -> {
                ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                reserveTourList.add(reserveTour);
            });
        });
        return reserveTourList;
    }

    public ReserveTourResponse getReserveTour (String slug) {
        if (StringUtils.getIdFromUrl(slug) == -1) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        List<Tour> tourList = tourRepository.findAll();
        List<ReserveTourResponse> getReserveTour = new ArrayList<>();
        tourList.forEach(tour -> {
            tour.getTourTimes().forEach(tourTime -> {
                if (tourTime.getTour_time_id() == StringUtils.getIdFromUrl(slug)) {
                    ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                    reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                    getReserveTour.add(reserveTour);
                }


            });
        });
        if(getReserveTour.isEmpty()) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        return getReserveTour.getFirst();
    }



    public boolean isDateInRange(LocalDate departureDate, LocalDate startDate, LocalDate endDate) {
        return !departureDate.isBefore(startDate) && !departureDate.isAfter(endDate);
    }

    public List<ReserveTourResponse> filterReserveTour (ReserveTourFilterRequest reserveTourFilterRequest) {

        System.out.println("data reserve" + reserveTourFilterRequest);
        List<Tour> tourList = new ArrayList<>();
        if (!reserveTourFilterRequest.getCategory_slug().isEmpty()) {
           tourList = tourRepository.daysFilterReserveTours(reserveTourFilterRequest.getStart_date(), reserveTourFilterRequest.getEnd_date(),  StringUtils.getIdFromUrl(reserveTourFilterRequest.getCategory_slug()));
        } else {
           tourList = tourRepository.daysFilterReserveTours(reserveTourFilterRequest.getStart_date(), reserveTourFilterRequest.getEnd_date(), null);
        }

        List<ReserveTourResponse> reserveTourList = new ArrayList<>();

            if (reserveTourFilterRequest.getStart_date() !=null  && reserveTourFilterRequest.getEnd_date() != null ) {
                tourList.forEach(tour -> {
                    tour.getTourTimes().forEach(tourTime -> {
                        if(isDateInRange(tourTime.getDeparture_date(), reserveTourFilterRequest.getStart_date(),reserveTourFilterRequest.getEnd_date())) {
                            ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                            reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                            reserveTourList.add(reserveTour);
                        }
                    });
                });
            } else {
                tourList.forEach(tour -> {
                    tour.getTourTimes().forEach(tourTime -> {
                        ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                        reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                        reserveTourList.add(reserveTour);
                    });
                });
            }

        return reserveTourList;
    }


    public String reserveTour (ReserveRequests reserveRequests) {

        List<Reserve> reserveList = new ArrayList<>();
        Employee employee = employeeRepository.findById(reserveRequests.getReserveRequests().getFirst().getTour_time_id())
                .orElseThrow(()-> new AppException(EmployeeErrorCode.EMPLOYEE_NOT_FOUND));
        TourTime tourTime = tourTimeRepository.findById(reserveRequests.getReserveRequests().getFirst().getTour_time_id())
                .orElseThrow(()-> new AppException(TourTimeErrorCode.TIME_NOT_FOUND));

        if (tourTime.getQuantity_left() < reserveRequests.getReserveRequests().size()) {
            throw new AppException(ReserveErrorCode.RESERVE_QUANTITY_NOT_ENOUGH);
        }

        reserveRequests.getReserveRequests().forEach(reserveRequest -> {
            Reserve reserve = new Reserve();

            reserve = reserveMapper.toReserve(reserveRequest);


            reserve.setTourTime(tourTime);
            reserve.setEmployee(employee);
            reserve.setStatus(2);
            reserve.setPay(0);
            // neu khach hang co san
            if(reserveRequest.getCustomerResponse().getCustomer_id() != null) {
                Customer customer = customerRepository.findById(reserveRequest.getCustomerResponse().getCustomer_id())
                        .orElseThrow(()-> new AppException(CustomerErrorCode.CUSTOMER_NOT_FOUND));
                reserve.setCustomer(customer);

            }
            else {
               Customer customer = customerMapper.toCustomerFromResponse(reserveRequest.getCustomerResponse());
               customer.setRelationship_name("Chủ hộ");
               reserve.setCustomer(customer);
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            reserve.setTime(localDateTime);
            reserveList.add(reserve);
        });
        reserveList.forEach(reserve -> {customerRepository.save(reserve.getCustomer());});
        reserveRepository.saveAll(reserveList);

        tourTime.setQuantity_reserve(tourTime.getQuantity_reserve() + reserveRequests.getReserveRequests().size());
        tourTime.setQuantity_left(tourTime.getQuantity() -  tourTime.getQuantity_reserve());
        tourTimeRepository.save(tourTime);

        return "success";
    }



}
