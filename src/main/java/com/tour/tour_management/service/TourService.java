package com.tour.tour_management.service;

import com.tour.tour_management.dto.request.history.HistoryRequest;
import com.tour.tour_management.dto.request.tour.TourCreateRequest;
import com.tour.tour_management.dto.request.tour.TourUpdateRequest;
import com.tour.tour_management.dto.request.tourtime.TourTimeRequest;
import com.tour.tour_management.dto.response.tour.TourResponse;
import com.tour.tour_management.dto.response.tour.TourDetailResponse;
import com.tour.tour_management.entity.*;
import com.tour.tour_management.entity.Tour;
import com.tour.tour_management.exception.AppException;
import com.tour.tour_management.exception.CategoryErrorCode;
import com.tour.tour_management.exception.TourErrorCode;
import com.tour.tour_management.exception.TourTimeErrorCode;
import com.tour.tour_management.mapper.TourMapper;
import com.tour.tour_management.repository.AirlineRepository;
import com.tour.tour_management.repository.CategoryRepository;
import com.tour.tour_management.repository.TourRepository;
import com.tour.tour_management.repository.TourTimeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

// thay the autowired va tu tao private final,
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Service
public class TourService {
    TourRepository tourRepository;
    CategoryRepository categoryRepository;
    TourMapper tourMapper;
    AirlineRepository airlineRepository;
    TourTimeRepository tourTimeRepository;
    HistoryService historyService;

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public TourDetailResponse getTour(int tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
        return tourMapper.toTourDetailResponse(tourSorted(tour));
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getTours() {
        List<Tour> tourList = tourRepository.findAll();
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getToursCategory(Integer category_id, int active) {
        List<Tour> tourList = tourRepository.findByCategoryId(category_id);
        List<TourResponse> tourResponseList = new ArrayList<>();
        tourList.forEach(tour -> {
            if (active == -1)
                tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
            else if (tour.getStatus() == active) {
                tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
            }
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getActiveTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(1, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('ACCESS_TOUR')")
    public List<TourResponse> getLockedTours() {
        Sort sort = Sort.by(Sort.Direction.DESC, "tour_id");

        List<Tour> tourList = tourRepository.findByStatusWithSorting(0, sort);
        List<TourResponse> tourResponseList = new ArrayList<>();

        tourList.forEach(tour -> {
            tourResponseList.add(tourMapper.toTourResponse(tourSorted(tour)));
        });
        return tourResponseList;
    }

    @PreAuthorize("hasRole('CREATE_TOUR')")
    public TourDetailResponse createTour(TourCreateRequest tourCreateRequest) {
        Category category = categoryRepository.findById(tourCreateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        Tour tour = tourMapper.toTour(tourCreateRequest);
        tour.setCategory(category);
        tour.setStatus(1);

        Set<TourTime> tourTimes = new HashSet<>();

        tourCreateRequest.getTourTimes().forEach(tourTimeRequest -> {
            TourTime tourTime = tourMapper.toTourTime(tourTimeRequest);

            tourTime.setTour(tour);
            tourTime.setStatus(1);

            if (tourTimeRequest.getReturn_airline_id() != null) {
                Airline airline = null;
                airline = airlineRepository.findById(tourTimeRequest.getReturn_airline_id()).orElseThrow(
                        () -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
                tourTime.setReturnAirline(airline);
            } else {
                tourTime.setReturnAirline(null);
            }
            if (tourTimeRequest.getDeparture_airline_id() != null) {
                Airline airline = null;
                airline = airlineRepository.findById(tourTimeRequest.getDeparture_airline_id()).orElseThrow(
                        () -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
                tourTime.setDepartureAirline(airline);
            } else {
                tourTime.setDepartureAirline(null);
            }

            tourTimes.add(tourTime);
        });
        tour.setTourTimes(tourTimes);

        tourRepository.save(tour);
        historyService.createHistory("Created tour: " + tour.getTour_name() + " : " + tour.getTour_id());

        return tourMapper.toTourDetailResponse((tourSorted(tour)));
    }

    public Tour tourSorted(@NotNull Tour tour) {
        tour.setTourTimes(
                tourTimeRepository.findAllOrderedByTourId(
                        tour.getTour_id()));
        return tour;
    }

    @PreAuthorize("hasRole('CREATE_TOUR')")
    public TourDetailResponse createTourTime(int tour_id, TourTimeRequest tourTimeRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTime = new TourTime();
        tourMapper.CreateTourTime(tourTime, tourTimeRequest);
        tourTime.setTour(tour);
        tourTime.setStatus(1);

        if (tourTimeRequest.getReturn_airline_id() != null) {
            Airline airline = null;
            airline = airlineRepository.findById(tourTimeRequest.getReturn_airline_id()).orElseThrow(
                    () -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
            tourTime.setReturnAirline(airline);
        } else {
            tourTime.setReturnAirline(null);
        }

        if (tourTimeRequest.getDeparture_airline_id() != null) {
            Airline airline = airlineRepository.findById(tourTimeRequest.getDeparture_airline_id()).orElseThrow(
                    () -> new AppException(TourErrorCode.TOUR_NOT_FOUND));
            tourTime.setDepartureAirline(airline);
        } else {
            tourTime.setDepartureAirline(null);
        }

        tourTimeRepository.save(tourTime);
        tour.getTourTimes().add(tourTime);

        tourRepository.save(tour);
        historyService.createHistory("Created tour time: " + tour.getTour_name() + " : " + tour.getTour_id() + " : "
                + tourTime.getTime_name() + " : " + tourTime.getTour_time_id());

        return tourMapper.toTourDetailResponse(tour);
    }

    @PreAuthorize("hasRole('UPDATE_TOUR')")
    public TourDetailResponse updateTourTime(int tour_id, int tour_time_id, TourTimeRequest tourTimeRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTimeFound = new TourTime();

        for (TourTime tourTime : tour.getTourTimes()) {
            if (tourTime.getTour_time_id() == tour_time_id) {
                tourTime.setTour(tour);
                tourMapper.updateTourTime(tourTime, tourTimeRequest);
                tourTimeFound = tourTime;
                tourTimeRepository.save(tourTime);
                break;
            }
        }
        if (tourTimeFound.getTour_time_id() != tour_time_id) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }

        historyService.createHistory("Created tour time: " + tour.getTour_name() + " : " + tour.getTour_id() + " : "
                + tourTimeFound.getTime_name() + " : " + tourTimeFound.getTour_time_id());
        return tourMapper.toTourDetailResponse(tour);
    }

    @PreAuthorize("hasRole('UPDATE_TOUR')")
    public TourDetailResponse updateTour(int tour_id, TourUpdateRequest tourUpdateRequest) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        Category category = categoryRepository.findById(tourUpdateRequest.getCategory_id())
                .orElseThrow(() -> new AppException(CategoryErrorCode.CATEGORY_NOT_FOUND));
        tourMapper.updateTour(tour, tourUpdateRequest);
        tour.setCategory(category);

        tourRepository.save(tour);
        historyService.createHistory("Updated tour: " + tour.getTour_name() + " : " + tour.getTour_id());
        return tourMapper.toTourDetailResponse((tourSorted(tour)));
    }

    @PreAuthorize("hasRole('CHANGE_TOUR_STATUS')")
    public TourDetailResponse changeStatusTour(Integer tour_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        if (tour.getStatus() == 0) {
            tour.setStatus(1);
        } else {
            tour.setStatus(0);
        }
        Tour tourSaved = tourRepository.save(tour);
        historyService.createHistory("Changed status tour: " + tour.getTour_name() + " : " + tour.getTour_id());
        return tourMapper.toTourDetailResponse(tourSorted(tour));

    }

    @PreAuthorize("hasRole('CHANGE_TOUR_STATUS')")
    public TourDetailResponse changeStatusTourTime(int tour_id, int tour_time_id) {
        Tour tour = tourRepository.findById(tour_id)
                .orElseThrow(() -> new AppException(TourErrorCode.TOUR_NOT_FOUND));

        TourTime tourTimeFound = new TourTime();

        for (TourTime tourTime : tour.getTourTimes()) {
            if (tourTime.getTour_time_id() == tour_time_id) {
                tourTime.setTour(tour);
                if (tourTime.getStatus() == 0) {
                    tourTime.setStatus(1);
                } else {
                    tourTime.setStatus(0);
                }
                tourTimeFound = tourTime;
                tourTimeRepository.save(tourTime);
                break;
            }
        }
        if (tourTimeFound.getTour_time_id() != tour_time_id) {
            throw new AppException(TourTimeErrorCode.TIME_NOT_FOUND);
        }
        historyService.createHistory("Changed status tour time: " + tour.getTour_name() + " : " + tour.getTour_id()
                + " : " + tourTimeFound.getTime_name() + " : " + tourTimeFound.getTour_time_id());
        return tourMapper.toTourDetailResponse(tourSorted(tour));

    }
}
