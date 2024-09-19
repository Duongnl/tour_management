package com.tour.tour_management.service;


import com.tour.tour_management.dto.request.reserve.ReserveTourFilterRequest;
import com.tour.tour_management.dto.response.reserve.ReserveTourResponse;
import com.tour.tour_management.entity.Role;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.mapper.ReserveMapper;
import com.tour.tour_management.repository.RoleRepository;
import com.tour.tour_management.repository.TourRepository;
import com.tour.tour_management.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<ReserveTourResponse> getReserveTour () {
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

    public boolean isDateInRange(LocalDate departureDate, LocalDate startDate, LocalDate endDate) {
        return !departureDate.isBefore(startDate) && !departureDate.isAfter(endDate);
    }

    public List<ReserveTourResponse> filterReserveTour (ReserveTourFilterRequest reserveTourFilterRequest) {
        List<Tour> tourList = new ArrayList<>();
        List<ReserveTourResponse> reserveTourList = new ArrayList<>();

        // loc moi danh muc
        if (Objects.nonNull(reserveTourFilterRequest.getCategory_slug())
        ) {
            tourList = tourRepository.categoryFilterReserveTours(StringUtils.getIdFromUrl(reserveTourFilterRequest.getCategory_slug()));
            tourList.forEach(tour -> {
                tour.getTourTimes().forEach(tourTime -> {
                    ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                    reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                    reserveTourList.add(reserveTour);
                });
            });
            // loc moi thoi gian
        } else if (Objects.nonNull(reserveTourFilterRequest.getStart_date())
                    && Objects.nonNull(reserveTourFilterRequest.getEnd_date())
                    && reserveTourFilterRequest.getDate_filter()
        ) {
        tourList = tourRepository.daysFilterReserveTours(reserveTourFilterRequest.getStart_date()
                ,reserveTourFilterRequest.getEnd_date());
        tourList.forEach(tour -> {

            tour.getTourTimes().forEach(tourTime -> {
                if(isDateInRange(tourTime.getDeparture_date(), reserveTourFilterRequest.getStart_date(),reserveTourFilterRequest.getEnd_date())) {
                ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                    reserveTourList.add(reserveTour);
                }
            });
        });

        // lay tat ca
        } else {
            tourList = tourRepository.findAll();
            tourList.forEach(tour -> {

                tour.getTourTimes().forEach(tourTime -> {
                    ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                    reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                    reserveTourList.add(reserveTour);
                });

            });
        }

        if (Objects.nonNull(reserveTourFilterRequest.getStart_date())
                && Objects.nonNull(reserveTourFilterRequest.getEnd_date())
                && reserveTourFilterRequest.getDate_filter()
                && Objects.nonNull(reserveTourFilterRequest.getCategory_slug())
        ) {
            tourList = tourRepository.filterReserveTours(StringUtils.getIdFromUrl(reserveTourFilterRequest.getCategory_slug()),reserveTourFilterRequest.getStart_date()
                    ,reserveTourFilterRequest.getEnd_date());
            tourList.forEach(tour -> {

                tour.getTourTimes().forEach(tourTime -> {
                    if(isDateInRange(tourTime.getDeparture_date(), reserveTourFilterRequest.getStart_date(),reserveTourFilterRequest.getEnd_date())) {
                        ReserveTourResponse reserveTour = reserveMapper.toReserveTourResponse(tour);
                        reserveTour.setTourTime(reserveMapper.toReserveTourTimeResponse(tourTime));
                        reserveTourList.add(reserveTour);
                    }
                });
            });
        }


        return reserveTourList;
    }



}
